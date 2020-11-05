package Allure;

import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LogListener implements ITestListener {
        private ByteArrayOutputStream request = new ByteArrayOutputStream();
        private ByteArrayOutputStream response = new ByteArrayOutputStream();

        private PrintStream requestVar = new PrintStream(request, true);
        private PrintStream responseVar = new PrintStream(response, true);


        public void onStart(ITestContext iTestContext) {
            RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL, responseVar),
                    new RequestLoggingFilter(LogDetail.ALL, requestVar));
        }

        public void onTestSuccess(ITestResult iTestResult) {
            logRequest(request);
            logResponse(response);
        }

        public void onTestFailure(ITestResult iTestResult) {
            onTestSuccess(iTestResult);
        }

        @Attachment(value = "request")
        public byte[] logRequest(ByteArrayOutputStream stream) {
            return attach(stream);
        }

        @Attachment(value = "response")
        public byte[] logResponse(ByteArrayOutputStream stream) {
            return attach(stream);
        }

        @Attachment(value = "page screenshot", type = "image/png")
        public byte[] saveScreenshotPNG(WebDriver driver) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        }

        public byte[] attach(ByteArrayOutputStream log) {
            byte[] array = log.toByteArray();
            log.reset();
            return array;
        }

        public void onTestStart(ITestResult iTestResult) {
        }

        public void onTestSkipped(ITestResult iTestResult) {

        }

        public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

        }

        public void onFinish(ITestContext iTestContext) {

        }
    }