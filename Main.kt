package search

fun main() {
    val message = PrintMessage()
    println(message.enterNumberOfPeople)
    val numOfPeople = readlnOrNull()?.toInt()?: 0 //NumberFormatException
    println(message.enterAllPeople)
    val allPeopleList = List(numOfPeople) { readln().trim() }
    val userMenu = UserMenu()
    while (true) {
        when(userMenu.outMenu()) {
            0 -> break
            1 -> searchQuery(allPeopleList, message)
            2 -> printPerson(allPeopleList, message)
            else -> message.menuInputError
        }
    }
    println("\nBye!")
}