package com.arbostar.automation.web.ui;

import com.arbostar.automation.web.ui.screens.EmailTemplatesScreen;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmailTemplatesScreenTest extends  BaseUiTest {

    private EmailTemplatesScreen emailTemplatesScreen;

    @BeforeAll
    void beforeAll() {
        emailTemplatesScreen = new EmailTemplatesScreen(driver);
    }

    @DisplayName("<Email Templates> screen should be displayed after opening")
    @Order(1)
    @Test
    void openEmailTemplatesScreen() {
        emailTemplatesScreen.openScreen();
        Assertions.assertTrue(emailTemplatesScreen.isScreenOpen());
    }
}