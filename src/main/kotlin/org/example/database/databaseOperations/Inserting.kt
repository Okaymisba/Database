package org.example.database.databaseOperations

import org.example.database.databaseCreation.currentDatabase
import java.io.File
import org.json.JSONObject

object InsertIntoTable {
    fun insert(
        databaseName: String? = Currentdb.get(),
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
            val columnFile = File(tableDirectory.plus("/Columns/$column.json"))
            val contentsOfColumnFile = columnFile.readText()
            val jsonObjectOfColumn =
                if (contentsOfColumnFile.isNotEmpty()) JSONObject(contentsOfColumnFile) else JSONObject()
            val jsonObjectOfColumnInfo = jsonObjectOfTableInfo.getJSONObject(column)
            val dataType = jsonObjectOfColumnInfo.getString("type")
            val isUnique = jsonObjectOfColumnInfo.getBoolean("isUnique")
            val notNull = jsonObjectOfColumnInfo.getBoolean("notNull")
            val columnValuesInDatabase = jsonObjectOfColumn.getJSONArray(column)
            val columnIndex = columns.indexOf(column)

            if (columnIndex == -1) {
                if (notNull) {
                    throw Exception("Value cannot be null")
                } else {
                    columnValuesInDatabase.put(JSONObject.NULL)
                    jsonObjectOfColumn.put(column, columnValuesInDatabase)

                    columnFile.writeText(jsonObjectOfColumn.toString())
                }

                // TODO: Handling of null values in columns

            } else if (dataType == "string") {
                if (values[columnIndex] !is String) {
                    throw Exception("Invalid data type")
                }

            } else if (dataType == "int") {
                if (values[columnIndex] !is Int) {
                    throw Exception("Invalid data type")
                }

            } else if (dataType == "boolean") {
                if (values[columnIndex] !is Boolean) {
                    throw Exception("Invalid data type")
                }

            } else if (dataType == "double") {
                if (values[columnIndex] !is Double) {
                    throw Exception("Invalid data type")
                }

            }

            if (isUnique && values[columnIndex] in columnValuesInDatabase) {
                throw Exception("Value already exists")
            }

        }


        for (column in columnsOfTable) {
            val columnFile = File(tableDirectory.plus("/Columns/$column.json"))
            val contentsOfColumnFile = columnFile.readText()
            val jsonObjectOfColumn =
                if (contentsOfColumnFile.isNotEmpty()) JSONObject(contentsOfColumnFile) else JSONObject()
            val columnValuesInDatabase = jsonObjectOfColumn.getJSONArray(column)
            val columnIndex = columns.indexOf(column)

            if (columnIndex != -1) {
                columnValuesInDatabase.put(values[columnIndex])
                jsonObjectOfColumn.put(column, columnValuesInDatabase)

                columnFile.writeText(jsonObjectOfColumn.toString())
            }
        }
    }
}