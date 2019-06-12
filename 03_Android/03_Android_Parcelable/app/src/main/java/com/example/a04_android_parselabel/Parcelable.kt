package com.example.a04_android_parselabel

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class Parcelable : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcelable)

        val criss: Usuario? = intent.getParcelableExtra<Usuario>("usuario")
        Log.i("parcelable","Nombre ${criss?.nombre}")
        Log.i("parcelable","Edad ${criss?.edad}")
        Log.i("parcelable","Fecha ${criss?.fechaNacimiento}")
        Log.i("parcelable","Sueldo ${criss?.sueldo}")

        val criss2: Mascota? = intent.getParcelableExtra<Mascota>("mascota")
        Log.i("parcelable","Nombre ${criss2?.nombre}")
        Log.i("parcelable","Duenio ${criss2?.duenio}")
    }

    fun regresarAmenu(){
        val intentExplicito = Intent (
            this,
            MainActivity::class.java
        )
        startActivity(intentExplicito)
    }
}
