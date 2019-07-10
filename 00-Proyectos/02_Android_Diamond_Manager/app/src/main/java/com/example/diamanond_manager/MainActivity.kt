package com.example.diamanond_manager

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.setOnClickListener {
            val usr = txt_user.text.toString()
            val pass = txt_pass.text.toString()
            val user = User(usr,pass)
            irAdiamntePrueba(user)
        }
    }

    private fun irAdiamntePrueba(user: User){
        startActivity(Intent(this,DiamantePrueba1::class.java )
            .putExtra("user",user).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }
}
