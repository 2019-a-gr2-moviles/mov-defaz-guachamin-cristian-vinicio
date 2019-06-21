package com.example.shazam

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

@Suppress("JoinDeclarationAndAssignment")
class AdaptadorCancion (
    private val listaCanciones: List<ParcelableMusica>,
    val contexto: MusicaRecyclerView
):
    RecyclerView.Adapter<AdaptadorCancion.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txvTitulo: TextView
        val txvArtista: TextView
        val txvAlbum: TextView
        val txvIdCancion: TextView
        val imgCaratula: ImageView

        init {
            txvTitulo=view.findViewById(R.id.txv_item_titulo) as TextView
            txvArtista=view.findViewById(R.id.txv_item_artista) as TextView
            txvAlbum=view.findViewById(R.id.txv_item_album) as TextView
            txvIdCancion=view.findViewById(R.id.txv_id) as TextView
            imgCaratula=view.findViewById(R.id.img_item_caratula) as ImageView

            val layoutC = view.findViewById(R.id.rv_item_cancion) as RelativeLayout
            layoutC.setOnClickListener {
                val cancion = ParcelableMusica(
                    txvTitulo.text.toString(),
                    txvArtista.text.toString(),
                    txvAlbum.text.toString(),
                    Integer.parseInt(txvIdCancion.text.toString()))
                irAVerCancionActivity(cancion)
                Log.i("recycler-view", "Pulsando sobre layout")
            }
        }
        private fun irAVerCancionActivity(cancion: ParcelableMusica){
            contexto.irAVerCancionActivityFromRecycler(cancion)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(
                R.layout.layout_cancion,
                p0,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaCanciones.size
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        val cancion = listaCanciones[position]

        myViewHolder.txvTitulo.text = cancion.titulo
        myViewHolder.txvArtista.text = cancion.artista
        myViewHolder.txvAlbum.text = cancion.album
        myViewHolder.txvIdCancion.text = cancion.idCancion.toString()

        when (cancion.idCancion){
            1->{myViewHolder.imgCaratula.setImageResource(R.mipmap.caracal)}
            2->{myViewHolder.imgCaratula.setImageResource(R.mipmap.uglyisbeauty)}
            3->{myViewHolder.imgCaratula.setImageResource(R.mipmap.urbanflora)}
            4->{myViewHolder.imgCaratula.setImageResource(R.mipmap.wallflower)}
            5->{myViewHolder.imgCaratula.setImageResource(R.mipmap.mini)}
            6->{myViewHolder.imgCaratula.setImageResource(R.mipmap.feli)}
            7->{myViewHolder.imgCaratula.setImageResource(R.mipmap.xanax)}
            8->{myViewHolder.imgCaratula.setImageResource(R.mipmap.caracal)}
            9->{myViewHolder.imgCaratula.setImageResource(R.mipmap.brave)}
            10->{myViewHolder.imgCaratula.setImageResource(R.mipmap.homie)}
            11->{myViewHolder.imgCaratula.setImageResource(R.mipmap.shadow)}
        }
    }

}
