package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {

    AndroidDriver driver;
    WebDriverWait wait;

    public SearchPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ── Locators ──────────────────────────────────────────

    private WebElement searchIcon() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.ImageView[@content-desc='Search']")
                // 🔴 Update from Appium Inspector
        ));
    }

    private WebElement searchBox() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.EditText[@hint='Search']")
                // 🔴 Update from Appium Inspector
        ));
    }

    private List<WebElement> searchResults() {
        return driver.findElements(
                AppiumBy.xpath("//android.widget.TextView[@resource-id='result_name']")
                // 🔴 Update from Appium Inspector
        );
    }

    // ── Actions ───────────────────────────────────────────

    public void clickSearchIcon() {
        searchIcon().click();
        System.out.println("✅ Search icon clicked");
    }

    public void typeSearchText(String text) {
        searchBox().sendKeys(text);
        System.out.println("✅ Search text entered: " + text);
    }

    public List<String> getResultNames() {
        List<WebElement> results = searchResults();
        List<String> names = new java.util.ArrayList<>();
        for (WebElement result : results) {
            names.add(result.getText());
        }
        System.out.println("✅ Results found: " + names.size());
        return names;
    }

    // ── From old project ──────────────────────────────────

    private boolean isSearchResultDisplayed(org.openqa.selenium.By locator, boolean shouldExist) {
        try {
            WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return shouldExist == result.isDisplayed();
        } catch (Exception e) {
            return !shouldExist;
        }
    }

    public boolean searchInHomePage(String searchTerm) {
        try {
            searchBox().sendKeys(searchTerm);
            return isSearchResultDisplayed(
                    org.openqa.selenium.By.xpath("//android.view.View[contains(@content-desc, 'Gokhana')]"),
                    !searchTerm.equals("xxxxxxx")
            );
        } catch (Exception e) {
            System.err.println("❌ searchInHomePage failed: " + e.getMessage());
            return false;
        }
    }

    public boolean irrelevantSearch() {
        return searchInHomePage("xxxxxxx");
    }
}