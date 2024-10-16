package Tema1

import kotlin.math.ceil
import kotlin.random.Random

fun main() {
    val articulos = listOf(
        Articulo("Refresco", 1.0, 2.0),
        Articulo("Saco de manzanas", 3.0, 5.0),
        Articulo("Comida para gato", 4.0, 10.0),
        Articulo("Detergente para ropa", 5.0, 14.0),
        Articulo("Mancuerna de 7 kg", 7.0, 15.0),
    )

    val articulosSeleccion = seleccionarArticulos(articulos, 8.0)
}

fun seleccionarArticulos(articulos: List<Articulo>, pesoMaximo: Double): List<Articulo> {


    return articulos
}

fun combinaciones(articulos: List<Articulo>): List<List<Articulo>> {
    val combinaciones: List<Articulo>

}

data class Articulo (
    var nombre: String,
    var peso: Double,
    var valor: Double,
)