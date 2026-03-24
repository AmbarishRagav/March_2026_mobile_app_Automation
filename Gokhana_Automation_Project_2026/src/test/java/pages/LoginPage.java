package pages;

import constants.Locators;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    AndroidDriver driver;
    WebDriverWait wait;

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ── Locators ──────────────────────────────────────────

    private WebElement allowNotificationBtn() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath(Locators.NOTIFICATION_ALLOW_BTN)
        ));
    }

    private boolean isNotificationPopupPresent() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(4))
                    .until(ExpectedConditions.presenceOfElementLocated(
                            AppiumBy.xpath(Locators.NOTIFICATION_ALLOW_BTN)
                    ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isUpdatePopupPresent() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(4))
                    .until(ExpectedConditions.presenceOfElementLocated(
                            AppiumBy.accessibilityId(Locators.LATER_BUTTON)
                    ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement laterBtn() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId(Locators.LATER_BUTTON)
        ));
    }

    private WebElement foodCourtButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId(Locators.FOOD_COURT_BUTTON)
        ));
    }

    private WebElement mobileNumberField() {
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.xpath(Locators.MOBILE_NUMBER_FIELD)
                ));
    }

    private WebElement termsCheckbox() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath(Locators.TERMS_CHECKBOX)
        ));
    }

    private WebElement getOTPButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath(Locators.GET_OTP_BUTTON)
        ));
    }

    private WebElement otpInputField() {
        // OTP field uniquely identified by "Enter OTP" label before it
        return new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath(Locators.OTP_INPUT_FIELD)
                ));
    }

    // ── Actions ───────────────────────────────────────────

    public void allowNotifications() {
        if (isNotificationPopupPresent()) {
            allowNotificationBtn().click();
            System.out.println("✅ Notification allowed");
        } else {
            System.out.println("⚠️ Notification popup not present — skipping");
        }
    }

    public void dismissUpdatePopup() {
        if (isUpdatePopupPresent()) {
            laterBtn().click();
            System.out.println("✅ Update popup dismissed");
        } else {
            System.out.println("⚠️ Update popup not present — skipping");
        }
    }

    public void clickFoodCourt() {
        foodCourtButton().click();
        System.out.println("✅ Food Court clicked");
    }

    public void enterMobileNumber(String number) throws InterruptedException {
        mobileNumberField().sendKeys(number);
        Thread.sleep(2000);

        // Keyboard handling differs per device type
        String udid = driver.getCapabilities().getCapability("appium:udid").toString();
        boolean isPhysical = !udid.contains("emulator");

        if (isPhysical) {
            // Physical device — press back button to close keyboard
            try {
                Runtime.getRuntime().exec(new String[]{"adb", "-s", udid, "shell", "input", "keyevent", "4"});
                System.out.println("✅ Keyboard dismissed via back button (physical device)");
            } catch (Exception e) {
                System.out.println("⚠️ Could not dismiss keyboard: " + e.getMessage());
            }
        } else {
            // Emulator — tap title area at top to close keyboard
            org.openqa.selenium.Dimension size = driver.manage().window().getSize();
            int x = size.getWidth() / 2;
            int y = (int) (size.getHeight() * 0.12);
            org.openqa.selenium.interactions.PointerInput finger =
                    new org.openqa.selenium.interactions.PointerInput(
                            org.openqa.selenium.interactions.PointerInput.Kind.TOUCH, "finger");
            org.openqa.selenium.interactions.Sequence tap =
                    new org.openqa.selenium.interactions.Sequence(finger, 1);
            tap.addAction(finger.createPointerMove(Duration.ZERO,
                    org.openqa.selenium.interactions.PointerInput.Origin.viewport(), x, y));
            tap.addAction(finger.createPointerDown(
                    org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT.asArg()));
            tap.addAction(finger.createPointerUp(
                    org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(java.util.Arrays.asList(tap));
            System.out.println("✅ Keyboard dismissed via tap at (" + x + ", " + y + ") (emulator)");
        }

        Thread.sleep(2000); // wait for checkbox to appear
        System.out.println("✅ Mobile number entered: " + number);
    }

    public void acceptTerms() throws InterruptedException {
        termsCheckbox().click();
        Thread.sleep(2000); // wait for Get OTP button to activate
        System.out.println("✅ Terms accepted");
    }

    public void clickGetOTP() throws InterruptedException {
        getOTPButton().click();
        Thread.sleep(2000); // wait for OTP screen to load
        System.out.println("✅ Get OTP clicked");
    }

    public void enterOTP(String otp) throws InterruptedException {
        otpInputField().click();
        otpInputField().sendKeys(otp);
        Thread.sleep(2000); // wait for Login button to highlight
        System.out.println("✅ OTP entered: " + otp);
    }

    // ── Main Login Flow ───────────────────────────────────

    public void login(String mobileNumber, String otp) throws InterruptedException {
        allowNotifications();            // handle notification popup if present
        dismissUpdatePopup();            // handle update popup if present
        clickFoodCourt();                // navigate to login screen
        enterMobileNumber(mobileNumber); // type number + dismiss keyboard
        acceptTerms();                   // tick checkbox, wait 2s
        clickGetOTP();                   // click Get OTP, wait 2s
        enterOTP(otp);                   // enter OTP from config
        System.out.println("✅ Login Complete");
    }
}
