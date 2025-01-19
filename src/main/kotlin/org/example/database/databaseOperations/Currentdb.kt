package org.example.database.databaseOperations

import java.io.File

object Currentdb {
    fun get(): String {
        val fileForCurrentDatabase = File("${System.getProperty("user.dir")}/crrtdb/crrtdb.txt")

        if (fileForCurrentDatabase.readText().isEmpty()) {
            throw Exception("No database found")
        }
        return fileForCurrentDatabase.readText()
    }
}