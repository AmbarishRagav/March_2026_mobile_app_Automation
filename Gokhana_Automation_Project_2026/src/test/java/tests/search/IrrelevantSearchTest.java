package tests.search;

import bases.Base;
import pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IrrelevantSearchTest extends Base {

    SearchPage searchPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void initPages() {
        searchPage = new SearchPage(driver);
    }

    @Test
    public void irrelevantSearch() {
        boolean result = searchPage.irrelevantSearch();
        Assert.assertFalse(result, "Irrelevant search should return no results but it did");
    }
}
