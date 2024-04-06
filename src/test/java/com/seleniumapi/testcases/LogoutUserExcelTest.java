package com.seleniumapi.testcases;

import com.seleniumapi.helper.OrderHelper;
import com.seleniumapi.objects.Product;
import com.seleniumapi.pages.HomePage;
import com.seleniumapi.testbase.TestBase;
import com.seleniumapi.utils.Constants.Constants;
import com.seleniumapi.utils.PropertyReaderUtils.PropertyReader;
import com.seleniumapi.utils.excelUtils.ExcelUtils;
import com.seleniumapi.utils.resourceLoaderUtil.ResourceLoader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class LogoutUserExcelTest extends TestBase {

    private HomePage homePage;
    @BeforeMethod(alwaysRun = true)
    public void setObjects() throws IOException {
        homePage = new HomePage(TestBase.getDriver());
    }


    @Test(groups = {"sanity"}, dataProvider = "test1" )
    public void logoutUserCheckoutFlowExcel(Map<String,String> map) throws InterruptedException, IOException {
        Product product = OrderHelper.getProductData(PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES,"productID"));
        homePage.load()
                .goToStorePage()
                .searchProductWithColorAndAddToCart(PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES,"color"),product.getName(),1)
                .navigateToCartIfItemAdded()
                .proceedToCheckout()
                .enterBillingDetailsNewUserExcel(map)
                .placeOrder()
                .isOrderConfirmed();
    }

    @DataProvider(name = "test1")
    public Object[][] getDataFromExcel() throws IOException {
        Map<String,String> map = null;
        try {
            String sheet_name = "stage";
            String testdata = "logutuser01";
            map = ExcelUtils.getInstance().getRowData(sheet_name, testdata);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Object[][] {{map}};
    }
}
