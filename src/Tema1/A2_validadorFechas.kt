package Tema1

fun main() {
    var fechaString = pedirFecha()
    var esFechaValida = formatoValido(fechaString)
    println("La fecha introducida es válida: $esFechaValida")
}

fun pedirFecha(): String {
    var input: String
    do {
        println("Introduce una fecha (DD/MM/AAAA): ")
        input = readln()
    } while (input.isEmpty())
    return input
}

fun formatoValido(fecha: String): Boolean {
    var fechaDividida = fecha.split('/')
    if (fechaDividida.size == 3 && contieneNumero(fechaDividida)) {
        var day = fechaDividida[0].toInt()
        var month = fechaDividida[1].toInt()
        var year = fechaDividida[2].toInt()
        var esBisiesto: Boolean = isBisiesto(year)

        return (validDay(day) && validMonth(month, day, esBisiesto) && validYear(year))
    }
    return false
}

fun contieneNumero(fecha: List<String>): Boolean {
    for (parte in fecha) {
        if (parte.equals("") || !esDigito(parte)) return false
    }
    return true
}

fun esDigito(cadena: String): Boolean {
    for (caracter in cadena) {
        if (!"0123456789".contains(caracter)) return false
    }
    return true
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