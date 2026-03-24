package pages;

import constants.Locators;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    public AccountPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    private WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private boolean clickUsingActions(By locator) {
        try {
            WebElement element = waitForElement(locator);
            actions.moveToElement(element).click().perform();
            return true;
        } catch (Exception e) {
            System.err.println("❌ Failed to click: " + locator + " - " + e.getMessage());
            return false;
        }
    }

    private boolean sendKeysUsingActions(By locator, String text) {
        try {
            WebElement element = waitForElement(locator);
            actions.moveToElement(element).click().sendKeys(text).perform();
            return true;
        } catch (Exception e) {
            System.err.println("❌ Failed to send keys: " + locator + " - " + e.getMessage());
            return false;
        }
    }

    // ── Account Details ───────────────────────────────────

    public boolean addAccountDetails() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("(//android.widget.ImageView)[last()]"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.ImageView[@content-desc='Edit Profile']"))).click();

            clickUsingActions(By.xpath("//android.view.View[@content-desc='Employee ID, Double tap to start typing']/android.widget.EditText"));
            sendKeysUsingActions(By.xpath("//android.view.View[@content-desc='Employee ID, Double tap to start typing']/android.widget.EditText"), "GK123");

            clickUsingActions(By.xpath("//android.view.View[@content-desc='Company email, Double tap to start typing']/android.widget.EditText"));
            sendKeysUsingActions(By.xpath("//android.view.View[@content-desc='Company email, Double tap to start typing']/android.widget.EditText"), "mohammed.nihal@gokhana.com");

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.view.View[@content-desc=\"Add Details\"]"))).click();

            System.out.println("✅ Account details added");
            return true;
        } catch (Exception e) {
            System.err.println("❌ addAccountDetails failed: " + e.getMessage());
            return false;
        }
    }

    public boolean editAccountDetails() {
        try {
            clickUsingActions(By.xpath("(//android.widget.ImageView)[last()]"));
            clickUsingActions(By.xpath("//android.widget.ImageView[@content-desc='Edit Profile']"));
            clickUsingActions(By.xpath("//android.view.View[@content-desc=\"Edit Employee Details\"]"));
            clickUsingActions(By.xpath("//android.view.View[@content-desc=\"Cubicle, Double tap to start typing\"]/android.widget.EditText"));
            sendKeysUsingActions(By.xpath("//android.view.View[@content-desc=\"Cubicle, Double tap to start typing\"]/android.widget.EditText"), "1234");

            System.out.println("✅ Account details edited");
            return true;
        } catch (Exception e) {
            System.err.println("❌ editAccountDetails failed: " + e.getMessage());
            return false;
        }
    }

    // ── Corporate Code ────────────────────────────────────

    public boolean addNewCorporateCode(String corporateCode) {
        try {
            boolean result = clickUsingActions(By.xpath("(//android.widget.ImageView)[last()]")) &&
                    clickUsingActions(By.xpath("//android.widget.ImageView[@content-desc='Corporate Code']")) &&
                    sendKeysUsingActions(By.xpath("//android.widget.EditText"), corporateCode) &&
                    clickUsingActions(By.xpath("//android.view.View[@content-desc='Validate']")) &&
                    clickUsingActions(By.xpath("//android.widget.ImageView[@content-desc='Your corporate code has been added successfully']"));
            System.out.println("✅ Corporate code added");
            return result;
        } catch (Exception e) {
            System.err.println("❌ addNewCorporateCode failed: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteCorporateCode() {
        try {
            boolean result = clickUsingActions(By.xpath("(//android.widget.ImageView)[last()]")) &&
                    clickUsingActions(By.xpath("//android.widget.ImageView[@content-desc='Corporate Code']")) &&
                    clickUsingActions(By.xpath("//android.widget.Button[@content-desc='Delete Icon, Double tap to remove corporate code']")) &&
                    clickUsingActions(By.xpath("//android.view.View[@content-desc='Yes, Delete']"));
            System.out.println("✅ Corporate code deleted");
            return result;
        } catch (Exception e) {
            System.err.println("❌ deleteCorporateCode failed: " + e.getMessage());
            return false;
        }
    }

    // ── Account Deletion ──────────────────────────────────

    public boolean deleteAccount() {
        try {
            boolean result = clickUsingActions(By.xpath("(//android.widget.ImageView)[last()]")) &&
                    clickUsingActions(By.xpath("//android.widget.ImageView[@content-desc='Delete Account']")) &&
                    clickUsingActions(By.xpath("//android.view.View[@content-desc='Continue']")) &&
                    clickUsingActions(By.xpath("//android.view.View[@content-desc='Delete']"));
            System.out.println("✅ Account deleted");
            return result;
        } catch (Exception e) {
            System.err.println("❌ deleteAccount failed: " + e.getMessage());
            return false;
        }
    }

    // ── Favourites ────────────────────────────────────────

    public boolean addFavourites() {
        try {
            boolean result = clickUsingActions(By.xpath("(//android.widget.ImageView)[last()-2]")) &&
                    clickUsingActions(By.xpath("(//android.widget.ImageView[@content-desc='Favourite Button, Double tap to add to favourite'])[1]")) &&
                    clickUsingActions(By.xpath("(//android.widget.ImageView[@content-desc='Favourite Button, Double tap to add to favourite'])[2]"));
            System.out.println("✅ Favourites added");
            return result;
        } catch (Exception e) {
            System.err.println("❌ addFavourites failed: " + e.getMessage());
            return false;
        }
    }

    // ── Address ───────────────────────────────────────────

    public boolean selectCurrentAddress() {
        try {
            boolean result = clickUsingActions(By.xpath("(//android.widget.ImageView)[last()]")) &&
                    clickUsingActions(By.xpath("//android.widget.ImageView[@content-desc='Manage Address']")) &&
                    clickUsingActions(By.xpath("//android.view.View[@content-desc='Add New Address']")) &&
                    clickUsingActions(By.xpath("//android.view.View[@content-desc='Use current locations']")) &&
                    clickUsingActions(By.xpath("//android.view.View[@content-desc='Confirm']")) &&
                    sendKeysUsingActions(By.xpath("//android.view.View[@content-desc='House / Flat No.*, Double tap to start typing']"), "24th Main") &&
                    sendKeysUsingActions(By.xpath("//android.view.View[@content-desc='Apartment/Road/Landmark.*, Double tap to start typing']"), "MSR PG") &&
                    sendKeysUsingActions(By.xpath("//android.view.View[@content-desc='Locality*, Double tap to start typing']"), "Bangalore") &&
                    clickUsingActions(By.xpath("//android.view.View[@content-desc='Add Address']"));
            System.out.println("✅ Address selected");
            return result;
        } catch (Exception e) {
            System.err.println("❌ selectCurrentAddress failed: " + e.getMessage());
            return false;
        }
    }
}
