package com.arbostar.automation.web.ui.screens;

import com.arbostar.automation.web.enums.LeftSideNavMenuItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmailTemplatesScreen extends AbstractScreen {

    public EmailTemplatesScreen(WebDriver driver) {
        super(driver);
    }

    public WebElement getContainer() {
        return getDriverFromAbstractScreen().findElement(By.cssSelector("section.panel.panel-default.table-style.table-style-fit-to-screen"));
    }

    @Override
    public EmailTemplatesScreen openScreen() {
        new DashboardScreen(getDriverFromAbstractScreen()).openScreen();
        new LeftSideNavigationMenu(getDriverFromAbstractScreen()).selectLeftSideNavMenuItem(LeftSideNavMenuItems.CLIENTS);
        getDriverFromAbstractScreen().get("https://staging.arbostar.com/clients/letters");
        return waitScreenOpen();
    }

    @Override
    public EmailTemplatesScreen waitScreenOpen() {
        webActionManager.waitGetVisibleElement(getContainer());
        return this;
    }

    @Override
    public boolean isScreenOpen() {
        return webActionManager.isElementDisplayed(getContainer());
    }
}