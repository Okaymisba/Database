package org.example.database.queryParser

import org.example.database.databaseOperations.InsertIntoTable

object ParseQuery {
    fun parse(query: String?) {

        if (query != null) {
            val insertRegex =
                Regex("""INSERT\s*INTO\s*(\w+)\s*\((.*?)\)\s*VALUES\s*\((.*?)\);""", RegexOption.IGNORE_CASE)

            when {
                insertRegex.matches(query) -> {
                    val matchResult = insertRegex.matchEntire(query)!!
                    val tableName = matchResult.groupValues[1]
                    val columns = matchResult.groupValues[2].split(",").map {
                        it.trim()
                    }
                    val column = columns.map { it.toString() }
                    val values = matchResult.groupValues[3].split(",").map {
                        it.trim().removeSurrounding("'").let { value ->
                            if (value.startsWith("\"") && value.endsWith("\"")) {
                                value.removeSurrounding("\"")

                            } else if (value == "true" || value == "false") {
                                value.toBoolean()

                            } else if (value.matches("-?\\d+".toRegex())) {
                                value.toInt()

                            } else if (value.matches("-?\\d*\\.\\d+".toRegex())) {
                                value.toDouble()

                            } else {
                                value

                            }

                        }
                    }

                    InsertIntoTable.insert("testDatabase29Dec", tableName, column, values)
                }
            }
        }
    }
}