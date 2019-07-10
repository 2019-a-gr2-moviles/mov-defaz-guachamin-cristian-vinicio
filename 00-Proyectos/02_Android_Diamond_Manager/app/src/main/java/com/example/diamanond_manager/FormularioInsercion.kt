package com.example.diamanond_manager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_formulario_insercion.*

class FormularioInsercion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_insercion)
        // Arreglo de atributos
        val diamanteNuevo = Diamante()
        var posClaridad = 0
        var posColor = 0
        var posCorte = 0
        var posPais = 0


        val arregloClaridad = arrayListOf<String>()
        arregloClaridad.add("Nivel de claridad:")
        ConexionesHTTP.listarDatosClaridad().forEach {
            arregloClaridad.add(it.clarityName)
        }

        val arregloColor = arrayListOf<String>()
        arregloColor.add("Nivel de color:")
        ConexionesHTTP.listarDatosColor().forEach {
            arregloColor.add(it.colorName)
        }

        val arregloCorte = arrayListOf<String>()
        arregloCorte.add("Tipo de Corte")
        ConexionesHTTP.listarDatosCorte().forEach {
            arregloCorte.add(it.cutName)
        }

        val arregloPaises = arrayListOf<String>()
        arregloPaises.add("País de Origen:")
        ConexionesHTTP.listarDatosPaises().forEach {
            arregloPaises.add(it.countryName)
        }

        //Adapter for spinners
        spinner_claridad.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arregloClaridad)
        spinner_color.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arregloColor)
        spinner_corte.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arregloCorte)
        spinner_paises.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arregloPaises)

        //item selected listener for spinner
        spinner_claridad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
                if (posicion > 0) {
                    val id = ConexionesHTTP
                        .buscarID("claridad", p0?.getItemAtPosition(posicion).toString())
                    diamanteNuevo.fkClarity.id = id!!
                    posClaridad = posicion
                }
            }
        }

        spinner_color.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
                if (posicion > 0) {
                    val id = ConexionesHTTP
                        .buscarID("color", p0?.getItemAtPosition(posicion).toString())
                    diamanteNuevo.fkColor.id = id!!
                    posColor = posicion
                }
            }
        }

        spinner_corte.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
                if (posicion > 0) {
                    val id = ConexionesHTTP
                        .buscarID("corte", p0?.getItemAtPosition(posicion).toString())
                    diamanteNuevo.fkCut.id = id!!
                    posCorte = posicion
                }
            }
        }

        spinner_paises.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
                if (posicion > 0) {
                    val id = ConexionesHTTP
                        .buscarID("pais", p0?.getItemAtPosition(posicion).toString())
                    diamanteNuevo.fkCountry.id = id!!
                    posPais = posicion
                }
            }
        }

        btn_guardar.setOnClickListener {
            // Creando diamante a insertar
            val nombre = txt_nombre_diamante.text.toString()
            val quilate = txt_quilate.text.toString()
            val precio = txt_precio.text.toString()
            // Validar que no esté en cero la posicion de los spinners o campos vacios
            if (nombre == "" || quilate == "" || precio == "" ||
                posClaridad == 0 || posColor == 0 || posCorte == 0 || posPais == 0) {
                Toast.makeText(
                    applicationContext,
                    "Rellene todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val diamanteAInsertar = listOf(
                    "nombreDiamante" to nombre,
                    "caratDiamante" to quilate.toFloat(),
                    "precioDiamante" to precio.toFloat(),
                    "fkClarity" to diamanteNuevo.fkClarity.id,
                    "fkColor" to diamanteNuevo.fkColor.id,
                    "fkCountry" to diamanteNuevo.fkCountry.id,
                    "fkCut" to diamanteNuevo.fkCut.id
                )
                Toast.makeText(
                    applicationContext,
                    diamanteAInsertar.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                Log.i("hhtp", diamanteAInsertar.toString())
            }
        }
    }
}
