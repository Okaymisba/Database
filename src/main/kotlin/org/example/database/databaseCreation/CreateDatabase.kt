package org.example.database.databaseCreation

import java.io.File

class CreateDatabase(val databaseName: String) {
    fun createDatabase(databaseName: String) {
        val databaseDir = File(System.getProperty("user.dir") + "/Databases/$databaseName")
        if (!databaseDir.exists()) {
            databaseDir.mkdirs()
        }
    }

    fun createTable(tableName: String, keys: List<String>, dataTypes: List<String>) {
        val tableDir = File(System.getProperty("user.dir") + "/Databases/$databaseName/$tableName/Columns")
        if (!tableDir.exists()) {
            tableDir.mkdirs()
        }

        for (key in keys) {
            val keyFile = File(System.getProperty("user.dir") + "/Databases/$databaseName/$tableName/Columns/$key.json")
            val keyInfoFile =
                File(System.getProperty("user.dir") + "/Databases/$databaseName/$tableName/Columns/$key-info.json")
            if (!keyFile.exists()) {
                keyFile.createNewFile()
            }
            if (!keyInfoFile.exists()) {
                keyInfoFile.createNewFile()
                keyInfoFile.writeText("{\"type\": \"${dataTypes[keys.indexOf(key)]}\"}")
            }
        }
    }
}