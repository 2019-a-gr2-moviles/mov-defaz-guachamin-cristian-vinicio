import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>){
  //  println("Hello world")

    // variables
    /*
    *Comentarios
    */

    //variabkes mutables, no mutables
    //mutables: que pueden cambiar
    //mutables
    var nombre = "Criss"
    nombre = "Vinicio"

    //inmutables
    val nombreI = "Criss"
    // nombreI = "Vinicio"

    // Todo lo inmutable es bueno.
    // en cualquier lenguaje de programación

    // tipos de datos
    val apellido: String = "Defaz"
    val edad: Int = 25
    val sueldo: Double = 1.21
    val casado: Boolean = false;
    val profesor: Boolean = false;
    val hijos = null;
    // en kotlin no es necesario definir el tipo de datos.

    // duck typing
    // si parece un pato
    // si camina como pato
    // si suena como pato
    // si vuela como pato
    // PATO


    // Condicionales
    if(apellido == "Defaz"){
        println("Verdad");
    }else{
        println("Falso");
    }

    val tiennombreYApellido = if(apellido!=null && nombre!=null) "ok" else "no"
    println(tiennombreYApellido)

    estaJalado(1.0)
    estaJalado(8.0)
    estaJalado(0.0)
    estaJalado(7.0)
    estaJalado(10.0)

    holaMundo("Criss")
    holaMundoAvanzado(2)
    var total = sumarDosNumeros(1,3)
    println(total)


    val arregloCumpleanios: IntArray = intArrayOf(1,2,3,4) // dato inmutable
   // val arregloTodo: Array<Any> = arrayOf(1, "asdf", 10.2, true)
    var arregloTodo: Array<Any> = arrayOf(1, "asdf", 10.2, true)


    arregloCumpleanios[0] = 5
    arregloCumpleanios.set(0, 5)
  //  arregloCumpleanios = arrayOf(5,3,2,0)
    // variable no puede ser rasignada
    //
//    val fecha = Date()
 //   fecha.time = 11232323
  //  fecha = Date(1989,6, 10)
   // fecha.year = 2000
    // fecha = Date(1989,6,10)
    // Date es una clase, por tanto, se pueden hacer reasignar
    // La variable es inmutable, no se puede modificar la variable de la clase
    // Se pueden utilizat sus metodos para cambiar a la clase

    arregloTodo = arrayOf(5,2,3,4)
    val notas: ArrayList<Int> = arrayListOf(1,2,3,4,5,6)

    // Iteraciones
    // For each: itera el arreglo
    notas.forEachIndexed{ indice, nota ->
        println("Indice: $indice")
        println("Nota: $nota")
      //  return Unit
    }
    // con return Unit, solo se ejecuta una vez.


    // operadores básicos
    //MAP -> itera y modifica el arreglo
    // impares +1 Pares +2
    val notasDos = notas.map { nota ->

        // con if
   //     if(nota%2 == 0){
   //         nota+2
   //     }else{
   //         nota +1
  //      }

        //con when
        val modulo = nota%2
        val espar = 0
        when (modulo) {
            espar -> {
                nota + 1
            }
            else -> {
                nota + 2
            }
        }

        // devolver solo las notas mayores a 2
        val respuestaFiler = notas.filter {
            it > 2
        }

        respuestaFiler.forEach{ println(it)}

        // primero un filtro, luego un map
        val respuestaFiler2 = notas.filter {
            it in 3..4
            // it > 2 && it <5
        }
            .map{
                it*2
            }
        respuestaFiler2.forEach{ println(it)}

        val novias = arrayListOf(1,2,3,4,5)
        // algun elemento == 3?
        val respuestaNovia: Boolean = novias.any{
            it == 3
        }
        println(respuestaNovia)
        //algun0 =3? ----> entonces responde true
        // cambindo por it == 7 -------> responderá false, false, false, false....
        // todos
        val tazos = arrayListOf(1,2,3,4,5,6,7)
        val respuestaTazos = tazos.all {
            it > 1
        }

        // obtener la suma de todos los tazos?

       val totalTazos = tazos.reduce { valorAcumulado, tazo ->
            valorAcumulado + tazo
        }

        println(respuestaTazos)


    }

    notasDos.forEach {
        println("Notas 2: $it")
    }

}

// FUNCION
fun estaJalado(nota:Double){
    when(nota){
        7.0 -> {
            println("Pasaste con las justas");
        }
        10.0 -> {
            println("Genial :D felicitaciones!");
        }
        0.0 -> {
            println("Dios mío que vago !");
        }
        else -> {
            println("Tu nota es: $nota");
        }
    }
}

// mas funciones
fun holaMundo (mensaje: String): Unit {
    println("Mensaje: $mensaje" )
}

fun holaMundoAvanzado (mensaje: Any): Unit {
    println("Mensaje: $mensaje" )
}

//sumar 2 numeros
fun sumarDosNumeros (numUno: Int, numDos: Int): Int {
    return numUno + numDos
}

//arreglos
/*
En java:
ArrayList, Array
 */













