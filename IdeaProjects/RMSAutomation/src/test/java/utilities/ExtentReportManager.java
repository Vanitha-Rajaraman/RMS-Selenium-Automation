package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    private String reportName;
    private Properties prop;

    private void loadConfig() {
        prop = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart(ITestContext testContext) {
        loadConfig();

        String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
        reportName = "Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName);
        sparkReporter.config().setDocumentTitle("RMS Automation Report");
        sparkReporter.config().setReportName("RMS Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Web_RMS");
        extent.setSystemInfo("Module", "End-To-End Testing");
        extent.setSystemInfo("Sub Module", "Regression & Smoke Testing");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "Stage Testing");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.PASS, "Test Passed");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());

        try {
            String imgPath = BaseClass.captureScreen(result.getName());
            // Only attach screenshot if path is not null and file exists
            if (imgPath != null && new File(imgPath).exists()) {
                test.addScreenCaptureFromPath(imgPath);
            }
        } catch (Exception e) {
            System.err.println("Error while attaching screenshot to report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable() != null ? result.getThrowable().getMessage() : "No exception");
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();
        //sendEmailWithAttachment(testContext.getName());
    }

//    private void sendEmailWithAttachment(String testName) {
//        if (prop.getProperty("email.username") == null || prop.getProperty("email.password") == null ||
//                prop.getProperty("email.password").isEmpty()) {
//            System.out.println("Email credentials not found or incomplete in config.properties. Skipping email sending.");
//            return;
//        }
//
//        try {
//            EmailAttachment attachment = new EmailAttachment();
//            attachment.setPath(generateReportPath());
//            attachment.setDisposition(EmailAttachment.ATTACHMENT);
//            attachment.setDescription("Test Report");
//
//            MultiPartEmail email = new MultiPartEmail();
//            email.setHostName("smtp.gmail.com");
//            email.setSmtpPort(465);
//            email.setAuthenticator(new DefaultAuthenticator(prop.getProperty("email.username"),
//                    prop.getProperty("email.password")));
//            email.setSSLOnConnect(true);
//            email.addTo(prop.getProperty("email.username"));
//            email.setFrom(prop.getProperty("email.username"));
//            email.setSubject("Test Automation Report - " + testName);
//            email.setMsg("Please find attached the test automation report for: " + testName);
//            email.attach(attachment);
//            email.send();
//            System.out.println("Email report sent successfully.");
//        } catch (Exception e) {
//            System.out.println("Failed to send email report.");
//            e.printStackTrace();
//        }
//    }
//
//    private String generateReportPath() {
//        return System.getProperty("user.dir") + File.separator + "reports" + File.separator + reportName;
//    }
}
