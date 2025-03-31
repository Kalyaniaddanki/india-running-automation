package com.qa.pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import java.lang.reflect.Method;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {
    protected WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        // Generate timestamp for the report name
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportName = "IndiaRunningReport_" + timestamp + ".html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/" + reportName);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("India Running Automation Report");
        sparkReporter.config().setReportName("Fit Page Test Execution");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Addanki Kalyani");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Report Generated On", timestamp); // Add timestamp to report
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush(); // Ensures the report is written to the file
    }

    @BeforeMethod
    @Parameters("browser")
    public void launchBrowser(String browser, Method method) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported");
        }

        driver.get("https://indiarunning-organizer-dashboard-staging.bombayrunning.com/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Create a test in the report with the test method name
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("Test Failed: " + result.getName());
            try {
                String screenshotPath = takeScreenshot(result.getName());
                test.addScreenCaptureFromPath(screenshotPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test Skipped: " + result.getName());
        }

        driver.quit();
    }

    public String takeScreenshot(String testName) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String destDir = "screenshots";
        String fileName = testName + "_" + timestamp + ".png";

        // Ensure directory exists
        File dir = new File(destDir);
        if (!dir.exists()) {
            dir.mkdirs(); // Create the folder (and parent folders if necessary)
        }

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(dir, fileName);
        FileHandler.copy(srcFile, destFile);

        return destFile.getAbsolutePath(); // Return full path for reporting tools like ExtentReports
    }

}
