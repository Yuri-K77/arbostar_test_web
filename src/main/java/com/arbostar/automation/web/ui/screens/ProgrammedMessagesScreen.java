package com.arbostar.automation.web.ui.screens;

import com.arbostar.automation.web.enums.LeftSideNavMenuItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProgrammedMessagesScreen extends AbstractScreen {

    public ProgrammedMessagesScreen(WebDriver driver) {
        super(driver);
    }

    public WebElement getContainer() {
        return getDriverFromAbstractScreen().findElement(By.cssSelector("div.table-style.table-style-fit-to-screen"));
    }

    @Override
    public ProgrammedMessagesScreen openScreen() {
        new DashboardScreen(getDriverFromAbstractScreen()).openScreen();
        new LeftSideNavigationMenu(getDriverFromAbstractScreen()).selectLeftSideNavMenuItem(LeftSideNavMenuItems.CLIENTS);
        getDriverFromAbstractScreen().get("https://staging.arbostar.com/administration/followup");
        return waitScreenOpen();
    }

    @Override
    public ProgrammedMessagesScreen waitScreenOpen() {
        webActionManager.waitGetVisibleElement(getContainer());
        return this;
    }

    @Override
    public boolean isScreenOpen() {
        return webActionManager.isElementDisplayed(getContainer());
    }
}