package Tema1

import java.util.*

fun main() {
    var fechaString = pedirFecha()
    var fecha: Date

    var day: Int = 1
    var month: Int = 12
    var year: Int = 1999

    var esBisiesto: Boolean = isBisiesto(year)

    var esFechaValida =
        if (validDay(day) && validMonth(month, day, esBisiesto) && validYear(year)) true else false
}

fun pedirFecha(): String {
    var input: String
    do {
        println("Introduce una fecha: ")
        input = readln()
    } while (input.isEmpty())
    return input
}

fun validDay(day: Int): Boolean {
    return day in 1..31
}

fun validMonth(month: Int, day: Int, esBisiesto: Boolean): Boolean {
    if (!(month in 1..12)) return false

    var meses30 = listOf(4, 6, 9, 11)

    when (month) {
        2 -> {
            if (day <= 28 && esBisiesto) return true
            else if (day <= 29 && !esBisiesto) return true
            else return false
        }
        in meses30 -> if (day <= 30) return true else return false
        else -> {if (day <= 31) return true else return false}
    }
}

fun validYear(year: Int): Boolean {
    if (year < 1582) return false
    return true
}

fun isBisiesto(year: Int): Boolean {
    if (year % 100 == 0) {
        if (year % 400 == 0) {
            return true
        } else {
            return false
        }
    } else if (year % 4 == 0) {
        return true
    } else {
        return false
    }
}