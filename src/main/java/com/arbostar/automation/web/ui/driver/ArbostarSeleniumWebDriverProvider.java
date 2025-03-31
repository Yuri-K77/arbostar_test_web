package com.arbostar.automation.web.ui.driver;

import org.openqa.selenium.remote.RemoteWebDriver;


public class ArbostarSeleniumWebDriverProvider {

    private static final ThreadLocal<WebDriverDelegate<RemoteWebDriver>> webDriverDelegate = ThreadLocal.withInitial(WebDriverDelegate::new);

    public static void setWebDriver(RemoteWebDriver driver) {
        (webDriverDelegate.get()).setDelegate(driver);
    }

    public static RemoteWebDriver getWebDriver() {
        return (RemoteWebDriver) webDriverDelegate.get().getDelegate();
    }
}