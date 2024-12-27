package org.example.database

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import org.example.database.databaseCreation.CreateDatabase

class MainClass : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(MainClass::class.java.getResource("Main-Page.fxml"))
        val scene = Scene(fxmlLoader.load(), 320.0, 240.0)
        stage.title = "Hello!"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
//    Application.launch(MainClass::class.java)
    val createDatabase = CreateDatabase()
//    createDatabase.createDatabase("testDatabase")
//    createDatabase.createTable(
//        tableName = "testTable",
//        columns = listOf("name", "age"),
//        dataTypes = listOf("string", "int"),
//        isUnique = listOf(false, true),
//        notNull = listOf(true, false)
//    )
}