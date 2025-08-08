package com.arbostar.automation.web.ui;

import com.arbostar.automation.web.ui.screens.NewslettersScreen;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NewslettersScreenTest extends BaseUiTest {

    private NewslettersScreen newslettersScreen;

    @BeforeAll
    void beforeAll() {
        newslettersScreen = new NewslettersScreen(driver);
    }

    @DisplayName("<Newsletters> screen should be displayed after opening")
    @Order(1)
    @Test
    void openNewslettersScreen() {
        newslettersScreen.openScreen();
        Assertions.assertTrue(newslettersScreen.isScreenOpen());
    }
}