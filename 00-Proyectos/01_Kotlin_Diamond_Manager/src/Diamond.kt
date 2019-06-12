import java.awt.Dimension
import java.awt.Font
import java.io.File
import java.io.FileWriter
import java.io.IOException
import javax.swing.JComboBox
import javax.swing.JOptionPane
import javax.swing.JScrollPane
import javax.swing.JTextArea

val arrayOfClarityOptions = arrayOf<Any>(
    "Flawless",
    "Internally Flawless",
    "Very Very Sightly Included",
    "Very Sightly Included",
    "Sightly Included",
    "Included"
)

val arrayOfColorOptions = arrayOf<Any>(
    "Colorless",
    "Near Colorless",
    "Faint",
    "Very Light",
    "Light"
)

val arrayOfCutOptions = arrayOf<Any>(
    "Ideal (Round)",
    "Premium (Princess)",
    "Very good (Marquise)",
    "Good (Pear)",
    "Fair (Emerald)"
)

fun loadCountriesToList(): ArrayList<String>{
    val listOfCountries = ArrayList<String>()
    try {
        val linesOfCountries = File("src/Countries.csv").readLines()
        linesOfCountries.forEach{ country ->
            listOfCountries.add(country) // each line = country
        }
    }catch (e: IOException){
        println("Cannot read")
    }
        listOfCountries.sorted()
        return listOfCountries
}

val clarityCombo = JComboBox(arrayOfClarityOptions)
val colorCombo = JComboBox(arrayOfColorOptions)
val cutCombo = JComboBox(arrayOfCutOptions)
val countriesCombo = JComboBox(loadCountriesToList().toArray())

val arrayOfSubmenu0: Array<String> = arrayOf("Show all available diamonds","Filter by...","Return to menu")
val arryOfSubmenu1: Array<String> = arrayOf("by Name","by Clarity","by Color","by Cut","by Country of origin","Volver al menú")
val arrayOfSubmenu2: Array<String> = arrayOf("Change Name","Change Clarity","Change Color","Change Cut","Change Carat","Change Price","Change Country")

// Diamond class
class DiamondBDD(
    var name: String,
    var clarity: String,
    var color: String,
    var cut: String,
    var carat: String,
    var price: String,
    var country: String){

    // Default constructor
    constructor():this(
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    )

    companion object {

        private fun addDiamondToDatabaseFile(diamondToAdd: ArrayList<DiamondBDD>) {
            val linesToWriteInFile = createLinesOfDiamonds(diamondToAdd)
            appendNewLinesToFile(linesToWriteInFile)
        }

        private fun appendNewLinesToFile(lineToAppendInFile: String){
            try {
                val diamondFile = FileWriter("src/DiamondDatabase.csv",true)
                diamondFile.write(lineToAppendInFile)
                diamondFile.close()
            }catch (e: IOException){
                println("Cannot read")
            }
        }

        private fun overwriteNewLinesToFile(linesOfDiamonds: String){
            try {
                val newFileOfDiamonds = FileWriter("src/DiamondDatabase.csv")
                newFileOfDiamonds.write(linesOfDiamonds)
                newFileOfDiamonds.close()
            }catch (e: IOException){
                println("Cannot read")
            }
        }

        private fun showAllDiamondsFromFile(): ArrayList<DiamondBDD>{
            val listOfDiamonds = ArrayList<DiamondBDD>()
            try {
                val diamondLinesFromFile = File("src/DiamondDatabase.csv").readLines()
                diamondLinesFromFile.forEach{ diamondLine ->
                    val arrayDiamond = diamondLine.split(";")
                    val newDiamondFromLine = DiamondBDD(arrayDiamond[0],arrayDiamond[1],arrayDiamond[2],arrayDiamond[3],
                                                        arrayDiamond[4],arrayDiamond[5],arrayDiamond[6])
                    listOfDiamonds.add(newDiamondFromLine)
                }
            }catch (e: IOException){
                println("Cannot read")
            }
            return listOfDiamonds
        }

        private fun createStringOfDiamonds(anyListOfDiamonds:ArrayList<DiamondBDD>):String{
            var stringOfDiamondsToCreate = ""
            var diamondCounter = 0
            anyListOfDiamonds.forEach { eachDiamond ->
                stringOfDiamondsToCreate +="Name: ${eachDiamond.name}" +
                        "\nClarity: ${eachDiamond.clarity}" +
                        "\nColor: ${eachDiamond.color}" +
                        "\nCut: ${eachDiamond.cut}" +
                        "\nCarat: ${eachDiamond.carat}        " +
                        "Price: ${eachDiamond.price}" +
                        "\nCountry of origin: ${eachDiamond.country}\n\n"
                diamondCounter++
            }
            return "$stringOfDiamondsToCreate \nTotal diamonds found = $diamondCounter"
        }

        private fun createLinesOfDiamonds(anyListOfDiamonds:ArrayList<DiamondBDD>):String{
            var linesOfDiamondsToWriteinFile = ""
            anyListOfDiamonds.forEach { eachDiamond ->
                linesOfDiamondsToWriteinFile+="${eachDiamond.name};" +
                        "${eachDiamond.clarity};${eachDiamond.color};"+
                        "${eachDiamond.cut};${eachDiamond.carat};" +
                        "${eachDiamond.price};${eachDiamond.country}\n"
            }
            return linesOfDiamondsToWriteinFile
        }

        private fun searchDiamondByCategory(categoryDiamond: String): List<DiamondBDD>? {
            showAllDiamondsFromFile().forEach {diamond ->
                when(categoryDiamond){
                    diamond.name -> {
                        return showAllDiamondsFromFile().filter { eachDiamond ->
                            eachDiamond.name == categoryDiamond
                        }
                    }
                    diamond.clarity -> {
                        return showAllDiamondsFromFile().filter { eachDiamond ->
                            eachDiamond.clarity == categoryDiamond
                        }
                    }
                    diamond.color -> {
                        return showAllDiamondsFromFile().filter { eachDiamond ->
                            eachDiamond.color == categoryDiamond
                        }
                    }
                    diamond.cut -> {
                        return showAllDiamondsFromFile().filter { eachDiamond ->
                            eachDiamond.cut == categoryDiamond
                        }
                    }
                    diamond.country -> {
                        return showAllDiamondsFromFile().filter { eachDiamond ->
                            eachDiamond.country == categoryDiamond
                        }
                    }
                }
            }
            return null
        }

        fun getNewDiamondsToAdd(){
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
                JOptionPane.showMessageDialog(null, clarityCombo, "Select diamond clarity", JOptionPane.QUESTION_MESSAGE)
                newDiamond.clarity = clarityCombo.selectedItem.toString()

                // get color
                JOptionPane.showMessageDialog(null, colorCombo, "Select diamond color", JOptionPane.QUESTION_MESSAGE)
                newDiamond.color = colorCombo.selectedItem.toString()

                // get cut
                JOptionPane.showMessageDialog(null, cutCombo, "Select diamond cut", JOptionPane.QUESTION_MESSAGE)
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
                // get country
                JOptionPane.showMessageDialog(null, countriesCombo, "Select country of origin", JOptionPane.QUESTION_MESSAGE)
                newDiamond.country = countriesCombo.selectedItem.toString()

                listOfDiamondsToAdd.add(newDiamond)
                val selectedOption = JOptionPane.showConfirmDialog(null, "Dou you want to Add more diamonds?")
            } while (selectedOption == JOptionPane.YES_OPTION)

            addDiamondToDatabaseFile(listOfDiamondsToAdd)
            val stringOfDiamondsToShow =  createStringOfDiamonds(listOfDiamondsToAdd)
            showDiamondsInTextArea(stringOfDiamondsToShow)
        }

        fun findDiamondsByCategory(){
            do{
                var selectedOption = JOptionPane.showOptionDialog(null, "Diamonds catalog, please select an option",
                    "Diamonds EPN", 1,3,null,arrayOfSubmenu0,null )
                when (selectedOption) {
                    0 -> { // show all
                        val arrayOfAllDiamonds = showAllDiamondsFromFile()
                        val stringOfDiamondsToShow =  createStringOfDiamonds(arrayOfAllDiamonds)
                        showDiamondsInTextArea(stringOfDiamondsToShow)
                    }
                    1 -> { // filter by category
                        do {
                            var seletecOption = JOptionPane.showOptionDialog(null, "Filtering diamonds, please select an option",
                                "Diamonds EPN", 1,3,null,arryOfSubmenu1,null )
                            when(seletecOption){
                                0 -> { // by Name
                                    val name = JOptionPane.showInputDialog(null,"Diamond's Name: ") ?: ""
                                    showDiamondsFound(name)
                                }
                                1 -> { // by clarity
                                    JOptionPane.showMessageDialog(null, clarityCombo, "Select diamond clarity", JOptionPane.QUESTION_MESSAGE)
                                    showDiamondsFound(clarityCombo.selectedItem.toString())
                                }
                                2 -> { // by color
                                    JOptionPane.showMessageDialog(null, colorCombo, "Select diamond color", JOptionPane.QUESTION_MESSAGE)
                                    showDiamondsFound(colorCombo.selectedItem.toString())
                                }
                                3 -> { // by cut
                                    JOptionPane.showMessageDialog(null, cutCombo, "Select diamond cut", JOptionPane.QUESTION_MESSAGE)
                                    showDiamondsFound(cutCombo.selectedItem.toString())
                                }
                                4 -> { // by country
                                    val listOfAvailableCountries = filterAvailableCountries().toArray()
                                    val availableCountries = JComboBox<Any>(listOfAvailableCountries)
                                    JOptionPane.showMessageDialog(null, availableCountries, "Select one of the available countries of origin", JOptionPane.QUESTION_MESSAGE)
                                    showDiamondsFound(availableCountries.selectedItem.toString())
                                }
                                5 -> { // breaking loop
                                    seletecOption = 6
                                }
                            }
                        }while (seletecOption in 0..5)
                    }
                    2 -> { // finishing loop
                        selectedOption = 3
                    }
                }
            }while (selectedOption in 0..2)
        }

        fun modifyAnyDiamond(){
            val diamondName = JOptionPane.showInputDialog(null,"Diamond's Name: ").trim()
            val diamondToModify = searchDiamondByCategory(diamondName)
            if (diamondToModify != null) {
                removeAnyDiamondFromDBFile(diamondName, false)
                when(JOptionPane.showOptionDialog(null, "Modifying '$diamondName' diamond, please select an option",
                    "Diamonds EPN", 1,3,null,arrayOfSubmenu2,null )){
                    0 -> { // Change name
                        diamondToModify[0].name = JOptionPane.showInputDialog(null,"New Name: ")
                    }
                    1 -> { // Change clarity
                        JOptionPane.showMessageDialog(null, clarityCombo, "Select new Clarity:", JOptionPane.QUESTION_MESSAGE)
                        diamondToModify[0].clarity = clarityCombo.selectedItem.toString()
                    }
                    2 -> { // Change Color
                        JOptionPane.showMessageDialog(null, colorCombo, "Select new Color:", JOptionPane.QUESTION_MESSAGE)
                        diamondToModify[0].color = colorCombo.selectedItem.toString()
                    }
                    3 -> { // Change Cut
                        JOptionPane.showMessageDialog(null, cutCombo, "Select new Cut:", JOptionPane.QUESTION_MESSAGE)
                        diamondToModify[0].cut = cutCombo.selectedItem.toString()
                    }
                    4 -> { // Change carat
                        diamondToModify[0].carat = JOptionPane.showInputDialog(null,"New Carat: ")
                    }
                    5 -> { // Change price
                        diamondToModify[0].price = JOptionPane.showInputDialog(null,"New Price: ")
                    }
                    6 -> { // Change country
                        JOptionPane.showMessageDialog(null, countriesCombo, "New Country of origin:", JOptionPane.QUESTION_MESSAGE)
                        diamondToModify[0].country = countriesCombo.selectedItem.toString()
                    }
                }
                val stringOfDiamondsToShow = createStringOfDiamonds(diamondToModify as ArrayList<DiamondBDD>)
                showDiamondsInTextArea(stringOfDiamondsToShow)
                addDiamondToDatabaseFile(diamondToModify)
            }else{
                JOptionPane.showMessageDialog(null,"Diamond: '$diamondName' not found in database")
            }
        }

        fun removeAnyDiamondFromDBFile(diamondName: String, showChanges: Boolean){

                val diamondToDelete = searchDiamondByCategory(diamondName)
                val listOfDiamondsToSave = ArrayList<DiamondBDD>()
                if (diamondToDelete != null) {
                    val selectedOption = JOptionPane.showConfirmDialog(null,
                        "This action will remove '$diamondName' from database permanently !",
                        "Are you sure? :(",0,2)
                    if(selectedOption == JOptionPane.YES_OPTION){
                        showAllDiamondsFromFile().forEach {
                            if(it.name != diamondToDelete[0].name){
                                listOfDiamondsToSave.add(it)
                            }
                        }
                        // Show the new List without deleted diamond
                        val stringOfDiamondsToShow = createStringOfDiamonds(listOfDiamondsToSave)
                        val linesOfDiamondsToWriteinFile = createLinesOfDiamonds(listOfDiamondsToSave)
                        overwriteNewLinesToFile(linesOfDiamondsToWriteinFile)
                        if(showChanges){
                            showDiamondsInTextArea(stringOfDiamondsToShow)
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"'$diamondName' not found in database")
                }
        }

        private fun showDiamondsFound(categoryDiamond: String){
            if(categoryDiamond != ""){
                val arrayOfDiamondsToFind = searchDiamondByCategory(categoryDiamond)
                if(arrayOfDiamondsToFind != null) {
                    val stringOfDiamondsToShow = createStringOfDiamonds(arrayOfDiamondsToFind as ArrayList<DiamondBDD>)
                    showDiamondsInTextArea(stringOfDiamondsToShow)
                }else{
                        JOptionPane.showMessageDialog(null,"Diamond: '$categoryDiamond' not found in database")
                }
            }else{
                JOptionPane.showMessageDialog(null,"Please, enter a valid name")
            }
        }

        // Show results in JTextArea over JOptionPane
        private fun showDiamondsInTextArea(stringOfDiamonds: String){
            val textAreaForResults = JTextArea(stringOfDiamonds)
            val scrollPane = JScrollPane(textAreaForResults)
            textAreaForResults.font = Font("Tahoma", Font.PLAIN, 11)
            textAreaForResults.isEditable = false
            scrollPane.preferredSize = Dimension(230, 300)
            JOptionPane.showMessageDialog(null,scrollPane,"Available Diamonds !!!",1,null)
        }

        private fun filterAvailableCountries(): ArrayList<String>{
            val listOfAvailableCountries = ArrayList<String>()
            showAllDiamondsFromFile().forEach {diamond ->
                if(listOfAvailableCountries.indexOf(diamond.country) == -1){
                    listOfAvailableCountries.add(diamond.country)
                }
            }
            return listOfAvailableCountries
        }
    }
}