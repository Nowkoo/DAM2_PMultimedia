package Tema1

import java.io.File
import java.io.FileWriter

var opciones = listOf("Mostrar usuarios mayores de edad", "Buscar usuarios por nombre", "Agregar un nuevo usuario", "Salir")

fun main() {
    while (true) {
        var i = 1
        for (opcion in opciones) {
            println("$i.-$opcion")
            i++
        }

        var input = pedirOpcion()
        when (input) {
            "1" -> {mostrarMayoresDeEdad().forEach { usuario -> println("${usuario.first},${usuario.second}")}}
            "2" -> {buscarPorNombre("juan").forEach { usuario -> println("${usuario.first},${usuario.second}")}}
            "3" -> {agregarNuevo()}
            "4" -> {
                println("Adiós")
                break
            }
            else -> println("Opción inválida")
        }
    }
}

fun agregarNuevo() {
    println("Introduce el nombre del nuevo usuario:")
    var nombre = readLine().toString()
    if (nombre == "") {
        println("El nombre no puede estar vacío.")
    } else {
        println("Ahora introduce su edad:")
        var edad = readLine().toString()
        if (edad != "") {
            if (edad.toInt() < 0) {
                println("La edad no puede ser negativa.")
            } else {
                if (buscarPorNombre(nombre).count() > 0) {
                    println("El nombre de usuario ya existe.")
                }
                File("Recursos/usuarios.txt").appendText("\n$nombre,$edad")
            }
        }
    }
}

fun buscarPorNombre(nombre: String): List<Pair<String, Int>> {
    var usuarios = leerUsuarios("Recursos/usuarios.txt")
    return usuarios
        .filter { usuario -> usuario.first.trim().lowercase() == nombre.trim().lowercase() }

}

fun mostrarMayoresDeEdad(): List<Pair<String, Int>> {
    var usuarios = leerUsuarios("Recursos/usuarios.txt")
    return usuarios
        .filter { usuario -> usuario.second > 18 }
}

fun leerUsuarios(archivo: String): MutableList<Pair<String, Int>> {
    val usuarios = mutableListOf<Pair<String, Int>>()

    // Leer línea por línea y agregar cada usuario a la lista
    val file = File(archivo)
    if (!file.exists()) {
        println("El archivo no existe. Se creará un nuevo archivo.")
        file.createNewFile()
    } else {
        file.forEachLine { linea ->
            val partes = linea.split(",")
            if (partes.size == 2) {
                val nombre = partes[0].trim()
                val edad = partes[1].trim().toIntOrNull()

                if (edad != null) {
                    usuarios.add(nombre to edad)
                }
            }
        }
    }
    return usuarios
}

fun pedirOpcion(): String {
    var input: String
    do {
        println("Introduce una opción: ")
        input = readLine().toString()
    } while (input.isEmpty())
    return input
}