package com.example.shazam

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class AdaptadorCancion (
    val listaCanciones: List<ParcelableMusica>,
    val contexto: MusicaRecyclerView,
    val recyclerView: RecyclerView
):

    RecyclerView.Adapter<AdaptadorCancion.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txvTitulo: TextView
        val txvAutor: TextView
        val txvAlbum: TextView
        val imgCaratula: ImageView
        init {

        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
    val itemView = LayoutInflater
        .from(p0.context)
        .inflate(
            R.layout.layout,
            p0,
            false
        )
    return MyViewHolder(itemView)
}
    }
