package com.example.a04_android_parselabel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)


        val listaNombres = arrayListOf<String>()
        listaNombres.add("Criss")
        listaNombres.add("Daniela")
        listaNombres.add("Viviana")
        listaNombres.add("Franklin")
        listaNombres.add("Jordana")

        val adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,listaNombres)

        lv_ejemplo.adapter = adapter

        // Mostrar la psoicion en Logcat,
        // por cada pulsacion sobre un item de la lista
        lv_ejemplo.onItemClickListener = AdapterView
            .OnItemClickListener { parent, view, position, id ->
                val nombre = parent.getItemAtPosition(position)
                Log.i("list-view","$nombre en la Posicion $position")
                // Muestra la info en el Snackbar
                mostrarSnackbar(view, "$nombre en la Posicion $position")
            }

    }
    // Mostrar un snckbar con el nombre y la posición
    fun mostrarSnackbar(view: View, texto:String){
        Snackbar.make(view, texto, Snackbar.LENGTH_LONG)
            .setAction("Action",null).show()
        //Snackbar.make
    }
}
