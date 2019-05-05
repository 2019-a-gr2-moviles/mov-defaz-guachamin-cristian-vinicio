import javax.swing.*
import java.awt.Dimension
import java.awt.Font
import java.awt.Font.PLAIN
import java.io.FileWriter
import java.io.IOException

val optionClarity = arrayOf<Any>(
    "Flawless",
    "Internally Flawless",
    "Very Very Sightly Included",
    "Very Sightly Included",
    "Sightly Included",
    "Included"
)
val optionClar = JComboBox(optionClarity)

val optionColor = arrayOf<Any>(
    "Colorless",
    "Near Colorless",
    "Faint",
    "Very Light",
    "Light"
)
val optionCol = JComboBox(optionColor)

val optionCut = arrayOf<Any>(
    "Ideal (Round)",
    "Premium (Princess)",
    "Very good (Marquise)",
    "Good (Pear)",
    "Fair (Emerald)"
)
val optionCuts = JComboBox(optionCut)

fun main() {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    val arrMain: Array<String> = arrayOf("New","Search diamond","Modify","Delete","Exit")
    val icon = ImageIcon("src/diamond.png")

    // Main menu loop
    do {
    val x = JOptionPane.showOptionDialog(null, "Welcome to Diamonds EPN, please select an option",
        "Diamonds EPN", 1,3,icon,arrMain,null )
        when (x) {
            0 -> { // add new diamond
                add()
            }
            1 -> {
                find()
            }
            2 -> {
                modify()
            }
            3 -> {
                val paramName = JOptionPane.showInputDialog(null,"Diamond's Name: ")
                delete(paramName, true)
            }
            4 -> {
                System.exit(0)
            }
        }
    }while (x in 0..4)
}

// Add new diamond to BDD
fun add(){
    val listaDiamantes = ArrayList<DiamanteBDD>() // Lista de diamantes
    do{
        var ok = false
        var ok2 = false
        var ok3 = false
        val diamond = DiamanteBDD() // instancia de diamante

        // get name
        while (!ok){
            val name = JOptionPane.showInputDialog(null,"Name:")
            if(name.matches("".toRegex())){
                JOptionPane.showMessageDialog(null,"Non valid input","Error",0,null)
            }else{
                diamond.name = name
                ok = true //finish loop
            }
        }
        // get clarity
        JOptionPane.showMessageDialog(null, optionClar, "Select diamond clarity",JOptionPane.QUESTION_MESSAGE)
        diamond.clarity = optionClar.selectedItem.toString()

        // get color
        JOptionPane.showMessageDialog(null, optionCol, "Select diamond color",JOptionPane.QUESTION_MESSAGE)
        diamond.color = optionCol.selectedItem.toString()

        // get cut
        JOptionPane.showMessageDialog(null, optionCuts, "Select diamond cut",JOptionPane.QUESTION_MESSAGE)
        diamond.cut = optionCuts.selectedItem.toString()

        // get carat
        while (!ok2){
            val carat = JOptionPane.showInputDialog(null,"Carat:")
            if(carat.matches("\\d+(\\.\\d+)?".toRegex())){
                diamond.carat = carat
                ok2 = true
            }else{
                JOptionPane.showMessageDialog(null,"Non valid input","Error",0,null)
            }
        }
        // get price
        while (!ok3){
            val price = JOptionPane.showInputDialog(null,"Price:")
            if(price.matches("\\d+(\\.\\d+)?".toRegex())){
                diamond.price = price
                ok3 = true
            }else{
                JOptionPane.showMessageDialog(null,"Non valid input","Error",0,null)
            }
        }
        listaDiamantes.add(diamond) // añade el diamante a la lista
        val sw = JOptionPane.showConfirmDialog(null, "Add more diamonds?")
    } while (sw == JOptionPane.YES_OPTION)
    // Writing on file
    DiamanteBDD.addDiamond(listaDiamantes) // Arraylist to file
    var items = "" // Sumarize new items
    listaDiamantes.forEach {
        items +="Name: ${it.name}\nClarity: ${it.clarity}\nColor: ${it.color}\n" +
                "Cut: ${it.cut}\nCarat: ${it.carat}        Price: ${it.price}\n\n"
    }
    showText(items)
}

fun find(){
    val arrFind: Array<String> = arrayOf("View all","Filter by..","Return to menu")
    do{
        var x = JOptionPane.showOptionDialog(null, "Available diamonds, please select an option",
            "Diamonds EPN", 1,3,null,arrFind,null )
        when (x) {
            0 -> { // show all
                var items = "" // Sumarize new items
                DiamanteBDD.listarDiamantes().forEach {
                    items +="Name: ${it.name}\nClarity: ${it.clarity}\nColor: ${it.color}\n" +
                            "Cut: ${it.cut}\nCarat: ${it.carat}        Price: ${it.price}\n\n"
                }
                println(items)
                showText(items)
            }
            1 -> { // filter by category
                val arrFilter: Array<String> = arrayOf("by Name","by Clarity","by Color","by Cut","Volver al menú")
                do {
                    var y = JOptionPane.showOptionDialog(null, "Filtering diamonds, please select an option",
                        "Diamonds EPN", 1,3,null,arrFilter,null )
                    when(y){
                        0 -> { // by Name
                            val name = JOptionPane.showInputDialog(null,"Diamond's Name: ")
                            showFind(name)
                        }
                        1 -> { // by clarity
                            JOptionPane.showMessageDialog(null, optionClar, "Select diamond clarity",JOptionPane.QUESTION_MESSAGE)
                            showFind(optionClar.selectedItem.toString())
                        }
                        2 -> { // by color
                            JOptionPane.showMessageDialog(null, optionCol, "Select diamond color",JOptionPane.QUESTION_MESSAGE)
                            showFind(optionCol.selectedItem.toString())
                        }
                        3 -> { // by cut
                            JOptionPane.showMessageDialog(null, optionCuts, "Select diamond cut",JOptionPane.QUESTION_MESSAGE)
                            showFind(optionCuts.selectedItem.toString())
                        }
                        4 -> { // breaking loop
                            y = 5
                        }
                    }

                }while (y in 0..4)
                println("Buscar uno solo")
            }
            2 -> {
               x = 3 // finishing loop
            }
        }
    }while (x in 0..2)
}

// Actualizr un diamante, buscándolo por su nombre
fun modify(){
    val paramName = JOptionPane.showInputDialog(null,"Diamond's Name: ")
    val diamondToModify = DiamanteBDD.searchBy(paramName)
    if (diamondToModify != null) {
        val arrCar: Array<String> = arrayOf("Name","Clarity","Color","Cut","Carat","Price","Return to menu")
        delete(paramName, false) // Borra la linea del diamante a modificar
        when(JOptionPane.showOptionDialog(null, "Modifying '$paramName' diamond, please select an option",
            "Diamonds EPN", 1,3,null,arrCar,null )){
            0 -> { // Change name
                diamondToModify[0].name = JOptionPane.showInputDialog(null,"New Name: ")
            }
            1 -> { // Change clarity
                JOptionPane.showMessageDialog(null, optionClar, "Select new Clarity:",JOptionPane.QUESTION_MESSAGE)
                diamondToModify[0].clarity = optionClar.selectedItem.toString()
            }
            2 -> { // Change Color
                JOptionPane.showMessageDialog(null, optionCol, "Select new Color:",JOptionPane.QUESTION_MESSAGE)
                diamondToModify[0].color = optionCol.selectedItem.toString()
            }
            3 -> { // Change Cut
                JOptionPane.showMessageDialog(null, optionCuts, "Select new Cut:",JOptionPane.QUESTION_MESSAGE)
                diamondToModify[0].cut = optionCuts.selectedItem.toString()
            }
            4 -> { // Change carat
                diamondToModify[0].carat = JOptionPane.showInputDialog(null,"New Carat: ")
            }
            5 -> { // Change price
                diamondToModify[0].price = JOptionPane.showInputDialog(null,"New Price: ")
            }
        }
        var items = ""
        diamondToModify.forEach { item ->
            items +="Name: ${item.name}\nClarity: ${item.clarity}\nColor: ${item.color}\n" +
                    "Cut: ${item.cut}\nCarat: ${item.carat}        Price: ${item.price}\n\n"
        }
        showText(items)
        println(items)
        DiamanteBDD.addDiamond(diamondToModify as ArrayList<DiamanteBDD>)
    }else{
        JOptionPane.showMessageDialog(null,"$paramName no esta registrado")
    }
}

// Delete diamonds
fun delete(paramName: String, ok: Boolean){

    val diamondToDelete = DiamanteBDD.searchBy(paramName) // Averiguamos si existe
    val newList = ArrayList<DiamanteBDD>() // Nueva lista generada
    if (diamondToDelete != null) {
        DiamanteBDD.listarDiamantes().forEach {
            if(it.name != diamondToDelete[0].name){
                newList.add(it) // Se excluye al elemento eliminado
            }
        }
        // Print new List without deleted diamond
        var items = ""
        var text = ""
        newList.forEach { item ->
            items +="Name: ${item.name}\nClarity: ${item.clarity}\nColor: ${item.color}\n" +
                    "Cut: ${item.cut}\nCarat: ${item.carat}        Price: ${item.price}\n\n"
            text+="${item.name};${item.clarity};${item.color};${item.cut};${item.carat};${item.price}\n"
        }
            // Sobreescribiendo el archivo
        try {
            val file = FileWriter("src/texto.txt")
            file.write(text)
            file.close()
        }catch (e: IOException){
            println("No se puede leer")
        }
        if(ok){
            showText(items)
        }
    }
}

fun showFind(param: String){
    var items = ""
    DiamanteBDD.searchBy(param)?.forEach { item ->
        items +="Name: ${item.name}\nClarity: ${item.clarity}\nColor: ${item.color}\n" +
                "Cut: ${item.cut}\nCarat: ${item.carat}        Price: $ ${item.price}\n\n"
    }
    showText(items)

}

// Show results in JTextArea
fun showText(items: String){
    val txt = JTextArea(items)
    val scr = JScrollPane(txt)
    txt.font = Font("Tahoma", PLAIN, 12)
    txt.isEditable = false
    scr.preferredSize = Dimension(230, 300)
    JOptionPane.showMessageDialog(null,scr,"Available Diamonds !!!",1,null)
}


