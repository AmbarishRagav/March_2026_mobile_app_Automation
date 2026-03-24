package utils;

import bases.Base;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    private static final String SCREENSHOTS_DIR =
            "C:\\Users\\ambar\\IdeaProjects\\2026NEW_Moblie_App\\screenshots\\";

    // ── Suite level ───────────────────────────────────────

    @Override
    public void onStart(ITestContext context) {
        System.out.println("🚀 Suite Started: " + context.getName());
        ExtentReportManager.getInstance(); // initialize report
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("🏁 Suite Finished: " + context.getName());
        ExtentReportManager.flushReport(); // save the HTML report
    }

    // ── Test level ────────────────────────────────────────

    @Override
    public void onTestStart(ITestResult result) {
        // Get device info for report
        String device = getDeviceInfo(result);
        ExtentTest extentTest = ExtentReportManager.createTest(
                result.getName() + " [" + device + "]", device);
        extentTest.info("Test Started on: " + device);
        System.out.println("▶️ Test Started: " + result.getName() + " | Device: " + device);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().pass("✅ Test Passed");
        System.out.println("✅ Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstance = result.getInstance();
        String screenshotPath = null;

        if (testInstance instanceof Base) {
            AndroidDriver driver = ((Base) testInstance).driver;
            if (driver != null) {
                screenshotPath = takeScreenshot(driver, result.getName());
            }
        }

        // Log failure to Extent Report with screenshot
        ExtentTest extentTest = ExtentReportManager.getTest();
        extentTest.fail("❌ Test Failed: " + result.getThrowable().getMessage());

        if (screenshotPath != null) {
            try {
                extentTest.fail("Screenshot at failure:",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } catch (Exception e) {
                extentTest.fail("Could not attach screenshot: " + e.getMessage());
            }
        }

        System.out.println("❌ Test Failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().skip("⚠️ Test Skipped: " + result.getThrowable());
        System.out.println("⚠️ Test Skipped: " + result.getName());
    }

    // ── Helpers ───────────────────────────────────────────

    private String getDeviceInfo(ITestResult result) {
        Object testInstance = result.getInstance();
        if (testInstance instanceof Base) {
            Base base = (Base) testInstance;
            return base.targetDevice != null ? base.targetDevice : "unknown";
        }
        return "unknown";
    }

    private String takeScreenshot(AndroidDriver driver, String testName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File dir = new File(SCREENSHOTS_DIR);
            if (!dir.exists()) dir.mkdirs();

            String destPath = SCREENSHOTS_DIR + testName + "_" + timestamp + ".png";
            FileUtils.copyFile(srcFile, new File(destPath));
            System.out.println("📸 Screenshot saved: " + destPath);
            return destPath;
        } catch (IOException e) {
            System.err.println("❌ Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
