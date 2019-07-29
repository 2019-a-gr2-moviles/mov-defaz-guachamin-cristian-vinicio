package com.example.diamanond_manager

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_recycler_view_prueba.*

class RecyclerViewDiamantes : AppCompatActivity() {

    private var bddDiamantesRecyclerView = arrayListOf<DiamanteParcelable>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_prueba)
        bddDiamantesRecyclerView = transformarDiamantesAParcelable()
        mostrarToastDeCambios()

        txv_rv_titulo.setOnClickListener {
            iniciarRecyclerView(
                bddDiamantesRecyclerView,
                this,
                rv_lista_diamantes
            )
        }

        btn_agregar_diamante.setOnClickListener {
            irAFormularioDeInsercion()
        }
    }

    private fun mostrarToastDeCambios(){

        val mensajeDeInsercion =  intent?.getStringExtra("diamantecreado")
        val mensajeDeActualizacion =  intent?.getStringExtra("diamanteEditado")
        val mensajeDeEliminacion =  intent?.getStringExtra("diamanateEliminado")

        if(mensajeDeInsercion != null){
            Toast.makeText(
                applicationContext,
                mensajeDeInsercion,
                Toast.LENGTH_SHORT
            ).show()
        }
        if(mensajeDeActualizacion != null){
            Toast.makeText(
                applicationContext,
                mensajeDeActualizacion,
                Toast.LENGTH_SHORT
            ).show()
        }
        if(mensajeDeEliminacion != null){
            Toast.makeText(
                applicationContext,
                mensajeDeEliminacion,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun transformarDiamantesAParcelable(): ArrayList<DiamanteParcelable> {
        val listaDiamantes = arrayListOf<DiamanteParcelable>()
        val url = ServidorBackend.getURL("diamond")
        Log.i("http", "Mi URL: $url")
        url.httpGet().responseString { _, _, result ->
            when (result) {
                is Result.Failure -> {
                    val error = result.getException()
                    Log.i("http", "Error listando diamantes: $error")
                }
                is Result.Success -> {
                    val data = result.get()
                    val datosParseados = Klaxon().parseArray<Diamante>(data)
                    datosParseados?.forEach {
                        val diamante = DiamanteParcelable(
                            it.id,
                            it.nombreDiamante,
                            it.caratDiamante,
                            it.precioDiamante,
                            it.fkClarity.clarityName,
                            it.fkCut.cutName,
                            it.fkColor.colorName,
                            it.fkCountry.countryName,
                            it.fkCut.id
                        )
                        listaDiamantes.add(diamante)
                        Log.i("http", "${it.id} - ${it.nombreDiamante}")
                    }
                }
            }
        }
        return listaDiamantes
    }

    private fun iniciarRecyclerView(
        lista: List<DiamanteParcelable>,
        actividad: RecyclerViewDiamantes,
        recyclerView: RecyclerView
    ) {
        val adaptadorDiamante = AdaptadorDiamante(
            lista,
            actividad,
            recyclerView
        )
        recyclerView.adapter = adaptadorDiamante
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(actividad)
        adaptadorDiamante.notifyDataSetChanged()
    }

    fun verDetalleDiamante(nombreDiamante: String) {
        bddDiamantesRecyclerView.forEach {
            if (it.nombre == nombreDiamante) {
                val intentExplicito = Intent(
                    this,
                    VerDetalleDiamante::class.java
                )
                intentExplicito.putExtra("diamante", it)
                startActivity(intentExplicito)
            }
        }
    }

    fun enviarDatosDiamanteAEditar(nombreDiamante: String) {
        bddDiamantesRecyclerView.forEach {
            if (it.nombre == nombreDiamante) {
                val intentExplicito = Intent(
                    this,
                    FormularioEdicion::class.java
                )
                intentExplicito.putExtra("diamante", it)
                Log.i("http", "Datos a editar: $it")
                startActivity(intentExplicito)
            }
        }
    }

    fun borrarDiamante(nombreDiamante: String) {
        var idDiamanteBuscado = 0
        bddDiamantesRecyclerView.forEach {
            if (it.nombre == nombreDiamante) {
                idDiamanteBuscado = it.id
            }
        }
        val url = ServidorBackend.getURL("diamond/$idDiamanteBuscado")
        Log.i("http", "Mi URL: $url")
        url.httpDelete().responseString { _, _, result ->
            when (result) {
                is Result.Failure -> {
                    val error = result.getException()
                    Log.i("http", "Error borrando diamante: $error")
                }
                is Result.Success -> {
                    startActivity(intent.putExtra("diamanateEliminado",
                        "${DatosUsuario.obtenerUsuarioActual().nombreusuario} eliminó un diamante"))
                    finish()
                }
            }
        }
    }

    private fun irAFormularioDeInsercion() {
        val intentExplicito = Intent(
            this,
            FormularioInsercion::class.java
        )
        startActivity(intentExplicito)
    }

}

