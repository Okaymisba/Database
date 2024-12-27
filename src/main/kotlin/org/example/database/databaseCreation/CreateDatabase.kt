package org.example.database.databaseCreation

import java.io.File

var currentDatabase: String = ""

class CreateDatabase() {
    fun createDatabase(databaseName: String) {
        val databaseDir = File(System.getProperty("user.dir") + "/Databases/$databaseName")
        if (!databaseDir.exists()) {
            databaseDir.mkdirs()
            currentDatabase = databaseName
        } else if (databaseDir.exists()) {
            throw Exception("Database already exists")
        }
    }

    fun createTable(
        tableName: String,
        keys: List<String>,
        dataTypes: List<String>,
        isUnique: List<Boolean>,
        notNull: List<Boolean>
    ) {
        if (keys.size != dataTypes.size) {
            throw Exception("Must initialize Data Type for every Column")
        }
        val tableDir = File(System.getProperty("user.dir") + "/Databases/$currentDatabase/$tableName/Columns")
        if (!tableDir.exists()) {
            tableDir.mkdirs()
        }

        for (key in keys) {
            val keyFile =
                File(System.getProperty("user.dir") + "/Databases/$currentDatabase/$tableName/Columns/$key.json")
            val keyInfoFile =
                File(System.getProperty("user.dir") + "/Databases/$currentDatabase/$tableName/Columns/$key-info.json")
            if (!keyFile.exists()) {
                keyFile.createNewFile()
            }
            if (!keyInfoFile.exists()) {
                keyInfoFile.createNewFile()
                keyInfoFile.writeText(
                    "{\"type\": \"${dataTypes[keys.indexOf(key)]}\", " +
                            "\"isUnique\": ${isUnique[keys.indexOf(key)]}, " +
                            "\"notNull\": ${notNull[keys.indexOf(key)]}"
                )
            }
        }
    }
}