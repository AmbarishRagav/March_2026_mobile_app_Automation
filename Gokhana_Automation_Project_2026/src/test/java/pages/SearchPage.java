package pages;

import constants.Locators;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

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
                AppiumBy.xpath(Locators.SEARCH_ICON)
        ));
    }

    private WebElement searchInputField() {
        // The search field is a non-clickable android.view.View (Flutter/custom rendered)
        // We wait for it to be present (visible) — we will tap it by coordinates instead
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId(Locators.SEARCH_INPUT_FIELD)
        ));
    }

    // ── Actions ───────────────────────────────────────────

    public void clickSearchIcon() {
        searchIcon().click();
        System.out.println("✅ Search icon clicked — navigated to search page");
    }

    public void typeSearchQuery(String searchfoodcourtName) throws InterruptedException {
        // Wait for the search field to be visible — cursor is already focused on landing
        searchInputField();
        System.out.println("✅ Search input field found on screen");

        // Use 'mobile: type' — types into the currently focused element without needing
        // the soft keyboard to be visible. This works on both emulator and real device.
        // 'mobile: type' is natively supported by UiAutomator2 driver (no extra security flags needed).
        driver.executeScript("mobile: type", Map.of("text", searchfoodcourtName));

        System.out.println("✅ Search query typed: " + searchfoodcourtName);
        Thread.sleep(9000); // wait 9 seconds for results to load
    }

    // ── Flows ─────────────────────────────────────────────

    public void searchFor(String searchfoodcourtName) throws InterruptedException {
        clickSearchIcon();            // clicks the search icon ONCE → moves to search page
        typeSearchQuery(searchfoodcourtName); // taps field by coords → types into active element → waits 9s
    }
}