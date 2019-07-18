package com.example.json_http

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_http.setOnClickListener {
            irAIntentHttp()
        }

        btn_mapa.setOnClickListener {
            irAMapsActivity()
        }

        btn_cicloVida.setOnClickListener {
            irACicloDeVida()
        }
        btn_fragmento.setOnClickListener {
            irAFragmentos()
        }
    }

    private fun irAIntentHttp(){
        val intentExplicito = Intent(
            this,
            ConexionHttpActivity::class.java
        )
        startActivity(intentExplicito)
    }

    private fun irAMapsActivity(){
        val intentExplicito = Intent(
            this,
            MapsActivity::class.java
        )
        startActivity(intentExplicito)
    }

    private fun irACicloDeVida(){
        val intentExplicito = Intent(
            this,
            CicloVidaActivity::class.java
        )
        startActivity(intentExplicito)
    }

    private fun irAFragmentos(){
        val intentExplicito = Intent(
            this,
            FragmentosActivity::class.java
        )
        startActivity(intentExplicito)
    }
}
