package bases;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.LoginPage;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Base {

    public AndroidDriver driver;
    public Properties config;
    public String targetDevice; // physical or emulator
    private AppiumDriverLocalService appiumService;

    @Parameters("targetDevice")
    @BeforeClass
    public void setUp(@Optional("") String targetDeviceParam) throws Exception {

        // Load config.properties
        config = new Properties();
        FileInputStream fis = new FileInputStream(
                "src/test/resources/config.properties"
        );
        config.load(fis);

        // Use TestNG parameter if provided (parallel runs), else fall back to config
        targetDevice = (targetDeviceParam != null && !targetDeviceParam.isEmpty())
                ? targetDeviceParam
                : config.getProperty("targetDevice", "emulator");

        String deviceName     = config.getProperty(targetDevice + ".deviceName");
        String udid           = config.getProperty(targetDevice + ".udid");
        String platformVersion = config.getProperty(targetDevice + ".platformVersion");

        System.out.println("🎯 Running on: " + targetDevice + " | Device: " + deviceName + " | UDID: " + udid);

        // Use different Appium ports for parallel runs to avoid conflicts
        int appiumPort = targetDevice.equals("physical") ? 4723 : 4724;

        appiumService = new AppiumServiceBuilder()
                .withAppiumJS(new File(config.getProperty("appiumJSPath")))
                .withIPAddress("127.0.0.1")
                .usingPort(appiumPort)
                .build();
        appiumService.start();
        System.out.println("✅ Appium Server Started at port " + appiumPort + " for: " + targetDevice);

        // Appium Options
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setUdid(udid);
        options.setPlatformName("Android");
        options.setPlatformVersion(platformVersion);
        options.setAppPackage(config.getProperty("appPackage"));
        options.setAppActivity(config.getProperty("appActivity"));
        options.setNoReset(false); // install fresh preprod APK every run
        options.setCapability("newCommandTimeout", 120);
        options.setCapability("disableIdLocatorAutocompletion", true);

        // Install preprod APK
        File apk = new File(config.getProperty("apkPath"));
        if (apk.exists()) {
            options.setApp(apk.getAbsolutePath());
            System.out.println("✅ Using APK: " + apk.getAbsolutePath());
        } else {
            System.out.println("⚠️ APK not found at: " + apk.getAbsolutePath() + " — launching existing app");
            options.setNoReset(true);
        }

        driver = new AndroidDriver(appiumService.getUrl(), options);
        System.out.println("✅ Driver Started Successfully");

        // Verify app is installed
        boolean installed = driver.isAppInstalled(config.getProperty("appPackage"));
        if (installed) {
            System.out.println("✅ App is installed: " + config.getProperty("appPackage"));
        } else {
            System.out.println("❌ App not found: " + config.getProperty("appPackage"));
        }

        // Login once before any test class runs
        LoginPage loginPage = new LoginPage(driver);
        try {
            loginPage.login(
                    config.getProperty("mobileNumber"),
                    config.getProperty("otp")
            );
            System.out.println("✅ Login Completed in Base Setup");
        } catch (Exception e) {
            takeLoginFailureScreenshot();
            throw new RuntimeException(e);
        }
    }

    private void takeLoginFailureScreenshot() {
        try {
            String screenshotsDir = "C:\\Users\\ambar\\IdeaProjects\\2026NEW_Moblie_App\\screenshots";
            File dir = new File(screenshotsDir);
            if (!dir.exists()) dir.mkdirs();

            String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String destPath = screenshotsDir + "\\LOGIN_FAILED_" + timestamp + ".png";
            FileUtils.copyFile(srcFile, new File(destPath));
            System.out.println("📸 Login failure screenshot saved: " + destPath);
        } catch (Exception ex) {
            System.out.println("⚠️ Could not take login failure screenshot: " + ex.getMessage());
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
                System.out.println("✅ Driver Closed Successfully");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error while closing driver: " + e.getMessage());
        }
        try {
            if (appiumService != null && appiumService.isRunning()) {
                appiumService.stop();
                System.out.println("✅ Appium Server Stopped");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error while stopping Appium: " + e.getMessage());
        }
    }
}