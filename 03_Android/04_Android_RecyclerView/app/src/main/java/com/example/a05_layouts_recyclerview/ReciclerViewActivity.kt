package com.example.a05_layouts_recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recicler_view.*

class ReciclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recicler_view)

        val lista = arrayListOf<Persona>()
      //  val recyvler_view = rv_personas
      //  val actividad = this

        lista.add(Persona("Criss", "1723464465"))
        lista.add(Persona("Daniela", "1724249901"))
        lista.add(Persona("Viviana", "1724256693"))

        iniciarRecylerView(lista,this,rv_personas)

    }

    // Transformando a función
    fun iniciarRecylerView(
        lista: List<Persona>,
        actividad: ReciclerViewActivity,
        recycler_view: RecyclerView
    ) {
        val adaptadorPersona = PersonasAdaptador(lista,
            actividad,
            recycler_view)

        rv_personas.adapter = adaptadorPersona
        rv_personas.itemAnimator = DefaultItemAnimator()
        // Layout manager
        rv_personas.layoutManager = LinearLayoutManager(this)

        adaptadorPersona.notifyDataSetChanged()
        // sin notifyDatSetChanged() no se visualizarán los items
    }

    fun cambiatNombreTextView(texto: String){
        txv_titulo_rv.text = texto
    }


}
