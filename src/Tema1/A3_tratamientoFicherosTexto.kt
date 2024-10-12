package Tema1

import java.io.File

const val RUTA_USUARIOS = "Recursos/usuarios.txt"
val opciones = listOf("Mostrar usuarios mayores de edad", "Buscar usuarios por nombre", "Agregar un nuevo usuario", "Salir")


fun main() {
    while (true) {
        opciones.forEachIndexed {index, opcion -> println("${index + 1}.-$opcion")}

        when (pedirOpcion()) {
            "1" -> {mostrarMayoresDeEdad().forEach { usuario -> println("${usuario.first},${usuario.second}")}}
            "2" -> {
                println("¿Qué nombre estás buscando?")
                val nombre = readLine().orEmpty()
                buscarPorNombre(nombre).forEach { usuario -> println("${usuario.first},${usuario.second}")}
            }
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
    val nombre = readLine().orEmpty().trim()
    if (nombre.isEmpty()) {
        println("El nombre no puede estar vacío.")
        return
    }

    println("Ahora introduce su edad:")
    val edadString = readLine().orEmpty().trim()
    val edadInt = edadString.toIntOrNull()
    if (edadInt == null || edadInt < 0) {
        println("La edad tiene que ser un número positivo.")
        return
    }

    if (buscarPorNombre(nombre).isNotEmpty()) {
        println("El nombre de usuario ya existe.")
        return
    }

    File(RUTA_USUARIOS).appendText("\n$nombre,$edadInt")
    println("Usuario agregado.")
}

fun buscarPorNombre(nombre: String): List<Pair<String, Int>> {
    var usuarios = leerUsuarios(RUTA_USUARIOS)
    return usuarios.filter { usuario ->
            usuario.first.contains(nombre.trim(), ignoreCase = true)
        }
}

fun mostrarMayoresDeEdad(): List<Pair<String, Int>> {
    var usuarios = leerUsuarios(RUTA_USUARIOS)
    return usuarios.filter { usuario -> usuario.second >= 18 }
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
        input = readLine().orEmpty()
    } while (input.isEmpty())
    return input
}