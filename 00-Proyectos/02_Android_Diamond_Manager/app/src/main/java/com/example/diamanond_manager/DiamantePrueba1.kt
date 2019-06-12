package com.example.diamanond_manager

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_diamante_prueba1.*

class DiamantePrueba1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diamante_prueba1)
        val user = intent.getParcelableExtra<User>("user")

        Toast.makeText(applicationContext,
            user.toString(),
            Toast.LENGTH_SHORT).show()

        btn_gallery.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btn_manager.setOnClickListener {
            startActivity(Intent(this, ListViewPrueba::class.java))
        }
    }
}
