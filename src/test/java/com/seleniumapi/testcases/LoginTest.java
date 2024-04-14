package com.seleniumapi.testcases;
import com.seleniumapi.suite.helper.OrderHelper;
import com.seleniumapi.suite.objects.BillingAddress;
import com.seleniumapi.suite.objects.Product;
import com.seleniumapi.suite.testbase.TestBase;
import com.seleniumapi.suite.utils.Constants.Constants;
import com.seleniumapi.utils.JsonUtil.JsonUtil;
import com.seleniumapi.utils.PropertyReaderUtils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.seleniumapi.suite.pages.HomePage;

import java.io.IOException;


public class LoginTest extends TestBase {

    private static final Logger log = LogManager.getLogger(LoginTest.class.getName());
    private HomePage homePage;
    private BillingAddress testData;

  @BeforeMethod(alwaysRun = true)
    public void setObjects() throws IOException {
        homePage = new HomePage(TestBase.getDriver());
        testData = JsonUtil.getTestData(Constants.BILLING_ADDRESS_JSON, BillingAddress.class);
    }

    @Test(groups = {"sanity"})
    public void logoutUserCheckoutFlow() throws InterruptedException, IOException {
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


    @Test(enabled = false)
    public void loginUserCheckoutFlow() throws InterruptedException, IOException {
        Product product = OrderHelper.getProductData(PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES,"color"));
        homePage.load()
                .goToStorePage()
                .searchProductWithColorAndAddToCart(PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES,"color"),product.getName(),1)
                .navigateToCartIfItemAdded()
                .proceedToCheckout()
                 .loginWithUser()
                .enterBillingDetailsNewUser(OrderHelper.generateBillingData())
                .placeOrder()
                .isOrderConfirmed();
    }


    @Test(groups = {"regression"})
    public void loginUserCheckoutFlowFaiedCase() throws InterruptedException, IOException {
        Product product = OrderHelper.getProductData(PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES,"productID"));
        homePage.load()
                .goToStorePage()
                .searchProductWithColorAndAddToCart(PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES,"color2"),product.getName(),1)
                .navigateToCartIfItemAdded()
                .proceedToCheckout()
                .loginWithUser()
                .enterBillingDetailsNewUser(OrderHelper.generateBillingData())
                .placeOrder()
                .isOrderConfirmed();


    }


}
