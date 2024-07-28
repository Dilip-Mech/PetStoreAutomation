package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {

    private static ExtentReports extent;
    ExtentSparkReporter sparkReporter;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    String repName;

    @Override
    public void onStart(ITestContext context) {
        if (extent == null) {
        	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.SS").format(new Date());
    		repName ="Test-Repot-"+timeStamp+".html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
            
            sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // Ttile of report
    		sparkReporter.config().setReportName("Pet Store Users API");
    		sparkReporter.config().setTheme(Theme.DARK);
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
    		extent.setSystemInfo("Application", "Pet Store Users API");
    		extent.setSystemInfo("Opetating System", System.getProperty("os.name"));
    		extent.setSystemInfo("User Name", System.getProperty("user.name"));
    		extent.setSystemInfo("Environment", "QA");
    		extent.setSystemInfo("user", "Dilip");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        if (extent != null) {
            ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
            test.set(extentTest);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (test.get() != null) {
            test.get().pass("Test passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (test.get() != null) {
            test.get().fail(result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (test.get() != null) {
            test.get().skip("Test skipped");
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not implemented
    }
}
