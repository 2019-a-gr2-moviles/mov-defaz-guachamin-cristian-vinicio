
 fun main (){
     val arregloCumpleanios: IntArray = intArrayOf(1,2,3,4) // dato inmutable
     val arregloTodo1: Array<Any> = arrayOf(1, "asdf", 10.2, true) // arreglo inmutable
     var arregloTodo: Array<Any> = arrayOf(1, "asdf", 10.2, true)

     val notas: ArrayList<Int> = arrayListOf(7,6,9,9,10,4)
     // Iteraciones
     // For each: itera el arreglo

    /* notas.forEachIndexed { indice, nota ->
        println("Indice: $indice, Nota: $nota")
        //  return Unit
     }
     */

     // Operadores básicos: MAP -> itera y modifica el arreglo
     // Sumar a los impares +1 y a los pares +2
     val notasDos = notas.map { nota ->
         val modulo = nota % 2
         val espar = 0
         when (modulo) {
             espar -> {
                 nota + 1
             }
             else -> {
                 nota + 2
             }
         }
     }
/*
     println("Notas modificadas:")
     notasDos.forEach {
         println("$it")
     }
*/

     // devolver solo las notas mayores a 8
     val respuestaFiler = notas.filter {
         it > 8
     }
     respuestaFiler.forEach{ println(it)}
 }


