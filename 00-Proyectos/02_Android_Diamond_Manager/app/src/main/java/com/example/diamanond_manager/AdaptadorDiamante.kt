package com.example.diamanond_manager

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class AdaptadorDiamante(
    private val listaDiamantes: List<DiamanteParcelable>,
    val contexto: RecyclerViewDiamantes,
    recyclerView: RecyclerView
) :
    RecyclerView.Adapter<AdaptadorDiamante.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textviewNombre: TextView
        val textViewPais: TextView
        val imagenDiamante: ImageView

        init {
            textviewNombre=view.findViewById(R.id.txv_lay_nombre) as TextView
            textViewPais=view.findViewById(R.id.txv_lay_origen) as TextView
            imagenDiamante=view.findViewById(R.id.img_lay_diamante) as ImageView
            val capa1 = view.findViewById(R.id.rv_detalle_diamante) as ConstraintLayout
            capa1.setOnClickListener {
                contexto.verDetalleDiamante(textviewNombre.text.toString())
            }

            val botonEditar=view.findViewById(R.id.btn_lay_editar) as ImageButton
            botonEditar.setOnClickListener {
               contexto.enviarDatosDiamanteAEditar(textviewNombre.text.toString())
            }

            val botonEliminar = view.findViewById(R.id.btn_lay_borrar) as ImageButton
            botonEliminar.setOnClickListener {
                contexto.borrarDiamante(textviewNombre.text.toString())
            }
        }
    }

    // Override
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(
                R.layout.layout_diamante,
                p0,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaDiamantes.size
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, posicion: Int) {

        val diamnante = listaDiamantes[posicion]
        myViewHolder.textviewNombre.text = diamnante.nombre
        myViewHolder.textViewPais.text = diamnante.pais
        when(diamnante.imagen){
            1 -> {
                myViewHolder.imagenDiamante.setImageResource(R.drawable.round)
            }
            2 -> {
                myViewHolder.imagenDiamante.setImageResource(R.drawable.princess)
            }
            3 -> {
                myViewHolder.imagenDiamante.setImageResource(R.drawable.marquise)
            }
            4 -> {
                myViewHolder.imagenDiamante.setImageResource(R.drawable.pear)
            }
            5 -> {
                myViewHolder.imagenDiamante.setImageResource(R.drawable.emerald)
            }
        }
    }

}
