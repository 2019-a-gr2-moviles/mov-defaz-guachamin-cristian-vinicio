package com.example.diamanond_manager

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_formulario_insercion.*

class FormularioInsercion : AppCompatActivity() {

    // Arreglo de atributos
    private val arregloClaridad = arrayListOf<String>()
    private val arregloColor = arrayListOf<String>()
    private val arregloCorte = arrayListOf<String>()
    private val arregloPaises = arrayListOf<String>()
    val diamanteNuevo = Diamante()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_insercion)
        cargarDatosASpinners()

        txt_titulo_ins.setOnClickListener{
            inicializarSpinners()
        }

        btn_guardar.setOnClickListener {
            construirNuevoDiamante()
        }
    }

    private fun cargarDatosASpinners(){
        arregloClaridad.add("Nivel de claridad:")

        CargadorSpinners.listarDatosClaridad().forEach {
            arregloClaridad.add(it.clarityName)
        }

        arregloColor.add("Nivel de color:")
        CargadorSpinners.listarDatosColor().forEach {
            arregloColor.add(it.colorName)
        }

        arregloCorte.add("Tipo de Corte")
        CargadorSpinners.listarDatosCorte().forEach {
            arregloCorte.add(it.cutName)
        }

        arregloPaises.add("País de Origen:")
        CargadorSpinners.listarDatosPaises().forEach {
            arregloPaises.add(it.countryName)
        }
    }

    private fun inicializarSpinners(){
        spinner_claridad.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arregloClaridad)
        spinner_color.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arregloColor)
        spinner_corte.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arregloCorte)
        spinner_paises.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arregloPaises)
        spinner_claridad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) { }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
                if (posicion > 0) {
                    val id = CargadorSpinners
                        .buscarID("claridad", p0?.getItemAtPosition(posicion).toString())
                    Log.i("http","Claridad $id")
                    diamanteNuevo.fkClarity.id = id
                }
            }
        }

        spinner_color.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) { }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
                if (posicion > 0) {
                    val id = CargadorSpinners
                        .buscarID("color", p0?.getItemAtPosition(posicion).toString())
                    Log.i("http","Color $id")
                    diamanteNuevo.fkColor.id = id
                }
            }
        }

        spinner_corte.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) { }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
                if (posicion > 0) {
                    val id = CargadorSpinners
                        .buscarID("corte", p0?.getItemAtPosition(posicion).toString())
                    Log.i("http","Corte $id")
                    diamanteNuevo.fkCut.id = id
                }
            }
        }

        spinner_paises.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) { }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
                if (posicion > 0) {
                    val id = CargadorSpinners
                        .buscarID("pais", p0?.getItemAtPosition(posicion).toString())
                    Log.i("http","Pais $id")
                    diamanteNuevo.fkCountry.id = id
                }
            }
        }
    }

    private fun construirNuevoDiamante(){

        val nombre = txt_nombre_diamante.text.toString().trim()
        val quilate = txt_quilate.text.toString().trim()
        val precio = txt_precio.text.toString().trim()
        // Validar que no esté en cero la posicion de los spinners o campos vacios
        if (nombre == "" || quilate == "" || precio == ""
            || spinner_claridad.selectedItemPosition < 1 || spinner_color.selectedItemPosition < 1
            || spinner_corte.selectedItemPosition < 1 || spinner_paises.selectedItemPosition < 1) {
            Toast.makeText(
                applicationContext,
                "Rellene todos los campos",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            if(quilate.toDouble() > 5.00){
                Toast.makeText(
                    applicationContext,
                    "Valor de quilate fuera de rango (2.01 - 5.00)",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                val diamanteACrear: Array<Any> = arrayOf(
                    nombre,
                    quilate.toDouble(),
                    precio.toDouble(),
                    diamanteNuevo.fkClarity.id,
                    diamanteNuevo.fkColor.id,
                    diamanteNuevo.fkCountry.id,
                    diamanteNuevo.fkCut.id
                )
                conctarConBackendPost(diamanteACrear)
            }
        }
    }

    private fun conctarConBackendPost(diamanteACrear: Array<Any> ){

        val diamanteAInsertar = listOf(
            "nombreDiamante" to diamanteACrear[0],
            "caratDiamante" to diamanteACrear[1],
            "precioDiamante" to diamanteACrear[2],
            "fkClarity" to diamanteACrear[3],
            "fkColor" to diamanteACrear[4],
            "fkCountry" to diamanteACrear[5],
            "fkCut" to diamanteACrear[6]
        )
        diamanteAInsertar.forEach{Log.i("http",it.toString())}
        val url = ServidorBackend.getURL("diamond")
        Log.i("http", "Mi URL: $url")
        url.httpPost(diamanteAInsertar)
            .responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http", "Error insertando: $error")
                    }
                    is Result.Success -> {
                        val diamanteString = result.get()
                        Log.i("http", "Diamante creado: $diamanteString")
                        volverALista()
                    }
                }
            }
    }

    private fun volverALista(){
        val intentExplicito = Intent(
            this,
            RecyclerViewDiamantes::class.java
        )
        intentExplicito.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intentExplicito.putExtra("diamantecreado",
            "${DatosUsuario.obtenerUsuarioActual().nombreusuario} ha creado un nuevo diamante")
        startActivity(intentExplicito)
    }
}
