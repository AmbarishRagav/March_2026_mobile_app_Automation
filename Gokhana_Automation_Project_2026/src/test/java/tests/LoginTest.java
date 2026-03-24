package tests;

import bases.Base;
import pages.LoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends Base {

    LoginPage loginPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void initPages() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLogin() throws Exception {
        loginPage.login(
                config.getProperty("mobileNumber"),
                config.getProperty("otp")
        );
    }
}
