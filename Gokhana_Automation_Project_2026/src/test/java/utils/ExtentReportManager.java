package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Report saved to project_root/reports/
    private static final String REPORT_PATH =
            "C:\\Users\\ambar\\IdeaProjects\\2026NEW_Moblie_App\\reports\\";

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportFile = REPORT_PATH + "GoKhana_Report_" + timestamp + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportFile);
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("GoKhana Mobile Test Report");
            spark.config().setReportName("GoKhana Automation Results");
            spark.config().setTimeStampFormat("dd MMM yyyy HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Project", "GoKhana Mobile App");
            extent.setSystemInfo("Framework", "Appium + TestNG");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));

            System.out.println("📊 Extent Report will be saved at: " + reportFile);
        }
        return extent;
    }

    // Create a test entry — call at start of each @Test
    public static synchronized ExtentTest createTest(String testName, String device) {
        ExtentTest extentTest = getInstance().createTest(testName)
                .assignDevice(device);
        test.set(extentTest);
        return extentTest;
    }

    // Get current test — use inside @Test methods
    public static ExtentTest getTest() {
        return test.get();
    }

    // Flush report — call once after all tests
    public static synchronized void flushReport() {
        if (extent != null) {
            extent.flush();
            System.out.println("📊 Extent Report saved successfully");
        }
    }
}
