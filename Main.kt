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
            1 -> Search(mapIndex, allPeopleList).indexSearch()
            2 -> printPerson(allPeopleList, message)
            else -> message.menuInputError
        }
    }
    println("\nBye!")
}