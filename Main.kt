package search

fun main(args: Array<String>) {

    val fileName = args[args.indexOf("--data") + 1]
    val allPeopleList = readFile(fileName)
    val mapIndex = getIndexKey(allPeopleList)

    val message = PrintMessage()
    val userMenu = UserMenu()
    while (true) {
        when(userMenu.outMenu()) {
            0 -> break
            1 -> indexSearch(mapIndex, allPeopleList, message)
            2 -> printPerson(allPeopleList, message)
            else -> message.menuInputError
        }
    }
    println("\nBye!")
}