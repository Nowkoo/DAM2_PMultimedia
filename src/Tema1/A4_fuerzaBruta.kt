package Tema1

fun main() {
    val articulosSeleccion = mejorCombinacion(obtenerArticulos(), 8.0)

    println("La mejor combinación es: ")
    for (articulo in articulosSeleccion) {
        println(articulo)
    }
}

fun mejorCombinacion(articulos: List<Objeto>, pesoMaximo: Double): List<Objeto> {
    val combinaciones = combinaciones(articulos)

    var mejorValor = 0.0
    var mejorCombinacion: List<Objeto> =  emptyList()

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

fun combinaciones(articulos: List<Objeto>): List<List<Objeto>> {
    val todasLasCombinaciones = mutableListOf<List<Objeto>>()
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

fun obtenerArticulos(): List<Objeto> {
    return listOf(
            Objeto("Refresco", 1.0, 2.0),
            Objeto("Saco de manzanas", 3.0, 5.0),
            Objeto("Comida para gato", 4.0, 10.0),
            Objeto("Detergente para ropa", 5.0, 14.0),
            Objeto("Mancuerna de 7 kg", 7.0, 15.0),
    )
}

data class Articulo (
    var nombre: String,
    var peso: Double,
    var valor: Double,
)