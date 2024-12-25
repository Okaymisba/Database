package org.example.database.databaseCreation

import java.io.File

class CreateDatabase(val databaseName: String) {
    fun createDatabase(databaseName: String) {
        val databaseDir = File(System.getProperty("user.dir") + "/Databases/$databaseName")
        if (!databaseDir.exists()) {
            databaseDir.mkdirs()
        }
    }

    fun createTable(tableName: String, keys: List<String>) {
        val tableFile = File(System.getProperty("user.dir") + "/Databases/$databaseName/$tableName")
        if (!tableFile.exists()) {
            tableFile.mkdirs()
        }
    }
}