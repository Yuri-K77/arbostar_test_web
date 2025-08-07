package com.arbostar.automation.web.ui;

import com.arbostar.automation.web.ui.screens.ProgrammedMessagesScreen;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProgrammedMessagesScreenTest extends BaseUiTest {

    private ProgrammedMessagesScreen programmedMessagesScreen;

    @BeforeAll
    void beforeAll() {
        programmedMessagesScreen = new ProgrammedMessagesScreen(driver);
    }

    @DisplayName("<Programmed Messages> screen should be displayed after opening")
    @Order(1)
    @Test
    void openProgrammedMessagesScreen() {
        programmedMessagesScreen.openScreen();
        Assertions.assertTrue(programmedMessagesScreen.isScreenOpen());
    }
}