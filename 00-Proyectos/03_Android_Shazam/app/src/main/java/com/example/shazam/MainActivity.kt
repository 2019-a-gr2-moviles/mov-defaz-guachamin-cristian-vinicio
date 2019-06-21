package com.example.shazam

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_miShazam.setOnClickListener{
            irReciclerView()
        }

        btn_shazam.setOnClickListener {
            escucharCancion()
        }
    }

    private fun irReciclerView(){
        val intentExplicito = Intent(
            this,
            MusicaRecyclerView::class.java
        )
        startActivity(intentExplicito)
    }

    private fun escucharCancion(){
        val intentExplicito = Intent(
            this,
            ActividadEscucha::class.java
        )
        startActivity(intentExplicito)
    }
}
