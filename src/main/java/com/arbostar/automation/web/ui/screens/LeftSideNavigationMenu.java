package com.arbostar.automation.web.ui.screens;

import com.arbostar.automation.web.enums.MainLeftNavBarItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class LeftSideNavigationMenu extends AbstractScreen {

    public LeftSideNavigationMenu(WebDriver driver) {
        super(driver);
    }

    public WebElement getContainer() {
        return getDriverFromAbstractScreen().findElement(By.cssSelector("aside#nav"));
    }

    private List<WebElement> getMainLeftNavBarItems() {
        return getContainer().findElements(By.cssSelector("nav.nav-primary>ul.nav>li.nav-block>a span.nav-name"));
    }

    public WebElement getMainLeftNavBarItem(MainLeftNavBarItems item) {
        return getMainLeftNavBarItems()
                .stream()
                .filter(element -> element.getText().equals(item.getValue()))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Navigation bar item \"" + item.getValue() + "\" is not displayed"));
    }

    public void selectMainLeftNavBarItem(MainLeftNavBarItems item) {
        webActionManager.clickOnElementWithPollingInterval(getMainLeftNavBarItem(item));
    }

    public boolean isLeftSideNavMenuExpanded() {
        return !getContainer().getAttribute("class").contains("nav-xs");
    }
}