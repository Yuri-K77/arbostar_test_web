package com.arbostar.automation.web.ui;

import com.arbostar.automation.web.configuration.ArbostarConfig;
import com.arbostar.automation.web.ui.driver.ArbostarSeleniumWebDriverProvider;
import com.arbostar.automation.web.ui.extensions.BrowserStackExtension;
import com.arbostar.automation.web.ui.extensions.LocalWebDriverExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@Tag("ui")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(LocalWebDriverExtension.class)
@ExtendWith(BrowserStackExtension.class)
public abstract class BaseUiTest {

    protected WebDriver driver;

    @BeforeAll
    public void setUp() {
        driver = ArbostarSeleniumWebDriverProvider.getWebDriver();
        driver.get(ArbostarConfig.getBaseUrl());
    }
}