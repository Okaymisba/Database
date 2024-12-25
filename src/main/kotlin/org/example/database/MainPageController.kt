package org.example.database

import javafx.fxml.FXML
import javafx.scene.control.Label

class MainPageController {
    @FXML
    private lateinit var welcomeText: Label

    @FXML
    private fun onHelloButtonClick() {
        welcomeText.text = "Welcome to JavaFX Application!"
    }
}