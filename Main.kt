package search

fun main() {
    //val stringList = readln().split(" ")
    //val word = readln()
    //println(if (stringList.contains(word)) stringList.indexOf(word) + 1 else "Not found")
    val message = PrintMessage()
    println(message.enterNumberOfPeople)
    val numOfPeople = readln().toInt()
    println(message.enterAllPeople)
    val allPeopleList = List(numOfPeople) { readln().trim() }
    println(message.enterNumberOfQueries)
    val numQueries = readln().toInt()
    for (a in 1..numQueries) {
        println(message.enterDataToSearchPeople)
        val searchKey = readln().trim().lowercase()
        var flag = 0
        for (i in allPeopleList.indices) {
            if (allPeopleList[i].lowercase().contains(searchKey)) {
                if (flag == 0) {
                    println(message.peopleFound)
                    flag = 1
                }
                println(allPeopleList[i])
            }
        }
        if (flag == 0) println("${message.noMatchingPeopleFound}")
        //else println("\n")
    }
}
