package com.arbostar.automation.web.ui.screens;

import com.arbostar.automation.web.ui.actions.WebActionManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Абстрактный класс, который является родителем для всех компонентов экрана,
 * внутри конструктора инициализирует объект с действиями над вебэлементами.
 */
public abstract class AbstractWebElement {

    public final WebActionManager webActionManager;
    private final WebElement container;

    /**
     * Данный конструктор используется в случае, когда новый вебэлемент вложен в родительский контейнер
     */
    public AbstractWebElement(WebActionManager webActionManager, WebElement container) {
        this.webActionManager = webActionManager;
        this.container = container;
    }

    /**
     * Данный метод инициализирует контейнер вебэлемента, который вложен в родительский контейнер
     */
    public WebElement getContainer() {
        return container;
    }

    public WebElement customFindElement(By selector) {
        return getContainer().findElement(selector);
    }

    public List<WebElement> customFindElements(By selector) {
        return getContainer().findElements(selector);
    }

    public boolean isElementDisplayed() {
        return webActionManager.isElementDisplayed(getContainer());
    }

    public void waitElementToBeInvisible() {
        webActionManager.waitElementToBeInvisible(getContainer());
    }
}