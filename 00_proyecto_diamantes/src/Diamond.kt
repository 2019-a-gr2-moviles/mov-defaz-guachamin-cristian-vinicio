import java.io.File
import java.io.FileWriter
import java.io.IOException

// Diamond class
class DiamanteBDD(
    var name: String,
    var clarity: String,
    var color: String,
    var cut: String,
    var carat: String,
    var price: String){

    // Default constructor
    constructor():this(
        "",
        "",
        "",
        "",
        "",
        ""
    )

    companion object {
        private val listaDiamantes = arrayListOf<DiamanteBDD>()
        private var text: String = ""

        // Agregar una lista de diamantes a la BDD
        fun addDiamond(diamante: ArrayList<DiamanteBDD>) {
            diamante.forEach {
                listaDiamantes.add(it)
                // Creando linea para archivo
                text+="${it.name};${it.clarity};${it.color};${it.cut};${it.carat};${it.price}\n"
            }
                // Agregando a Archivo
                try {
                    val file = FileWriter("src/texto.csv",true)
                    file.write(text)
                    file.close()
                    text = "" // clean the items
                }catch (e: IOException){
                    println("No se puede leer")
                }
        }

        // Listar diamantes de arrayList desde archivo
        fun listarDiamantes(): ArrayList<DiamanteBDD>{
            val listaDiamantes = ArrayList<DiamanteBDD>() // Lista de diamantes
            try {
                val lineas = File("src/texto.csv").readLines()
                lineas.forEach{
                    val d = it.split(";")
                    val diamond = DiamanteBDD(d[0],d[1],d[2],d[3],d[4],d[5])
                    listaDiamantes.add(diamond)
                }
            }catch (e: IOException){
                println("Cannot read")
            }
            return listaDiamantes
        }

        // Buscar diamantes por cataegoria
        fun searchBy(param: String): List<DiamanteBDD>? {
            listarDiamantes().forEach {
                when(param){
                    it.name -> {
                        return listarDiamantes().filter { diam ->
                            diam.name == param
                        }
                    }
                    it.clarity -> {
                        return listarDiamantes().filter {diam ->
                            diam.clarity == param
                        }
                    }
                    it.color -> {
                        return listarDiamantes().filter {diam ->
                            diam.color == param
                        }
                    }
                    it.cut -> {
                        return listarDiamantes().filter {diam ->
                            diam.cut == param
                        }
                    }
                }
            }
            return null
        }
    }
}