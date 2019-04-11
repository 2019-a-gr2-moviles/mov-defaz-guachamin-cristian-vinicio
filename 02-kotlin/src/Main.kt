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






