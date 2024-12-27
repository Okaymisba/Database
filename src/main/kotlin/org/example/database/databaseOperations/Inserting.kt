package org.example.database.databaseOperations

import org.example.database.databaseCreation.currentDatabase
import java.io.File
import org.json.JSONObject

object InsertIntoTable {
    fun insert(
        databaseName: String? = currentDatabase,
        tableName: String,
        columns: List<String>,
        values: List<Any>
    ) {
        val tableDirectory = System.getProperty("user.dir") + "/Databases/$databaseName/$tableName"

        if (!File(tableDirectory).exists()) {
            throw Exception("Table doesn't exist")
        }

        try {
            File(tableDirectory.plus("/Table-info.json")).readText()
        } catch (e: Exception) {
            throw Exception("Table doesn't exist")
        }

        val tableInfoFile = File(tableDirectory.plus("/Table-info.json")).readText()
        val jsonObjectOfTableInfo = JSONObject(tableInfoFile)
        val columnsOfTable = jsonObjectOfTableInfo.keys().asSequence().toList()

        if (columns.size != values.size) {
            throw Exception("Must initialize value for every column")
        }

        if (columns.size > columnsOfTable.size) {
            throw Exception("Too many columns")
        }

        for (column in columns) {
            if (!columnsOfTable.contains(column)) {
                throw Exception("Column doesn't exist")
            }
        }

        for (column in columnsOfTable) {
            val columnFile = File(tableDirectory.plus("/Columns/$column.json")).readText()
            val jsonObjectOfColumn = JSONObject(columnFile)
            val dataType = jsonObjectOfColumn.getString("type")
            val isUnique = jsonObjectOfColumn.getBoolean("isUnique")
            val notNull = jsonObjectOfColumn.getBoolean("notNull")

            if (dataType == "string") {
                if (values[columns.indexOf(column)] !is String) {
                    throw Exception("Invalid data type")
                }

            } else if (dataType == "int") {
                if (values[columns.indexOf(column)] !is Int) {
                    throw Exception("Invalid data type")
                }

            } else if (dataType == "float") {
                if (values[columns.indexOf(column)] !is Float) {
                    throw Exception("Invalid data type")
                }

            } else if (dataType == "boolean") {
                if (values[columns.indexOf(column)] !is Boolean) {
                    throw Exception("Invalid data type")
                }

            } else if (dataType == "double") {
                if (values[columns.indexOf(column)] !is Double) {
                    throw Exception("Invalid data type")
                }

            }

        }
    }
}