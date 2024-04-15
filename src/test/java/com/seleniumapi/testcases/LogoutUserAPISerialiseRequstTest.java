package com.seleniumapi.testcases;

import com.seleniumapi.suite.api.Route;
import com.seleniumapi.suite.api.applicationapi.StudentApiList;
import com.seleniumapi.suite.helper.OrderHelper;
import com.seleniumapi.suite.objects.BillingAddress;
import com.seleniumapi.suite.objects.Product;
import com.seleniumapi.suite.pages.HomePage;
import com.seleniumapi.suite.testbase.TestBase;
import com.seleniumapi.suite.Constants.Constants;
import com.seleniumapi.utils.PropertyReaderUtils.PropertyReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

public class LogoutUserAPISerialiseRequstTest extends TestBase {

    private Response getSudtentApiResponse ;

    private BillingAddress testData;
    private HomePage homePage;
    @BeforeMethod(alwaysRun = true)
    public void setObjects() throws IOException {
        homePage = new HomePage(TestBase.getDriver());
    }

    @BeforeTest(alwaysRun = true)
    public void getStudentData() throws JSONException, IOException {
        System.out.println("Before test");

         Response response = StudentApiList.get(Route.STUDENT_DETAIL, Constants.JSON_SCHEMA_PATH);

        System.out.println("This is what we are getting" + response.statusCode());

        System.out.println("Normal Assert-> " + response.path("firstName"));
        String firstNameactual = response.path("firstName");
        Assert.assertEquals(firstNameactual,"demo" );

        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println("JsonPath Assert -> " + jsonPath.getString("firstName"));
        String actual = jsonPath.getString("firstName");
        assertThat(actual, equalTo("demo"));

        JSONAssert.assertEquals(response.asString(), "{\n" +
                "    \"firstName\": \"demo\",\n" +
                "    \"lastName\": \"iser\",\n" +
                "    \"city\": \"Delhi\",\n" +
                "    \"addressLineOne\": \"C-37\",\n" +
                "    \"postalCode\": \"110009\",\n" +
                "    \"email\": \"demouser@gmail.com\",\n" +
                "    \"company\": \"ABC_Company\",\n" +
                "    \"country\": \"India\",\n" +
                "    \"state\": \"Delhi\"\n" +
                "}", JSONCompareMode.STRICT);

        testData = response.as(BillingAddress.class);


    }

    @Test(groups = {"sanity"})
    public void logoutUserCheckoutFlowExcel() throws InterruptedException, IOException {
        Product product = OrderHelper.getProductData(PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES,"productID"));
        homePage.load()
                .goToStorePage()
                .searchProductWithColorAndAddToCart(PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES,"color"),product.getName(),1)
                .navigateToCartIfItemAdded()
                .proceedToCheckout()
                .enterBillingDetailsNewUser(testData)
                .placeOrder()
                .isOrderConfirmed();
    }
}
