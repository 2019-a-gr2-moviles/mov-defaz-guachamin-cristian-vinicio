package com.example.examen_app_pokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_2.*
import kotlinx.android.synthetic.main.activity_crear_entrenador.*
import android.widget.Toast



class CrearEntrenador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_entrenador)
        val parentLayout = findViewById<View>(android.R.id.content)
        val usuario: Usuario? = intent.getParcelableExtra("usuario")
        mostrarSnackbar(parentLayout,usuario?.nombreUsuario!!)

        btn_guardar.setOnClickListener{
           // crearEntrenador()
           // mostrarSnackbar(it, crearEntrenador())
            Toast.makeText(
                applicationContext,
                crearEntrenador(usuario), Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun crearEntrenador(user: Usuario): String{
        val nombres = txt_nom_entrenador.text.toString()
        val apellidos = txt_ape_entrenador.text.toString()
     //   val fecha = Entrenador.retornarFecha(txt_fechaNac_Entrenador.text.toString())
        val fecha = Entrenador.retornarFecha(txt_fechaNac_Entrenador.text.toString())
        val medallas = Integer.valueOf(lbl_num_medallas.text.toString())
        // Agregando nuevo entrenador:
        Entrenador.agregarEntrenador(nombres,apellidos,medallas,fecha)
        // Toast
       return "${user.nombreUsuario} ha creado un Nuevo entrenador: " +
               "\nNombre: $nombres \nApellidos: $apellidos \nFecha: $fecha \nMedallas: $medallas"
    }
    // Mostrar un snckbar con el nombre y la posición
    private fun mostrarSnackbar(view: View, texto:String){
        Snackbar.make(view, texto, Snackbar.LENGTH_LONG)
            .setAction("Action",null).show()
    }

    fun eliminarEntrenador(){

    }
}
