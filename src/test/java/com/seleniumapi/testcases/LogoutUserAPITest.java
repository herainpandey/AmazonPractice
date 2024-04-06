package com.seleniumapi.testcases;

import com.seleniumapi.helper.OrderHelper;
import com.seleniumapi.objects.BillingAddress;
import com.seleniumapi.objects.Product;
import com.seleniumapi.pages.HomePage;
import com.seleniumapi.testbase.TestBase;
import com.seleniumapi.utils.Constants.Constants;
import com.seleniumapi.utils.JsonUtil.JsonUtil;
import com.seleniumapi.utils.PropertyReaderUtils.PropertyReader;
import com.seleniumapi.utils.excelUtils.ExcelUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


import java.io.IOException;
import java.util.Map;

public class LogoutUserAPITest extends TestBase {

    private Response getSudtentApiResponse ;

    private BillingAddress testData;
    private HomePage homePage;
    @BeforeMethod(alwaysRun = true)
    public void setObjects() throws IOException {
        homePage = new HomePage(TestBase.getDriver());
    }

    @BeforeTest(alwaysRun = true)
    public void getStudentData() throws IOException {
        System.out.println("Before test");

        getSudtentApiResponse = given().baseUri("https://bf88ba7b-8264-46f2-8440-a99aa3ff9370.mock.pstmn.io")
                .when().log().all()
                .get("v1/getStudentDetail")
                .then()
                .extract().response();

        testData = JsonUtil.getDeserializeObject(getSudtentApiResponse.asString(), BillingAddress.class);

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
