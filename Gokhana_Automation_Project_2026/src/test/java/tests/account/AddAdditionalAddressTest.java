package tests.account;

import bases.Base;
import pages.AccountPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddAdditionalAddressTest extends Base {

    AccountPage accountPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void initPages() {
        accountPage = new AccountPage(driver);
    }

    @Test
    public void addAdditionalAddress() {
        Assert.assertTrue(accountPage.selectCurrentAddress(), "Address not added");
    }
}
