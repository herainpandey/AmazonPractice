package com.seleniumapi.testcases;

import com.seleniumapi.suite.helper.OrderHelper;
import com.seleniumapi.suite.objects.BillingAddress;
import com.seleniumapi.suite.objects.Product;
import com.seleniumapi.suite.pages.HomePage;
import com.seleniumapi.suite.testbase.TestBase;
import com.seleniumapi.suite.Constants.Constants;
import org.testng.annotations.BeforeMethod;
import com.seleniumapi.utils.JsonUtil.JsonUtil;
import org.testng.annotations.Test;
import com.seleniumapi.utils.PropertyReaderUtils.PropertyReader;

import java.io.IOException;

public class LogoutUserJSONTest extends TestBase {

    private BillingAddress testData;
    private HomePage homePage;


    @BeforeMethod(alwaysRun = true)
    public void setObjects() throws IOException {
        homePage = new HomePage(getDriver());
        this.testData = JsonUtil.getTestData(Constants.BILLING_ADDRESS_JSON, BillingAddress.class);

    }

    @Test(groups = {"sanity","regression"})
    public void logoutUserCheckoutFlowDataViaJSON() throws Exception {
        Product product = OrderHelper.getProductData(PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES,"productID"));
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
        Product product = OrderHelper.getProductData(com.seleniumapi.utils.PropertyReaderUtils.PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES,"productID"));
        homePage.load()
                .goToStorePage()
                .searchProductWithColorAndAddToCart(com.seleniumapi.utils.PropertyReaderUtils.PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES,"color2"),product.getName(), 1)
                .navigateToCartIfItemAdded()
                .proceedToCheckout()
                .enterBillingDetailsNewUser(testData)
                .placeOrder()
                .isOrderConfirmed();
    }
}
