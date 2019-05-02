// Clas
class DiamanteBDD(
    var carat: Double,
    var cut: String,
    var color: String,
    var clarity: String){

    companion object {
        val listaDiamantes = arrayListOf<DiamanteBDD>()

        // Agregar una lista de diamantes a la BDD
        fun addDiamante(diamante: ArrayList<DiamanteBDD>): ArrayList<DiamanteBDD> {
            diamante.forEach {
                listaDiamantes.add(it)
            }
            //listaDiamantes.add(diamante)
            return listaDiamantes
        }

        // Actualizr un diamante

    }
}