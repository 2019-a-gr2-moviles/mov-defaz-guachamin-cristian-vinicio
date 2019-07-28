package com.example.app_pokemon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.github.kittinunf.fuel.httpGet
import com.beust.klaxon.Klaxon
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_list_view_entrenador.*

class ListViewEntrenador : AppCompatActivity() {
    private var bddEntrenadores = arrayListOf<ClaseEntrenadores>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_entrenador)
        bddEntrenadores = cargarEntrenadores()
        mostrarToastConUsuario()

        txv_lv_entrenador.setOnClickListener {
            val listaEntrenadores = arrayListOf<String>()
            bddEntrenadores.forEach {
                listaEntrenadores.add("${it.id} - ${it.nombreEntrenador} ${it.apellidoEntrenador}")
            }
            crearAdaptador(listaEntrenadores)
        }
    }

    private fun mostrarToastConUsuario(){
        val accionRealizada = intent.getStringExtra("entrenadorCreado")
        if(accionRealizada != null){
            Toast.makeText(applicationContext,
                accionRealizada,
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun crearAdaptador(lista: ArrayList<String>){

        val adaptadorEntrenador = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            lista
        )
        lv_entrenadores.adapter = adaptadorEntrenador
        lv_entrenadores.onItemClickListener = AdapterView
            .OnItemClickListener { parent, _, position, _ ->
                val itemSeleccionado = parent.getItemAtPosition(position).toString()
                buscarEntrenadorPorId(itemSeleccionado.split("-")[0].trim().toInt())?.let {
                    solicitarDetallesDeEntrenador(
                        it
                    )
                }
                Toast.makeText(
                    applicationContext,
                    itemSeleccionado,
                    Toast.LENGTH_SHORT
                ).show()
            }
        adaptadorEntrenador.notifyDataSetChanged()
    }


    private fun cargarEntrenadores(): ArrayList<ClaseEntrenadores> {
        val url = ClaseServidorBackend.getURL("entrenador")
        Log.i("http", "Mi URL: $url")
        val entrenadores = arrayListOf<ClaseEntrenadores>()
        url.httpGet().responseString {_, _, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http", "Error get entrenadores: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get()
                    Log.i("http", data)
                    val datosParseados = Klaxon().parseArray<ClaseEntrenadores>(data)
                    datosParseados?.forEach{
                        entrenadores.add(it)
                        Log.i("http", "${it.id} - ${it.nombreEntrenador}")

                    }
                }
            }
        }
        return entrenadores
    }

    private fun solicitarDetallesDeEntrenador(entrenador: ClaseEntrenadores){
        val datosEntrenador = ClaseEntrenadorParcelable(
            entrenador.id,
            entrenador.nombreEntrenador,
            entrenador.apellidoEntrenador,
            entrenador.fechaNac,
            entrenador.medallas,
            entrenador.campeonActual
        )
        val intentExplicito = Intent(
            this,
            VerEntrenador::class.java
        )
        intentExplicito.putExtra("entrenador",datosEntrenador)
        startActivity(intentExplicito)
    }

    private fun buscarEntrenadorPorId(id: Int): ClaseEntrenadores? {
        bddEntrenadores.forEach {
            if(it.id == id){
                return it
            }
        }
        return null
    }

}
