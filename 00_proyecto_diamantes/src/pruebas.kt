import javax.swing.JOptionPane
import javax.swing.UIManager

// Poyecto Diamantes Aplicaciones móviles - GR2
fun main() {
    init()
/*    println("Hola  mundo !")

   // probarJPane()
    val a = 31
    val b = 51
    println("Producto:" +a +" * " +b +"= "+producto(a,b)+"\n")
    val nums = arrayListOf(122,24,45,86,99,13)
    nums.forEach {num ->
        println(num)
    }
    val arregloEquipos = arrayListOf("BSC","LDU","CSE","IDV")
    val arregloEquipos2 = arregloEquipos.map {
        "EC: $it"
    }
    arregloEquipos2.forEach {
        println(it)
    }*/

/*    val equipo = "LDU"
    val res = buscar(arregloEquipos,equipo)
    if(res){
        println("Existe equipo")
        JOptionPane.showMessageDialog(null,"Existe equipo")
    }else{
        println("No existe equipo")
        JOptionPane.showMessageDialog(null,"No existe equipo")
    }*/

    // escribir en archivo
/*    println("Esribiendo al archivo...")
    try {
    val file = FileWriter("src/texto.txt",true)
        file.write("Sigo esccribiendo\n")
        file.close()
    }catch (e: IOException){
        println("No se puede leer")
    }


    // Leer un archivo
    println("Leyendo archivo...")
    val lineas = File("src/texto.txt").readLines()
    var text = ""
    lineas.forEachIndexed{x: Int, line: String ->
        println("$x: $line")
        text+="$line \n"
    } */
 //   JOptionPane.showMessageDialog(null,text)
    val listaDiamantes = ArrayList<DiamanteBDD>()
    val diamond = DiamanteBDD(5.0,"premiun","G","best")
    val diamond2 = DiamanteBDD(3.2,"ideal","D","best")
    listaDiamantes.add(diamond)
    listaDiamantes.add(diamond2)
    listaDiamantes.add(DiamanteBDD(2.6,"low","E","worst"))
    var text = ""

    DiamanteBDD.addDiamante(listaDiamantes).forEach {
        text+="Color: ${it.color} Carat: ${it.carat}  Cut: ${it.cut}  Clarity: ${it.clarity}\n"
       // println(text)
    }

    JOptionPane.showMessageDialog(null,text)
    println(diamond.color)
}




fun buscar (arregloE: ArrayList<String>, eq: String): Boolean{
    return arregloE.any {
        it == eq
    }
}
fun producto (num1: Int, num2: Int): Int{
    return num1 * num2
}

fun init() {
    println("Starting application")
    try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    } catch(e: Exception){
        e.printStackTrace()
    }
}

fun probarJPane(){
    val nombre = JOptionPane.showInputDialog("Nombre:")

    val arreglo: Array<Any> = arrayOf("Hola","Seleciona una opcion","1. Saludar","2. Salir")
    val x = JOptionPane.showOptionDialog(null, "wenaaas !","xd", 2,3,null,arreglo,null )
    val y: String = nombre+x
    JOptionPane.showMessageDialog(null,y)
}

