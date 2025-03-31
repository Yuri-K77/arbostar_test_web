package com.arbostar.automation.web.ui.extensions;

import com.arbostar.automation.web.configuration.ArbostarConfig;
import com.arbostar.automation.web.ui.service.BrowserStackClient;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Optional;

public class BrowserStackExtension implements BeforeAllCallback, AfterAllCallback, TestWatcher {

    private Boolean isBrowserStack = false;
    private Boolean isAnyTestFailed;
    public BrowserStackClient sessionClient;

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {

    }

    @Override
    public void testSuccessful(ExtensionContext context) {

    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {

    }

    @SneakyThrows
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        this.isAnyTestFailed = true;
        Object clazz = context.getRequiredTestInstance();
        Field driverField = clazz.getClass().getSuperclass().getDeclaredField("driver");
        driverField.setAccessible(true);
        WebDriver driver = (WebDriver) driverField.get(clazz);

        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File fileDir = new File("target/screenshots");
        File fileAbsolutePath = new File(new File(fileDir,
                context.getRequiredTestClass().getName()
                        + "." +
                        context.getRequiredTestMethod().getName()
                        + "-" +
                        System.currentTimeMillis() + ".png").getAbsolutePath());
        FileUtils.copyFile(file, fileAbsolutePath);
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        if (extensionContext.getExecutionException().isPresent()) {
            this.isAnyTestFailed = true;
        }

        if (this.isBrowserStack && this.isAnyTestFailed) {
            this.sessionClient.updateSessionStatusFailed("At leas one test case failed");
            ArbostarConfig.removeCurrentSessionId();
        } else if (this.isBrowserStack) {
            this.sessionClient.updateSessionStatusPassed("");
            ArbostarConfig.removeCurrentSessionId();
        }
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        this.isBrowserStack = !ArbostarConfig.getRunType().equals("localrun");
        this.isAnyTestFailed = false;
        if (this.isBrowserStack) {
            this.sessionClient = new BrowserStackClient(ArbostarConfig.getCurrentSessionId());
            extensionContext.getTestClass().ifPresent((tc) -> {
                this.sessionClient.updateSessionName(tc.getSimpleName());
            });
        }
    }
}