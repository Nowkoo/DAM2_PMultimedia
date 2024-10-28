package Tema1

fun main() {
    val objetosDisponibles = obtenerObjetos()
    val capacidadMaxima = 8

    val matrizValoresMaximos = rellenarMatriz(objetosDisponibles, capacidadMaxima)
    println("Valor máximo que se puede obtener: ${matrizValoresMaximos[objetosDisponibles.size][capacidadMaxima]}")

    var mochila = extraerCombinacionConMejorValor(capacidadMaxima, objetosDisponibles, matrizValoresMaximos)
    println(mochila)
}

private fun extraerCombinacionConMejorValor(
    capacidadMaxima: Int,
    objetos: List<Objeto>,
    M: Array<DoubleArray>
): MutableList<Objeto> {
    var mochila = mutableListOf<Objeto>()
    var pesoRestante = capacidadMaxima
    for (i in objetos.size downTo 1) {
        if (M[i][pesoRestante] != M[i - 1][pesoRestante]) {
            mochila.add(objetos[i - 1])
            pesoRestante -= objetos[i - 1].peso.toInt()
        }
    }
    return mochila
}

private fun rellenarMatriz(
    objetos: List<Objeto>,
    capacidadMaxima: Int
): Array<DoubleArray> {
    val M = Array(objetos.size + 1) { DoubleArray(capacidadMaxima + 1) }

    for (i in 1..objetos.size) {
        for (j in 0..capacidadMaxima) {
            if (objetos[i - 1].peso <= j) {
                M[i][j] = maxOf(
                    M[i - 1][j],
                    objetos[i - 1].valor + M[i - 1][(j - objetos[i - 1].peso).toInt()]
                )
            } else {
                M[i][j] = M[i - 1][j]
            }
        }
    }

    return M
}

fun obtenerObjetos(): List<Objeto> {
    return listOf(
            Objeto("Refresco", 1.0, 2.0),
            Objeto("Saco de manzanas", 3.0, 5.0),
            Objeto("Comida para gato", 4.0, 10.0),
            Objeto("Detergente para ropa", 5.0, 14.0),
            Objeto("Mancuerna de 7 kg", 7.0, 15.0),
    )
}

data class Objeto (
        var nombre: String,
        var peso: Double,
        var valor: Double,
)