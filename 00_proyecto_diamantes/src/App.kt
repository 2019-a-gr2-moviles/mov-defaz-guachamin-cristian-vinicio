import javax.swing.*
import java.awt.Dimension
import java.awt.Font
import java.awt.Font.PLAIN
import java.io.FileWriter
import java.io.IOException

val arrayOfClarityOptions = arrayOf<Any>(
    "Flawless",
    "Internally Flawless",
    "Very Very Sightly Included",
    "Very Sightly Included",
    "Sightly Included",
    "Included"
)
val clarityCombo = JComboBox(arrayOfClarityOptions)

val arrayOfColorOptions = arrayOf<Any>(
    "Colorless",
    "Near Colorless",
    "Faint",
    "Very Light",
    "Light"
)
val colorCombo = JComboBox(arrayOfColorOptions)

val arrayOfCutOptions = arrayOf<Any>(
    "Ideal (Round)",
    "Premium (Princess)",
    "Very good (Marquise)",
    "Good (Pear)",
    "Fair (Emerald)"
)
val cutCombo = JComboBox(arrayOfCutOptions)

fun main() {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()) // L&F
    val arrayOfMainMenu: Array<String> = arrayOf("New","Search diamond","Modify","Delete","Exit")
    val icon = ImageIcon("src/diamond.png")

    // Main menu loop
    do {
    val selectedOption = JOptionPane.showOptionDialog(null, "Welcome to Diamonds EPN, please select an option",
        "Diamonds EPN", 1,3,icon,arrayOfMainMenu,null ) // menu options
        when (selectedOption) {
            0 -> {
                addNewDiamondToBD()
            }
            1 -> {
                findDiamondbyCategory()
            }
            2 -> {
                modifyAnyDiamond()
            }
            3 -> {
                val nameOfDiamondToDelete = JOptionPane.showInputDialog(null,"Diamond's Name: ")
                deleteAnyDiamond(nameOfDiamondToDelete, true)
            }
            4 -> {
                System.exit(0)
            }
        }
    }while (selectedOption in 0..4)
}

fun addNewDiamondToBD(){
    val listOfDiamondsToAdd = ArrayList<DiamondBDD>() // Lista de diamantes
    do{
        var isAvalidName = false
        var isAvalidCarat = false
        var isAvalidPrice = false
        val newDiamond = DiamondBDD()

        // get name
        while (!isAvalidName){
            val diamondName = JOptionPane.showInputDialog(null,"Name of the new diamond:")
            if(diamondName.matches("".toRegex())){
                JOptionPane.showMessageDialog(null,"Non valid input","Error",0,null)
            }else{
                newDiamond.name = diamondName
                isAvalidName = true //finish loop
            }
        }
        // get clarity
        JOptionPane.showMessageDialog(null, clarityCombo, "Select diamond clarity",JOptionPane.QUESTION_MESSAGE)
        newDiamond.clarity = clarityCombo.selectedItem.toString()

        // get color
        JOptionPane.showMessageDialog(null, colorCombo, "Select diamond color",JOptionPane.QUESTION_MESSAGE)
        newDiamond.color = colorCombo.selectedItem.toString()

        // get cut
        JOptionPane.showMessageDialog(null, cutCombo, "Select diamond cut",JOptionPane.QUESTION_MESSAGE)
        newDiamond.cut = cutCombo.selectedItem.toString()

        // get carat
        while (!isAvalidCarat){
            val newCarat = JOptionPane.showInputDialog(null,"Carat:")
            if(newCarat.matches("\\d+(\\.\\d+)?".toRegex())){
                newDiamond.carat = newCarat
                isAvalidCarat = true
            }else{
                JOptionPane.showMessageDialog(null,"Non valid input","Error",0,null)
            }
        }
        // get price
        while (!isAvalidPrice){
            val newPrice = JOptionPane.showInputDialog(null,"Price:")
            if(newPrice.matches("\\d+(\\.\\d+)?".toRegex())){
                newDiamond.price = newPrice
                isAvalidPrice = true
            }else{
                JOptionPane.showMessageDialog(null,"Non valid input","Error",0,null)
            }
        }
        listOfDiamondsToAdd.add(newDiamond)
        val selectedOption = JOptionPane.showConfirmDialog(null, "Add more diamonds?")
    } while (selectedOption == JOptionPane.YES_OPTION)

    DiamondBDD.addDiamond(listOfDiamondsToAdd)

    var stringOfDiamondsToShow = ""
    listOfDiamondsToAdd.forEach { eachDiamOfList ->
        stringOfDiamondsToShow +="Name: ${eachDiamOfList.name}\nClarity: ${eachDiamOfList.clarity}\nColor: ${eachDiamOfList.color}\n" +
                "Cut: ${eachDiamOfList.cut}\nCarat: ${eachDiamOfList.carat}        Price: ${eachDiamOfList.price}\n\n"
    }
    showDiamondsInTextArea(stringOfDiamondsToShow)
}

fun findDiamondbyCategory(){
    val arrayOfMenu1: Array<String> = arrayOf("View all","Filter by...","Return to menu")
    do{
        var selectedOption = JOptionPane.showOptionDialog(null, "Diamonds catalog, please select an option",
            "Diamonds EPN", 1,3,null,arrayOfMenu1,null )
        when (selectedOption) {
            0 -> { // show all
                var stringOfDiamondsToShow = ""
                var diamondCounter = 0
                DiamondBDD.showAllDiamondsFromFile().forEach { eachDiamond ->
                    stringOfDiamondsToShow+="Name: ${eachDiamond.name}\nClarity: ${eachDiamond.clarity}\nColor: ${eachDiamond.color}\n" +
                            "Cut: ${eachDiamond.cut}\nCarat: ${eachDiamond.carat}        Price: ${eachDiamond.price}\n\n"
                    diamondCounter++
                }
                showDiamondsInTextArea("$ \nTotal diamonds = $diamondCounter")

            }
            1 -> { // filter by category
                val arryOfSubmenu1: Array<String> = arrayOf("by Name","by Clarity","by Color","by Cut","Volver al menú")
                do {
                    var seletecOption = JOptionPane.showOptionDialog(null, "Filtering diamonds, please select an option",
                        "Diamonds EPN", 1,3,null,arryOfSubmenu1,null )
                    when(seletecOption){
                        0 -> { // by Name
                            val name = JOptionPane.showInputDialog(null,"Diamond's Name: ")
                            showDiamondsFounded(name)
                        }
                        1 -> { // by clarity
                            JOptionPane.showMessageDialog(null, clarityCombo, "Select diamond clarity",JOptionPane.QUESTION_MESSAGE)
                            showDiamondsFounded(clarityCombo.selectedItem.toString())
                        }
                        2 -> { // by color
                            JOptionPane.showMessageDialog(null, colorCombo, "Select diamond color",JOptionPane.QUESTION_MESSAGE)
                            showDiamondsFounded(colorCombo.selectedItem.toString())
                        }
                        3 -> { // by cut
                            JOptionPane.showMessageDialog(null, cutCombo, "Select diamond cut",JOptionPane.QUESTION_MESSAGE)
                            showDiamondsFounded(cutCombo.selectedItem.toString())
                        }
                        4 -> { // breaking loop
                            seletecOption = 5
                        }
                    }
                }while (seletecOption in 0..4)
            }
            2 -> { // finishing loop
               selectedOption = 3
            }
        }
    }while (selectedOption in 0..2)
}

// Actualizr un diamante, buscándolo por su nombre
fun modifyAnyDiamond(){
    val diamondName = JOptionPane.showInputDialog(null,"Diamond's Name: ").trim()
    val diamondToModify = DiamondBDD.searchDiamondByCategory(diamondName)
    if (diamondToModify != null) {
        val arrayOfSubmenu2: Array<String> = arrayOf("Change Name","Change Clarity","Change Color","Change Cut","Change Carat","Change Price")
        deleteAnyDiamond(diamondName, false) // Borra la linea del diamante a modificar
        when(JOptionPane.showOptionDialog(null, "Modifying '$diamondName' diamond, please select an option",
            "Diamonds EPN", 1,3,null,arrayOfSubmenu2,null )){
            0 -> { // Change name
                diamondToModify[0].name = JOptionPane.showInputDialog(null,"New Name: ")
            }
            1 -> { // Change clarity
                JOptionPane.showMessageDialog(null, clarityCombo, "Select new Clarity:",JOptionPane.QUESTION_MESSAGE)
                diamondToModify[0].clarity = clarityCombo.selectedItem.toString()
            }
            2 -> { // Change Color
                JOptionPane.showMessageDialog(null, colorCombo, "Select new Color:",JOptionPane.QUESTION_MESSAGE)
                diamondToModify[0].color = colorCombo.selectedItem.toString()
            }
            3 -> { // Change Cut
                JOptionPane.showMessageDialog(null, cutCombo, "Select new Cut:",JOptionPane.QUESTION_MESSAGE)
                diamondToModify[0].cut = cutCombo.selectedItem.toString()
            }
            4 -> { // Change carat
                diamondToModify[0].carat = JOptionPane.showInputDialog(null,"New Carat: ")
            }
            5 -> { // Change price
                diamondToModify[0].price = JOptionPane.showInputDialog(null,"New Price: ")
            }
        }
        var stringOfDiamondsToShow = ""
        diamondToModify.forEach { item ->
            stringOfDiamondsToShow +="Name: ${item.name}\nClarity: ${item.clarity}\nColor: ${item.color}\n" +
                    "Cut: ${item.cut}\nCarat: ${item.carat}        Price: ${item.price}\n\n"
        }
        showDiamondsInTextArea(stringOfDiamondsToShow)
      //  println(items)
        DiamondBDD.addDiamond(diamondToModify as ArrayList<DiamondBDD>)
    }else{
        JOptionPane.showMessageDialog(null,"$diamondName not found")
    }
}

// Delete diamonds
fun deleteAnyDiamond(paramName: String, showChanges: Boolean){
    val diamondToDelete = DiamondBDD.searchDiamondByCategory(paramName) // Averiguamos si existe
    val listOfDiamondsToSave = ArrayList<DiamondBDD>() // Nueva lista generada
    if (diamondToDelete != null) {
        DiamondBDD.showAllDiamondsFromFile().forEach {
            if(it.name != diamondToDelete[0].name){
                listOfDiamondsToSave.add(it) // Se excluye al elemento eliminado
            }
        }
        // Print new List without deleted diamond
        var stringOfDiamondsToShow = ""
        var lineOfDiamondsToWriteinFile = ""
        listOfDiamondsToSave.forEach { eachDiamond ->
            stringOfDiamondsToShow +="Name: ${eachDiamond.name}\nClarity: ${eachDiamond.clarity}\nColor: ${eachDiamond.color}\n" +
                    "Cut: ${eachDiamond.cut}\nCarat: ${eachDiamond.carat}        Price: ${eachDiamond.price}\n\n"
            lineOfDiamondsToWriteinFile+="${eachDiamond.name};${eachDiamond.clarity};${eachDiamond.color};${eachDiamond.cut};${eachDiamond.carat};${eachDiamond.price}\n"
        }

        try {
            val newFileOfDiamonds = FileWriter("src/texto.csv")
            newFileOfDiamonds.write(lineOfDiamondsToWriteinFile)
            newFileOfDiamonds.close()
        }catch (e: IOException){
            println("Cannot read")
        }
        if(showChanges){
            showDiamondsInTextArea(stringOfDiamondsToShow)
        }
    }
}

// Muestra los resultados de la búsqueda
fun showDiamondsFounded(categoryDiamond: String){
    var items = ""
    var cont = 0
    DiamondBDD.searchDiamondByCategory(categoryDiamond)?.forEach { eachDiamond ->
        items +="Name: ${eachDiamond.name}\nClarity: ${eachDiamond.clarity}\nColor: ${eachDiamond.color}\n" +
                "Cut: ${eachDiamond.cut}\nCarat: ${eachDiamond.carat}        Price: $ ${eachDiamond.price}\n\n"
        cont++
    }
    showDiamondsInTextArea("$items \nTotal diamonds = $cont")
}

// Show results in JTextArea over JOptionPane
fun showDiamondsInTextArea(stringOfDiamonds: String){
    val txt = JTextArea(stringOfDiamonds)
    val scr = JScrollPane(txt)
    txt.font = Font("Tahoma", PLAIN, 11)
    txt.isEditable = false
    scr.preferredSize = Dimension(220, 300)
    JOptionPane.showMessageDialog(null,scr,"Available Diamonds !!!",1,null)
}
