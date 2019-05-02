import javax.swing.JOptionPane
import javax.swing.UIManager

fun main() {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

    val arreglo: Array<String> = arrayOf("Nuevo","Modificar","Eliminar","Salir")
    val x = JOptionPane.showOptionDialog(null, "wenaaas !","xd", 1,2,null,arreglo,null )
    println("Seleccion: $x")

}