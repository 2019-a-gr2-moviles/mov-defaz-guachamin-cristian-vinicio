package com.example.a05_layouts_recyclerview

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class PersonasAdaptador(private val listaPersonas: List<Persona>,
                        private val contexto: ReciclerViewActivity, //contexto: this
                        private val recyclerView: RecyclerView) :

    RecyclerView.Adapter<PersonasAdaptador.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var nombreTextView: TextView
        var cedulaTextView: TextView
        var accionBoton: Button

        init {

            nombreTextView = view.findViewById(R.id.txt_nombre) as TextView
            cedulaTextView = view.findViewById(R.id.txt_cedula) as TextView
            accionBoton = view.findViewById(R.id.btn_accion) as Button

            val layout = view.findViewById(R.id.linear_layout_v) as LinearLayout

            layout.setOnClickListener{
                Log.i("recycler_view","Layout presionado")
            }

            accionBoton.setOnClickListener {
                nombreTextView.text = "Me cambiarooooon !!"
            }
        }
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PersonasAdaptador.MyViewHolder {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        // Esta funcion dfine el template que vamos a utilizar
        // el tenmplate esta en la carpeta de res/Layout
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(
                R.layout.layout1,
                p0,
                false
            )


        return MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
     //   TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        // Cuantos item estan en la lista, devuelve el número de items
        return listaPersonas.size
    }

    override fun onBindViewHolder(myViewHolder: PersonasAdaptador.MyViewHolder, position: Int) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        // p1: posicion
        // Sacar una persona por posición
        val persona = listaPersonas[position]
        myViewHolder.nombreTextView.text = persona.nombre
        myViewHolder.cedulaTextView.text = persona.cedula

        
    }

}

