package org.example.database.databaseCreation

import org.json.JSONObject
import java.io.File

var currentDatabase: String? = null

class CreateDatabase() {

    // This will create a Database Directory
    fun createDatabase(databaseName: String) {
        val databaseDir = File(System.getProperty("user.dir") + "/Databases/$databaseName")
        if (!databaseDir.exists()) {
            databaseDir.mkdirs()
            currentDatabase = databaseName
        } else if (databaseDir.exists()) {
            throw Exception("Database already exists")
        }
    }

    // This will create a Directory with the Table name and then a Column directory then all the Columns.json files and
    // also write the properties of each Column in the Table-info.json file
    fun createTable(
        tableName: String,
        columns: List<String>,
        dataTypes: List<String>,
        isUnique: List<Boolean>,
        notNull: List<Boolean>
    ) {
        if (columns.size != dataTypes.size) {
            throw Exception("Must initialize Data Type for every Column")
        }
        val tableDir = File(System.getProperty("user.dir") + "/Databases/$currentDatabase/$tableName/Columns")
        val tableInfoFile =
            File(System.getProperty("user.dir") + "/Databases/$currentDatabase/$tableName/Table-info.json")
        val jsonObjectOfColumnInfo = JSONObject()

        if (!tableDir.exists()) {
            tableDir.mkdirs()
        }

        if (!tableInfoFile.exists()) {
            tableInfoFile.createNewFile()
        }

        for (column in columns) {
            val columnFile =
                File(System.getProperty("user.dir") + "/Databases/$currentDatabase/$tableName/Columns/$column.json")

            if (!columnFile.exists()) {
                columnFile.createNewFile()
                columnFile.writeText("{\"$column\": []}")
            }

            val data = "{\"type\": \"${dataTypes[columns.indexOf(column)]}\", " +
                    "\"isUnique\": ${isUnique[columns.indexOf(column)]}, " +
                    "\"notNull\": ${notNull[columns.indexOf(column)]}}"

            val jsonObjectOfData = JSONObject(data)
            jsonObjectOfColumnInfo.put(column, jsonObjectOfData)
            tableInfoFile.writeText(jsonObjectOfColumnInfo.toString())
        }
    }
}