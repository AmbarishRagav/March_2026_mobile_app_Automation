package tests;

import bases.Base;
import pages.LoginPage;
import pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTest extends Base {

    LoginPage loginPage;
    SearchPage searchPage;
    String foodCourtName;

    @BeforeClass(dependsOnMethods = "setUp")
    public void initPages() {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        foodCourtName = config.getProperty("foodCourtName");
    }

    // TC_001 - Search with exact food court name
    @Test(priority = 1, description = "Search Food Court - Exact Name")
    public void searchWithExactName() {
        searchPage.clickSearchIcon();
        searchPage.typeSearchText(foodCourtName);

        List<String> results = searchPage.getResultNames();
        Assert.assertFalse(results.isEmpty(), "No results found!");

        boolean found = results.stream()
                .anyMatch(r -> r.equalsIgnoreCase(foodCourtName));
        Assert.assertTrue(found, "Exact food court name not found in results!");
        System.out.println("✅ TC_001 Passed - Exact Name Search");
    }

    // TC_002 - Search with partial name
    @Test(priority = 2, description = "Search Food Court - Partial Name")
    public void searchWithPartialName() {
        String partialName = foodCourtName.substring(0, 3); // first 3 chars

        searchPage.clickSearchIcon();
        searchPage.typeSearchText(partialName);

        List<String> results = searchPage.getResultNames();
        Assert.assertFalse(results.isEmpty(), "No results for partial search!");

        boolean found = results.stream()
                .anyMatch(r -> r.toLowerCase().contains(partialName.toLowerCase()));
        Assert.assertTrue(found, "Partial name not found in results!");
        System.out.println("✅ TC_002 Passed - Partial Name Search");
    }

    // TC_003 - Search with uppercase
    @Test(priority = 3, description = "Search Food Court - Uppercase")
    public void searchWithUppercase() {
        searchPage.clickSearchIcon();
        searchPage.typeSearchText(foodCourtName.toUpperCase());

        List<String> results = searchPage.getResultNames();
        Assert.assertFalse(results.isEmpty(), "No results for uppercase search!");
        System.out.println("✅ TC_003 Passed - Uppercase Search");
    }

    // TC_004 - Search with lowercase
    @Test(priority = 4, description = "Search Food Court - Lowercase")
    public void searchWithLowercase() {
        searchPage.clickSearchIcon();
        searchPage.typeSearchText(foodCourtName.toLowerCase());

        List<String> results = searchPage.getResultNames();
        Assert.assertFalse(results.isEmpty(), "No results for lowercase search!");
        System.out.println("✅ TC_004 Passed - Lowercase Search");
    }
}