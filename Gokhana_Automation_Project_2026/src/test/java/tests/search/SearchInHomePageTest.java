package tests.search;

import bases.Base;
import pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchInHomePageTest extends Base {

    SearchPage searchPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void initPages() {
        searchPage = new SearchPage(driver);
    }

    @Test
    public void searchInHomePage() {
        boolean result = searchPage.searchInHomePage("gokhana");
        Assert.assertTrue(result, "Search result for 'gokhana' not found on homepage");
    }
}
