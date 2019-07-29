package com.example.diamanond_manager

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_formulario_edicion.*

class FormularioEdicion : AppCompatActivity() {

    private val arregloClaridad = arrayListOf<String>()
    private val arregloColor = arrayListOf<String>()
    private val arregloCorte = arrayListOf<String>()
    private val arregloPaises = arrayListOf<String>()
    val diamanteNuevo = Diamante()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_edicion)
        cargarDatosASpinners()
        txt_titulo_edit.setOnClickListener {
            inicializarSpinners()
            cargarDatosDiamante()
        }
        btn_guardar_edit.setOnClickListener {
            construirNuevosDatosDiamante()
        }

    }

    private fun cargarDatosASpinners() {
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

    private fun inicializarSpinners() {
        // Spinners
        spinner_claridad_edit.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arregloClaridad)
        spinner_color_edit.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arregloColor)
        spinner_corte_edit.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arregloCorte)
        spinner_paises_edit.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arregloPaises)

        spinner_claridad_edit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
                if (posicion > 0) {
                    val id = CargadorSpinners
                        .buscarID("claridad", p0?.getItemAtPosition(posicion).toString())
                    Log.i("http","Claridad $id")
                    diamanteNuevo.fkClarity.id = id
                }
            }
        }

        spinner_color_edit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
                if (posicion > 0) {
                    val id = CargadorSpinners
                        .buscarID("color", p0?.getItemAtPosition(posicion).toString())
                    Log.i("http","Color $id")
                    diamanteNuevo.fkColor.id = id
                }
            }
        }

        spinner_corte_edit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
                if (posicion > 0) {
                    val id = CargadorSpinners
                        .buscarID("corte", p0?.getItemAtPosition(posicion).toString())
                    Log.i("http","Corte $id")
                    diamanteNuevo.fkCut.id = id
                }
            }
        }

        spinner_paises_edit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
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

    private fun cargarDatosDiamante() {
        val diamanteDeItemAEditar: DiamanteParcelable? = this.intent.getParcelableExtra("diamante")
        if (diamanteDeItemAEditar != null) {
            diamanteNuevo.id = diamanteDeItemAEditar.id
        }
        txt_nombre_diamante_edit.setText(diamanteDeItemAEditar?.nombre)
        txt_quilate_edit.setText(diamanteDeItemAEditar?.quilate.toString())
        txt_precio_edit.setText(diamanteDeItemAEditar?.precio.toString())
        spinner_claridad_edit.setSelection(arregloClaridad.indexOf(diamanteDeItemAEditar?.claridad))
        spinner_color_edit.setSelection(arregloColor.indexOf(diamanteDeItemAEditar?.color))
        spinner_corte_edit.setSelection(arregloCorte.indexOf(diamanteDeItemAEditar?.corte))
        spinner_paises_edit.setSelection(arregloPaises.indexOf(diamanteDeItemAEditar?.pais))
    }

    private fun construirNuevosDatosDiamante() {

        val nombre = txt_nombre_diamante_edit.text.toString().trim()
        val quilate = txt_quilate_edit.text.toString().trim()
        val precio = txt_precio_edit.text.toString().trim()
        // Validar que no esté en cero la posicion de los spinners o campos vacios
        if (nombre == "" || quilate == "" || precio == ""
            || spinner_claridad_edit.selectedItemPosition < 1 || spinner_color_edit.selectedItemPosition < 1
            || spinner_corte_edit.selectedItemPosition < 1 || spinner_paises_edit.selectedItemPosition < 1
        ) {
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
                val diamanteAEditar: Array<Any> = arrayOf(
                    nombre,
                    quilate.toDouble(),
                    precio.toDouble(),
                    diamanteNuevo.fkClarity.id,
                    diamanteNuevo.fkColor.id,
                    diamanteNuevo.fkCountry.id,
                    diamanteNuevo.fkCut.id
                )
                conectarConBackendPut(diamanteAEditar)
            }
        }
    }

    private fun conectarConBackendPut(arrDiamante: Array<Any>) {
        val diamanteAActualizar = listOf(
            "nombreDiamante" to arrDiamante[0],
            "caratDiamante" to arrDiamante[1],
            "precioDiamante" to arrDiamante[2],
            "fkClarity" to arrDiamante[3],
            "fkColor" to arrDiamante[4],
            "fkCountry" to arrDiamante[5],
            "fkCut" to arrDiamante[6]
        )
        diamanteAActualizar.forEach{Log.i("http",it.toString())}
       // val idDiamante = CargadorSpinners.buscarID("diamante", arrDiamante[0].toString())
        val url = ServidorBackend.getURL("diamond/${diamanteNuevo.id}")
        Log.i("http", "Mi URL: $url")
        url.httpPut(diamanteAActualizar)
            .responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http", "Error actualizando: $error")
                    }
                    is Result.Success -> {
                        val empresaString = result.get()
                        Log.i("http", "Mensaje: $empresaString")
                        volverALista()
                    }
                }
            }
    }

    private fun volverALista() {
        val intentExplicito = Intent(
            this,
            RecyclerViewDiamantes::class.java
        )
        intentExplicito.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intentExplicito.putExtra("diamanteEditado",
            "${DatosUsuario.obtenerUsuarioActual().nombreusuario} actualizó un diamante")
        startActivity(intentExplicito)
    }
}
