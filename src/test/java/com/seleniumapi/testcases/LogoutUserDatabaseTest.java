package com.seleniumapi.testcases;

import com.seleniumapi.suite.helper.OrderHelper;
import com.seleniumapi.suite.objects.BillingAddress;
import com.seleniumapi.suite.objects.Product;
import com.seleniumapi.suite.pages.HomePage;
import com.seleniumapi.suite.testbase.TestBase;
import com.seleniumapi.suite.Constants.Constants;
import com.seleniumapi.suite.utils.DBUtils.DatabaseManager;
import com.seleniumapi.utils.PropertyReaderUtils.PropertyReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.seleniumapi.suite.helper.OrderHelper.generateBillingData;

public class LogoutUserDatabaseTest extends TestBase {

    private ArrayList<BillingAddress> testData = new ArrayList<>();
    private HomePage homePage;
    @BeforeMethod(alwaysRun = true)
    public void setObjects() throws IOException {
        homePage = new HomePage(TestBase.getDriver());
    }

    @BeforeTest(alwaysRun = true)
    public void getStudentData() throws IOException, SQLException {
        System.out.println("Before test Getting data from Database");
        String sql= "select * from Student where firstName='demo';";
        ResultSet resultSet = DatabaseManager.getInstance().getData(sql);
        while(resultSet.next()) {
            testData.add(generateBillingData(resultSet));
        }
    }

    @Test(groups = {"sanity"})
    public void logoutUserCheckoutFlowExcel() throws InterruptedException, IOException {
        Product product = OrderHelper.getProductData(PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES,"productID"));
        homePage.load()
                .goToStorePage()
                .searchProductWithColorAndAddToCart(PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES,"color"),product.getName(),1)
                .navigateToCartIfItemAdded()
                .proceedToCheckout()
                .enterBillingDetailsNewUser(testData.get(0))
                .placeOrder()
                .isOrderConfirmed();
    }
}
