package com.arbostar.automation.web.ui;

import com.arbostar.automation.web.ui.screens.ClientsListScreen;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientsListScreenTest extends BaseUiTest {

    private ClientsListScreen clientsListScreen;

    @BeforeAll
    void beforeAll() {
        clientsListScreen = new ClientsListScreen(driver);
    }

    @DisplayName("<Clients list> screen should be displayed after opening")
    @Order(1)
    @Test
    void openClientsListScreen() {
        clientsListScreen.openScreen();
        Assertions.assertTrue(clientsListScreen.isScreenOpen());
    }
}