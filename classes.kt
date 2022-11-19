package search

import java.io.File

data class PrintMessage(var maxNumber: Int = 0) {
    //val enterNumberOfPeople = "Enter the number of people:"
    //val enterAllPeople = "Enter all people:"

    val enterDataToSearchPeople = "\nEnter a name or email to search all suitable people."
    val peopleFound = "People found:"
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

fun readFile(fileName: String): String {
    val userDir = System.getProperty ("user.dir") + File.separator
    return try {
        File("$userDir$fileName").readText()
    } catch (e: Exception) {
        println("Error! File not found.")
        "Error"
    }
}
