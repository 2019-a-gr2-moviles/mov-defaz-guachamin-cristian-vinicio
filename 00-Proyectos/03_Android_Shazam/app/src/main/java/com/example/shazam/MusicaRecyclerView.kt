package com.example.shazam

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_musica_recycler_view.*

class MusicaRecyclerView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musica_recycler_view)

        val listaCancionesIni = arrayListOf<ParcelableMusica>()

        listaCancionesIni.add(ParcelableMusica(
            "Moving Mountains",
            "Disclosure",
            "Caracal",
            1
        ))
        listaCancionesIni.add(ParcelableMusica(
            "Hurt",
            "Oliver Tree",
            "Ugly is Beautiful",
            2
        ))
        listaCancionesIni.add(ParcelableMusica(
            "Show Me",
            "Alina Baraz & Galimatias",
            "Urban Flora-EP",
            3
        ))
        listaCancionesIni.add(ParcelableMusica(
            "Money",
            "Mia Vaile",
            "Wallflower-EP",
            4
        ))
        listaCancionesIni.add(ParcelableMusica(
            "Tourner dans le vide",
            "Indila",
            "Mini World",
            5
        ))
        listaCancionesIni.add(ParcelableMusica(
            "Canta",
            "Feli",
            "Eu sunt Feli",
            6
        ))
        listaCancionesIni.add(ParcelableMusica(
            "Xanax",
            "Elohim",
            "Elohim 2016",
            7
        ))
        listaCancionesIni.add(ParcelableMusica(
            "Magnets",
            "Disclosure",
            "Caracal",
            8
        ))
        listaCancionesIni.add(ParcelableMusica(
            "Hold my heart",
            "Lindsey Stirling",
            "Brave Enough",
            9
        ))
        listaCancionesIni.add(ParcelableMusica(
            "Homie Lover Friend",
            "Secret Rendezvouz",
            "Homie Lover Friend",
            10
        ))
        listaCancionesIni.add(ParcelableMusica(
            "Diamond Hard",
            "Kerli",
            "Shadow Works",
            11
        ))
        iniciarRecyclerView(listaCancionesIni, this, rv_canciones)

        btn_volver.setOnClickListener {
            volverAInicio()
        }
    }

    private fun iniciarRecyclerView(
        lista: List<ParcelableMusica>,
        actividad: MusicaRecyclerView,
        recyclerView:RecyclerView
    ){
        val adaptadorCancion = AdaptadorCancion(
            lista,
            actividad
           // recyclerView
        )
        recyclerView.adapter = adaptadorCancion
        recyclerView.itemAnimator =DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(actividad)

        adaptadorCancion.notifyDataSetChanged()
    }

    fun irAVerCancionActivityFromRecycler(cancion: ParcelableMusica){
        val intentExplicito = Intent(
            this,
            VerCancion::class.java
        )
        intentExplicito.putExtra("cancion", cancion)
        startActivity(intentExplicito)
    }

    private fun volverAInicio(){
        val intentExplicito = Intent(
            this,
            MainActivity::class.java
        )
        intentExplicito.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intentExplicito)
    }

}
