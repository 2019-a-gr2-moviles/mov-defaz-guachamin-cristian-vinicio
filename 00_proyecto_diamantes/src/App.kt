import javax.swing.*

val arrayOfMainMenu: Array<String> = arrayOf("New","Find any diamond","Modify","Delete","Exit")
val icon = ImageIcon("src/diamond.png")

fun main() {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()) // L&F
    do {   // Main menu loop
    val selectedOption = JOptionPane.showOptionDialog(null, "Welcome to Diamonds EPN, please select an option",
        "Diamonds EPN", 1,3,icon,arrayOfMainMenu,null ) // menu options
        when (selectedOption) {
            0 -> {
                DiamondBDD.getNewDiamondsToAdd()
            }
            1 -> {
                DiamondBDD.findDiamondsByCategory()
            }
            2 -> {
                DiamondBDD.modifyAnyDiamond()
            }
            3 -> {
                val nameOfDiamondToDelete = JOptionPane.showInputDialog(null,"Name of the Diamond to remove: ")
                DiamondBDD.removeAnyDiamondFromDBFile(nameOfDiamondToDelete, true)
            }
            4 -> {
                System.exit(0)
            }
        }
    }while (selectedOption in 0..4)
}



