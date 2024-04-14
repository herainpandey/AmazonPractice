package com.seleniumapi.testcases;

import com.seleniumapi.suite.helper.OrderHelper;
import com.seleniumapi.suite.objects.BillingAddress;
import com.seleniumapi.suite.objects.Product;
import com.seleniumapi.suite.pages.HomePage;
import com.seleniumapi.suite.testbase.TestBase;
import com.seleniumapi.suite.utils.Constants.Constants;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.seleniumapi.utils.PropertyReaderUtils.PropertyReader;

import java.io.IOException;

public class LogoutUserJSONTest extends TestBase {

    private BillingAddress testData;
    private HomePage homePage;


    @Parameters("testDataPath")
    @BeforeTest
    public void setObjects(String testDataPath) throws IOException {
        homePage = new HomePage(getDriver());
        this.testData = com.seleniumapi.utils.JsonUtil.JsonUtil.getTestData(testDataPath, BillingAddress.class);

    }

    @Test(groups = {"sanity","regression"})
    public void logoutUserCheckoutFlowDataViaJSON() throws Exception {
        Product product = OrderHelper.getProductData(PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES,"productid"));
        homePage.load()
                .goToStorePage()
                .searchProductWithColorAndAddToCart(PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES, ("color")),product.getName(), 1)
                .navigateToCartIfItemAdded()
                .proceedToCheckout()
                .enterBillingDetailsNewUser(testData)
                .placeOrder()
                .isOrderConfirmed();
    }



    @Test(groups = {"smoke","regression"})
    public void logoutUserCheckoutFlowDataViaGenericDesirialiser() throws InterruptedException, IOException, IOException {
        Product product = OrderHelper.getProductData(com.seleniumapi.utils.PropertyReaderUtils.PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES,"productid"));
        homePage.load()
                .goToStorePage()
                .searchProductWithColorAndAddToCart(com.seleniumapi.utils.PropertyReaderUtils.PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES,"blue"),product.getName(), 1)
                .navigateToCartIfItemAdded()
                .proceedToCheckout()
                .enterBillingDetailsNewUser(testData)
                .placeOrder()
                .isOrderConfirmed();
    }
}
