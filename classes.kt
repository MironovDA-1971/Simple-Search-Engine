package search

import java.io.File
import java.util.NoSuchElementException

data class PrintMessage(var maxNumber: Int = 0) {
    val enterDataToSearchPeople = "\nEnter a name or email to search all suitable people."
    val personFound = "person found:"
    val noMatchingPeopleFound = "No matching people found."
    val listOfPeople = "\n=== List of people ==="

    val menuInputError = "Incorrect option! Try again."
    val menuItemList = listOf("\n=== Menu ===",
                              "Exit",
                              "Find a person",
                              "Print all people"
                             )
    val selectStrategy = "Select a matching strategy: ALL, ANY, NONE"
}

enum class Strategy(val select: String) {
    ALL("ALL"),
    ANY("ANY"),
    NONE("NONE"),
    NULL("");
}

class UserMenu {
    private val menu = PrintMessage()

    private fun printMenu(): IntRange {
        val range = menu.menuItemList.indices
        for (i in range){
            println(if(i == 0) menu.menuItemList[i] else "${i-1}. ${menu.menuItemList[i]}")
        }
        return range
    }

    fun outMenu(): Int {
        val range = printMenu()
        val num = readln().toInt()
        return if (num in range) num
        else -1
    }

    fun selectStrategy(message: PrintMessage): Strategy {
        while (true) {
            println(message.selectStrategy)
            val select = readln().uppercase()
            try {
                return  Strategy.valueOf(select)
            } catch (e: IllegalArgumentException) {
                println("Illegal operator!")
            }
        }
    }
}

fun getIndexKey(listPerson: List<String>): MutableMap<String, MutableList<Int>> {
    val indexMap = emptyMap<String, MutableList<Int>>().toMutableMap()

    for (i in listPerson.indices) {
        val tmp = listPerson[i].lowercase().split(" ")
        for (a in tmp.indices) {
            if (indexMap.putIfAbsent(tmp[a], mutableListOf(i)) != null) {
                indexMap[tmp[a]]?.add(i)
            } else indexMap[tmp[a]] = MutableList(1){i}
        }
    }
    return indexMap
}

fun indexSearch(
    indexMap: MutableMap<String, MutableList<Int>>,
    listPerson: List<String>,
    message: PrintMessage
) {
    try {
        val selStr = UserMenu().selectStrategy(message)
        println(message.enterDataToSearchPeople)
        val searchKey = readln().trim().lowercase().split(" ")
        var indexNum = emptyList<Int>()
        //
        for (stringKey in searchKey) {
            val indexStr = indexMap.filterKeys { it.lowercase() == stringKey }.getValue(stringKey)
            when(selStr) {
                Strategy.ANY -> indexNum = (indexNum union indexStr).toMutableList()
                Strategy.ALL -> indexNum = if (indexNum.isEmpty()) indexStr
                                           else (indexStr intersect indexNum.toSet()).toMutableList()
                Strategy.NONE -> indexNum = (listPerson.indices.toSet() subtract indexStr.toSet()).toList()
                else -> {}
            }
        }


        println("${indexNum.size} ${message.personFound}")
        indexNum.forEach { println(listPerson[it]) }
    } catch (e: NoSuchElementException) {
        println(message.noMatchingPeopleFound)
    }
}

fun printPerson(listPerson: List<String>, message: PrintMessage) {
    println(message.listOfPeople)
    listPerson.forEach { println(it) }
}

fun readFile(fileName: String): List<String> {
    val userDir = System.getProperty ("user.dir") + File.separator
    return try {
        File("$userDir$fileName").readLines()
    } catch (e: Exception) {
        println("Error! File not found.")
        listOf("Error")
    }
}
