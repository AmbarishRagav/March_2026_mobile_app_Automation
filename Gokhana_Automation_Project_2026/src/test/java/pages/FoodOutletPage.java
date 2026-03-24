package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FoodOutletPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public FoodOutletPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void navigateToFoodOption() {
        waitForElement(By.xpath("//android.view.View[contains(@content-desc, 'Food Courts') and contains(@content-desc, 'Browse across food courts and enjoy benefits')]")).click();
        waitForElement(By.xpath("//android.view.View[contains(@content-desc, 'GoKhana Food Court')]")).click();
        waitForElement(By.xpath("//android.view.View[contains(@content-desc, 'GK Bites')]")).click();
    }

    // ── Modify Quantity ───────────────────────────────────

    public void modifyQuantity(String action, int times) {
        navigateToFoodOption();

        By buttonLocator;
        if (action.equalsIgnoreCase("increase")) {
            buttonLocator = By.xpath("(//android.view.View[contains(@content-desc, 'Add')])[1]");
        } else if (action.equalsIgnoreCase("decrease")) {
            buttonLocator = By.xpath("//android.view.View[@content-desc='Remove One Quantity From Cart']");
        } else {
            throw new IllegalArgumentException("Invalid action: " + action + ". Use 'increase' or 'decrease'.");
        }

        for (int i = 0; i < times; i++) {
            waitForElement(buttonLocator).click();
        }
        System.out.println("✅ Quantity " + action + "d " + times + " time(s)");
    }

    // ── Counter Page ──────────────────────────────────────

    public void increaseAndDecreaseFromCounterPage() {
        navigateToFoodOption();
        waitForElement(By.xpath("//android.widget.ImageView[contains(@content-desc, 'View Cart')]")).click();
        waitForElement(By.xpath("(//android.view.View[contains(@content-desc, 'Add')])[1]")).click();
        System.out.println("✅ Quantity modified from counter page");
    }

    // ── View Cart ─────────────────────────────────────────

    public void validateViewCart() {
        navigateToFoodOption();
        waitForElement(By.xpath("(//android.view.View[contains(@content-desc, 'Add')])[1]")).click();
        waitForElement(By.xpath("//android.widget.ImageView[contains(@content-desc, 'View Cart')]")).click();
        System.out.println("✅ View Cart opened");
    }
}
