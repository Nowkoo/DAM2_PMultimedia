package Tema1

import kotlin.random.Random

val nombresMasculinos: List<String> = listOf("Pedro", "Vicente", "Javier", "Antonio", "Manuel", "Sergio")
val nombresFemeninos: List<String> = listOf("María", "Estela", "Ana", "Laura", "Sheila", "Fatima")
val frasesBienvenida: List<String> = listOf("¿Qué tal está hoy en este glorioso día?", "¿Cómo se encuentra usted hoy?", "¿Cómo va todo por ahí?")

fun main() {
    val nombre = pedirCampo("nombre")
    val apellido = pedirCampo("apellido")
    val edad = pedirCampo("edad").toInt()

    if (edad < 0) {
        println("Eso es imposible.")
        return
    }

    val saludoPersonalizado = generarSaludo(nombre, apellido, edad)
    println(saludoPersonalizado)
}

fun generarSaludo(nombre: String, apellido: String, edad: Int): String {
    val tratamiento = when (edad) {
        in 0..35 -> {
            when (nombre) {
                in nombresMasculinos -> "señorito"
                in nombresFemeninos -> "señorita"
                else -> "usuari@"
            }
        }
        in 36..59 -> {
            when (nombre) {
                in nombresMasculinos -> "señor"
                in nombresFemeninos -> "señora"
                else -> "usuari@"
            }
        }
        else -> {
            when (nombre) {
                in nombresMasculinos -> "don"
                in nombresFemeninos -> "doña"
                else -> "usuari@"
            }
        }
    }

    return "Hola, $tratamiento $nombre $apellido, ${fraseAleatoria()}"
}

fun fraseAleatoria(): String {
    return frasesBienvenida[Random.nextInt(0, frasesBienvenida.size)]
}

fun pedirCampo(campo: String): String {
    var input: String
    do {
        println("Introduce tu $campo: ")
        input = readln()
    } while (input.isEmpty())
    return input
}


