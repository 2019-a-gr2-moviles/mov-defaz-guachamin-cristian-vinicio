package com.example.app_pokemon

class ClaseEntrenadores (
    var id: Int,
    var nombreEntrenador: String,
    var apellidoEntrenador: String,
    var fechaNac: String,
    var medallas: Int,
    var campeonActual: Boolean
){

    override fun toString(): String {
        return "ClaseEntrenadores(id=$id, nombreEntrenador='$nombreEntrenador'," +
                " apellidoEntrenador='$apellidoEntrenador', fechaNac='$fechaNac', " +
                "medallas=$medallas, campeonActual=$campeonActual)"
    }

}

