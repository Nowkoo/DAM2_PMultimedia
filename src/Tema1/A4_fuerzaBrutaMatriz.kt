package Tema1

fun main() {
    val articulos = obtenerArticulos()
    val capacidadMaxima = 8

    val M = Array(articulos.size + 1) { IntArray(capacidadMaxima + 1) }
    for (i in 1..articulos.size) {
        for (j in 0..capacidadMaxima + 1) {
            if (articulos[i-1].peso < j) {
                M[i][j] = maxOf(
                    M[i-1][j],
                    articulos[i-1].valor + M[i-1][(j-articulos[i-1].peso).toInt()]
                )
            } else {
                M[i][j] = M[i-1][j]
            }
        }
    }
}