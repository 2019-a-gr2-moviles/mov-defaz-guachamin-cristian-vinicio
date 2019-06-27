package com.example.diamanond_manager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_view_prueba.*

class ListViewPrueba : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_prueba)

        val lp = ArrayList<String>()
        lp.add("1.  On Sunday   Ester Peony")
        lp.add("2.  Bambola     Betta Lemme")
        lp.add("3.  Lost On You     JP")
        lp.add("4.  Money       Mia Vaile")

        val lvAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            lp
        )
        lv_list.adapter = lvAdapter

        lv_list.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            Toast.makeText(applicationContext,
                "Parámetro escogido: ${parent.getItemAtPosition(position)} \nPosición: $position",
                Toast.LENGTH_SHORT).show()
        }


    }


}
