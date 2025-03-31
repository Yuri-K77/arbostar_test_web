package com.arbostar.automation.web.ui.extensions;

import com.arbostar.automation.web.ui.driver.DriverProvider;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;


public class LocalWebDriverExtension implements BeforeAllCallback, AfterAllCallback {

    private final DriverProvider driverProvider = new DriverProvider();

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        driverProvider.setupDriver();
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        driverProvider.quitDriver();
    }
}