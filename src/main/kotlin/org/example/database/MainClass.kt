package org.example.database

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import org.example.database.databaseCreation.CreateDatabase
import org.example.database.databaseOperations.InsertIntoTable

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
//    val createDatabase = CreateDatabase()
//    createDatabase.createDatabase("testDatabase29Dec")
//    createDatabase.createTable(
//        tableName = "testTable29DEC",
//        columns = listOf("name", "age"),
//        dataTypes = listOf("string", "int"),
//        isUnique = listOf(false, true),
//        notNull = listOf(true, false)
//    )
//    InsertIntoTable.insert(
//        databaseName = "testDatabase29Dec",
//        tableName = "testTable29DEC",
//        columns = listOf("name", "age"),
//        values = listOf("Misbah", 18)
//    )

}