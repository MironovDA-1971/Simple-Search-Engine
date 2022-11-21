package search

import java.io.File
import java.util.NoSuchElementException

data class PrintMessage(var maxNumber: Int = 0) {
    //val enterNumberOfPeople = "Enter the number of people:"
    //val enterAllPeople = "Enter all people:"

    val enterDataToSearchPeople = "\nEnter a name or email to search all suitable people."
    val peopleFound = "People found:"
    val personFound = "person found:"
    val noMatchingPeopleFound = "No matching people found."
    val listOfPeople = "\n=== List of people ==="

    val menuInputError = "Incorrect option! Try again."
    val menuItemList = listOf("\n=== Menu ===",
                              "Exit",
                              "Find a person",
                              "Print all people"
                             )
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

fun indexSearch(indexMap: MutableMap<String, MutableList<Int>>, listPerson: List<String>, message: PrintMessage) {
    println(message.enterDataToSearchPeople)
    val searchKey = readln().trim().lowercase()
    try {
        val indexNum = indexMap.filterKeys { it.lowercase() == searchKey }.getValue(searchKey)
        println("${indexNum.size} ${message.personFound}")
        for (i in indexNum.indices) println(listPerson[indexNum[i]])
    } catch (e: NoSuchElementException) {
        println(message.noMatchingPeopleFound)
    }
}

fun printPerson(listPerson: List<String>, message: PrintMessage) {
    println(message.listOfPeople)
    //for (i in listPerson.indices) println(listPerson[i])
    listPerson.forEach { println(it) }
}

fun searchQuery(listPerson: List<String>, message: PrintMessage) {
    println(message.enterDataToSearchPeople)
    val searchKey = readln().trim().lowercase()
    var flag = 0
    for (i in listPerson.indices) {
        if (listPerson[i].lowercase().contains(searchKey)) {
            if (flag == 0) {
                println(message.peopleFound)
                flag = 1
            }
            println(listPerson[i])
        }
    }
    if (flag == 0) println(message.noMatchingPeopleFound)
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
