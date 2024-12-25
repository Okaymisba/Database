package org.example.database

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import kotlinx.serialization.Serializable

class MainClass : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(MainClass::class.java.getResource("Main-Page.fxml"))
        val scene = Scene(fxmlLoader.load(), 320.0, 240.0)
        stage.title = "Hello!"
        stage.scene = scene
        stage.show()
    }
}

@Serializable
data class TestJson(val name: String, val age: Int)

fun main() {
    Application.launch(MainClass::class.java)
//    val createDatabase = CreateDatabase()
}