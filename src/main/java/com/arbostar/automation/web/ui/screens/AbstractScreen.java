package com.arbostar.automation.web.ui.screens;

import com.arbostar.automation.web.ui.actions.WebActionManager;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

/**
 * Класс, описывающий основные методы для экранов. Класс, хранит внутри себя объект
 *
 * @see AbstractScreen#webActionManager
 * который описывает действия над вебэлементами экрана.
 * <p>
 * AbstractScreen(initialization webActionManager) --> TaskScreen(get access to webActionManager) --> webElements(get access to webActionManager) <-- AbstractWebElement(receive from constructor webActionManager)
 */
public abstract class AbstractScreen {

    public final WebActionManager webActionManager;

    public AbstractScreen(WebDriver driver) {
        this.webActionManager = new WebActionManager(driver);
    }

    public WebDriver getDriverFromAbstractScreen() {
        return webActionManager.getDriver();
    }

    public AbstractScreen openScreen() {
        return this;
    }

    public AbstractScreen waitScreenOpen() {
        return this;
    }

    public boolean isScreenOpen() {
        return false;
    }

    public final boolean waitIsScreenOpen() {
        try {
            waitScreenOpen();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}