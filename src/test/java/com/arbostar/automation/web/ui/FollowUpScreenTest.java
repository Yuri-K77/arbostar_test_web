package com.arbostar.automation.web.ui;

import com.arbostar.automation.web.ui.screens.FollowUpScreen;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FollowUpScreenTest extends BaseUiTest {

    private FollowUpScreen followUpScreen;

    @BeforeAll
    void beforeAll() {
        followUpScreen = new FollowUpScreen(driver);
    }

    @DisplayName("<Follow Up> screen should be displayed after opening")
    @Order(1)
    @Test
    void openFollowUpScreen() {
        followUpScreen.openScreen();
        Assertions.assertTrue(followUpScreen.isScreenOpen());
    }
}