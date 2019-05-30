package com.example.a04_android_parselabel

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_parcelable.setOnClickListener {
            irAParcelable()
        }

        btn_Adapter.setOnClickListener{
            irAListaView()
        }
    }

    fun irAParcelable(){
        val intentExplicito = Intent(
            this,
            Parcelable::class.java
        )
        val criss = Usuario("Criss",25, Date(),12.12)
        intentExplicito.putExtra("usuario",criss)
            /////////
        val cachetes = Mascota("cachetes",criss)
        intentExplicito.putExtra("mascota",cachetes)
        startActivity(intentExplicito)
    }

    fun irAListaView(){
        val intentExplicito = Intent(
            this,
            ListViewActivity::class.java
        )
        startActivity(intentExplicito)
    }
}
