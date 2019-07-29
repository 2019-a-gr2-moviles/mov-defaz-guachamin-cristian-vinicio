package com.example.diamanond_manager

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.setOnClickListener {
            validarSesion()
        }
    }


    private fun validarSesion(){
        val nombreUsuario = txt_user.text.toString().trim()
        val contrasenia = txt_pass.text.toString().trim()
        if(nombreUsuario == "" || contrasenia == ""){
            Toast.makeText(applicationContext,
                "Error de inicio de sesión :(",
                Toast.LENGTH_SHORT).show()
        }else{
            DatosUsuario.crearUsuarioActual(nombreUsuario,contrasenia)
            irAdiamntePrueba()
        }
    }

    private fun irAdiamntePrueba(){
        startActivity(
            Intent(this,
                DiamantePrueba1::class.java )
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }
}
