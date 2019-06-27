package com.example.diamanond_manager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import kotlinx.android.synthetic.main.activity_formulario_insercion.*


class FormularioInsercion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_insercion)

        val spinnerArray = ArrayList<String>()
        spinnerArray.add("Flawless")
        spinnerArray.add("Colourless")

        //Adapter for spinner
        spin_claridad.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray)

        //item selected listener for spinner
        spin_claridad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@FormularioInsercion, spinnerArray[p2], LENGTH_LONG).show()
            }

        }
    }
}
