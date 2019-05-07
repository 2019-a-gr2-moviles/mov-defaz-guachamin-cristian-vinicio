

class Usuario(val cedula: String) {
    public var nombre: String = ""
    private var apellido: String = ""

    constructor(
        cedula: String,
        apellido: String
    ) : this(cedula) {
        this.apellido = apellido
    }

}

class UsuarioKT(
    public var nombre: String,
    public val apellido: String,
    protected var id: Int
) {

    // Public y Unit son por defecto,
    // los que no especifiquen o que devulve
    private fun hola(): String {
        return this.apellido
    }

    protected fun hola2() {

    }

    // Metodos static
    class A() {
        // A.correr() -> metodo estático
        // A.gravedad -> propiedad estática
        // En kotlin no existe 'static', entonces...
    }

    companion object {
        val gravedad = 10.5
        fun correr() {
            println("Estoy corriendo en $gravedad")

        }
    }
}

// Llamando a los metodos de UsuarioKT
fun aa() {
    UsuarioKT.gravedad
    UsuarioKT.correr()
}

fun a() {

    var criss = UsuarioKT("a", "b", 2);
    // En Kotlin, los getters/setters (de java) no son necesarios
    // Se las llama directamente:
    criss.nombre = "Vinni";
    // No se necesita escrbir 'public', ya que para kotlin,
    // todas las propiedades por defecto son públicas
    // Si se necesitan variables privadas:
}

// sobrecarga de funciones,
// constructores que reciban un # / tipo distinto de parámetros
class Numero(var numero: Int) {
    constructor(numeroString: String) : this(numeroString.toInt()) { // la clase recibe tanto enteros como strings
        println("contructuor")
    }

    init {
        println("Init")
    }
}

// Clases abstractas
// Todas las clases en kotiln son final, entonces usamos open
open class Numeros1(
    var numeroUno: Int,
    var numeroDos: Int
) {

}

// Si no queremos que se pueda instanciar la clase, colocamos abstract
abstract class Numeros(var numeroUno: Int, var numeroDos: Int) {
}

class Suma(num1: Int, num2: Int) : Numeros(num1, num2) {

}

fun cc() {
    val a = Suma(1, 2)
    // val b= Numeros(1,3)


}

fun presley(requerido: Int, opcional: Int = 1, nulo: Int?) {

}

fun cddd() {  // Named parameters
    presley(1, 3, 4) //Name parameters
    presley(7, 2, 3)
}

// companion object, singleton
class BaseDeDatos() {
    companion object {
        val usuarios = arrayListOf(1, 2, 3)
        fun agregarUsuarios(usuario: Int) {
            this.usuarios.add(usuario)
        }

        fun eliminarUsuario() {
            //this.usuarios
        }
    }
}