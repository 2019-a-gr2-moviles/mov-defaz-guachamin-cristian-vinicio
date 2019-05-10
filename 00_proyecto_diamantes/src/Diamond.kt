import java.io.File
import java.io.FileWriter
import java.io.IOException

// Diamond class
class DiamondBDD(
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
        private val diamondList1 = arrayListOf<DiamondBDD>()

        // Adding arraylist od Diamonds to Diamond BD
        fun addDiamond(diamond: ArrayList<DiamondBDD>) {
            var lineToFile = ""
            diamond.forEach { eachDiamond ->
                diamondList1.add(eachDiamond)
                lineToFile+="${eachDiamond.name};${eachDiamond.clarity};${eachDiamond.color};" +
                        "${eachDiamond.cut};${eachDiamond.carat};${eachDiamond.price}\n"
            }
            writeLineToFile(lineToFile)
            lineToFile = "" // clean line
        }

        private fun writeLineToFile(lineToFile: String){
            try {
                val file = FileWriter("src/texto.csv",true)
                file.write(lineToFile)
                file.close()
            }catch (e: IOException){
                println("Cannot read")
            }
        }

        fun showAllDiamondsFromFile(): ArrayList<DiamondBDD>{
            val listOfDiamonds = ArrayList<DiamondBDD>()
            try {
                val diamondLinesFromFile = File("src/texto.csv").readLines()
                diamondLinesFromFile.forEach{ diamondLine ->
                    val arrayDiamond = diamondLine.split(";")
                    val newDiamondFromLine = DiamondBDD(arrayDiamond[0],arrayDiamond[1],arrayDiamond[2],arrayDiamond[3],arrayDiamond[4],arrayDiamond[5])
                    listOfDiamonds.add(newDiamondFromLine)
                }
            }catch (e: IOException){
                println("Cannot read")
            }
            return listOfDiamonds
        }

        fun searchDiamondByCategory(categoryDiamond: String): List<DiamondBDD>? {
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
                }
            }
            return null
        }
    }
}