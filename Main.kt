package search

fun main() {
    val stringList = readln().split(" ")
    val word = readln()
    println(if (stringList.contains(word)) stringList.indexOf(word) + 1 else "Not found")
}
