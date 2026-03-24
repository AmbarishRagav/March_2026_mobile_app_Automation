package tests.account;

import bases.Base;
import pages.AccountPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteAnAccountTest extends Base {

    AccountPage accountPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void initPages() {
        accountPage = new AccountPage(driver);
    }

    @Test
    public void deleteAccount() {
        Assert.assertTrue(accountPage.deleteAccount(), "Account deletion failed");
    }
}
