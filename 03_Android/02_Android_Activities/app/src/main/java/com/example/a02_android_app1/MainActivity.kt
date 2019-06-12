package com.example.a02_android_app1

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        btn_actividad_dos.setOnClickListener{
           // this.irAActividadDos()
            irAActividadDos()
        }
    }

    private fun irAActividadDos(){
        val intent = Intent(
            this,
            Actividad_Dos::class.java
        )
        // elimina la actividad de la pila
       // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP /*or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK*/)
       // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        /*
        * FLAG_ACTIVITY_CLEAR_TOP no funciona cuando se tiene menús y submenús.
        * */

        intent.putExtra("nombre","Criss") // enviar datos de tipo primitivos
        intent.putExtra("edad","25")
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
