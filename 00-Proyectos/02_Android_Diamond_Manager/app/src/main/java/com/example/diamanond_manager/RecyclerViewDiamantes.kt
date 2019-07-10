package com.example.diamanond_manager

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_recycler_view_prueba.*

class RecyclerViewDiamantes : AppCompatActivity() {

    var bddDiamantesRecyclerView = arrayListOf<DiamanteParcelable>()
    val filtros = arrayOf(" ","Claridad","Color","Corte","País de Origen")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_prueba)
        val parentLayout = findViewById<View>(android.R.id.content)
        bddDiamantesRecyclerView = transformarDiamantesAParcelable()
        cargarSpinner()

        // Iniciar después de 4 segundos
        mostrarSnackbar(parentLayout,"Cargando datos" )
        Handler().postDelayed({
            Log.i("http", "Ya tengo datos, tamanio: ${bddDiamantesRecyclerView.size}")
            iniciarRecyclerView(
                bddDiamantesRecyclerView,
                this,
                rv_lista_diamantes)
        }, 5000)

        // Ir a formulario minsercion
        btn_agregar_diamante.setOnClickListener {
            irAFormularioDeInsercion()
        }
    }

    private fun transformarDiamantesAParcelable(): ArrayList<DiamanteParcelable>{
        val listaDiamantes = arrayListOf<DiamanteParcelable>()
        ConexionesHTTP.listarDatosDiamantes().forEach{
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
        }
       return listaDiamantes
    }

    private fun cargarSpinner(){
        val adapter =  ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            filtros)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_filtros.adapter = adapter
        spinner_filtros.setSelection(-1, true)

        spinner_filtros.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    1 -> {
                        mostrarSnackbar(view!!,"Seleccion: $position")
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nada
            }

        }
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
            if(it.nombre == nombreDiamante){
                val intentExplicito = Intent(
                    this,
                    VerDetalleDiamante::class.java
                )
                intentExplicito.putExtra("diamante", it)
                startActivity(intentExplicito)
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

    // ,"Claridad","Color","Corte","País de Origen"
    /*
    fun filtrarDiamante(opcion: Int): List<DiamanteParcelable>{
        bddDiamantesRecyclerView.forEach {diamond ->
            when(opcion){
               1 -> {
                    return bddDiamantesRecyclerView.filter { eachDiamond ->
                        eachDiamond.claridad == categoryDiamond
                    }
                }
                2 -> {
                    return bddDiamantesRecyclerView.filter { eachDiamond ->
                        eachDiamond.clarity == categoryDiamond
                    }
                }
                3 -> {
                    return bddDiamantesRecyclerView.filter { eachDiamond ->
                        eachDiamond.color == categoryDiamond
                    }
                }
                4 -> {
                    return bddDiamantesRecyclerView.filter { eachDiamond ->
                        eachDiamond.cut == categoryDiamond
                    }
                }
            }
        }
        return null
    }
    */

    fun editarDiamante() {

    }

    fun eliminarDiamante() {

    }

    private fun mostrarSnackbar(view: View, texto:String){
        Snackbar.make(view, texto, Snackbar.LENGTH_LONG)
            .setAction("Action",null).show()
    }

}

