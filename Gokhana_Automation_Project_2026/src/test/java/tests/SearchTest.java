package tests;

import bases.Base;
import pages.SearchPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchTest extends Base {

    SearchPage searchPage;
    String foodCourtName;

    @BeforeClass
    public void initPages() {
        searchPage = new SearchPage(driver);
        foodCourtName = config.getProperty("searchfoodcourtName");
    }

    // TC_001 - Verify food court can be searched using global search button
    @Test(priority = 1, description = "Search Food Court - Verify Search food court can be searched using global search button")
    public void searchWithExactName() throws InterruptedException {searchPage.searchFor(foodCourtName);
        System.out.println("✅ Search flow executed");
    }
}