package search

data class PrintMessage(var maxNumber: Int = 0) {
    val enterNumberOfPeople = "Enter the number of people:"
    val enterAllPeople = "Enter all people:"
    val enterNumberOfQueries = "\nEnter the number of search queries:"
    val enterDataToSearchPeople = "\nEnter data to search people:"
    val peopleFound = "People found:"
    val noMatchingPeopleFound = "No matching people found."

    val menuItemList = listOf("=== Menu ===",
                              "Exit",
                              "Find a person",
                              "Print all people"
                             )
}

object UserMenu {
    private val menu = PrintMessage()
    fun printMenu() {
        for (i in menu.menuItemList.indices){
            println(if(i == 0) menu.menuItemList[i] else "${i-1}. ${menu.menuItemList[i]}")
        }


    }
}

fun printPerson(){

}

fun searchPerson(){

}