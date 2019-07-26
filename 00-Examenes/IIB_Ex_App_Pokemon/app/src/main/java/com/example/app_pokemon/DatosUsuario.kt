package com.example.app_pokemon

class DatosUsuario (
    var nombreusuario: String,
    var contrasenia: String
){
    companion object{

        private var nuevoUsuario = DatosUsuario("","")
        fun crearUsuarioActual(usuario: String, pass: String){
            nuevoUsuario = DatosUsuario(usuario,pass)
        }

        fun obtenerUsuarioActual(): DatosUsuario{
            return nuevoUsuario
        }
    }
}




