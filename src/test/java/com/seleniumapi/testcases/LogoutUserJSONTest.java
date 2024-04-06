package com.seleniumapi.testcases;

import com.seleniumapi.objects.BillingAddress;
import com.seleniumapi.objects.Product;
import com.seleniumapi.testbase.TestBase;

public class LogoutUserJSONTest extends TestBase {

    /*private BillingAddress testData;
    private HomePage homePage;


    @Parameters("testDataPath")
    @BeforeTest
    public void setObjects(String testDataPath) throws IOException {
        homePage = new HomePage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, BillingAddress.class);

    }

    @Test(groups = {"sanity","regression"})
    public void logoutUserCheckoutFlowDataViaJSON() throws Exception {
        Product product = OrderHelper.getProductData(properties_config.getProperty("productid"));
        homePage.load()
                .goToStorePage()
                .searchProductWithColorAndAddToCart(properties_config.getProperty("color"),product.getName(), 1)
                .navigateToCartIfItemAdded()
                .proceedToCheckout()
                .enterBillingDetailsNewUser(testData)
                .placeOrder()
                .isOrderConfirmed();
    }



    @Test(groups = {"smoke","regression"})
    public void logoutUserCheckoutFlowDataViaGenericDesirialiser() throws InterruptedException, IOException {
        Product product = OrderHelper.getProductData(properties_config.getProperty("productid"));
        homePage.load()
                .goToStorePage()
                .searchProductWithColorAndAddToCart(properties_config.getProperty("blue"),product.getName(), 1)
                .navigateToCartIfItemAdded()
                .proceedToCheckout()
                .enterBillingDetailsNewUser(testData)
                .placeOrder()
                .isOrderConfirmed();
    }
*/
}
