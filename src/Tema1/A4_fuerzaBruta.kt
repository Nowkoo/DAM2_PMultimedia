package Tema1

fun main() {
    val articulosSeleccion = mejorCombinacion(obtenerArticulos(), 8.0)

    println("La mejor combinación es: ")
    for (articulo in articulosSeleccion) {
        println(articulo)
    }
}

fun mejorCombinacion(articulos: List<Articulo>, pesoMaximo: Double): List<Articulo> {
    val combinaciones = combinaciones(articulos)

    var mejorValor = 0.0
    var mejorCombinacion: List<Articulo> =  emptyList()

    for (combinacion in combinaciones) {
        val pesoTotal = combinacion.sumOf { articulo -> articulo.peso }
        val valorTotal = combinacion.sumOf { articulo -> articulo.valor }

        if (pesoTotal <= pesoMaximo && valorTotal > mejorValor) {
            mejorCombinacion = combinacion
            mejorValor = valorTotal
        }
    }

    return mejorCombinacion
}

fun combinaciones(articulos: List<Articulo>): List<List<Articulo>> {
    val todasLasCombinaciones = mutableListOf<List<Articulo>>()
    todasLasCombinaciones.add(emptyList())

    for (articulo in articulos) {
        val combinacionesActuales = todasLasCombinaciones.toList()

        for (combinacion in combinacionesActuales) {
            val nuevaCombinacion = combinacion + articulo
            todasLasCombinaciones.add(nuevaCombinacion)
        }
    }

    return todasLasCombinaciones
}

fun obtenerArticulos(): List<Articulo> {
    return listOf(
            Articulo("Refresco", 1.0, 2.0),
            Articulo("Saco de manzanas", 3.0, 5.0),
            Articulo("Comida para gato", 4.0, 10.0),
            Articulo("Detergente para ropa", 5.0, 14.0),
            Articulo("Mancuerna de 7 kg", 7.0, 15.0),
    )
}

data class Articulo (
    var nombre: String,
    var peso: Double,
    var valor: Double,
)