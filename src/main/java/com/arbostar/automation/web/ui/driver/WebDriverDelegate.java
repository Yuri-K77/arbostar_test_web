package com.arbostar.automation.web.ui.driver;

import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverDelegate<T> extends RemoteWebDriver {

    private T delegate;

    public T getDelegate() {
        return delegate;
    }

    public void setDelegate(T delegate) {
        this.delegate = delegate;
    }
}