package com.example.app_pokemon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login_log.setOnClickListener {
            validarSesion()
        }

    }

    private fun validarSesion(){
        val nombreUsuario = txv_login_usuario.text.toString()
        val contrasenia = txv_login_pass.text.toString()
        if(nombreUsuario == "" || contrasenia == ""){
            Toast.makeText(applicationContext,
               "Error de inicio de sesión :(",
                Toast.LENGTH_SHORT).show()
        }else{
            DatosUsuario.crearUsuarioActual(nombreUsuario,contrasenia)
            irAMenuPrincipal()
        }
    }

    private fun irAMenuPrincipal(){
        startActivity(
            Intent(this,
                MenuPrincipal::class.java )
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }




}
