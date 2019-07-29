package com.example.diamanond_manager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detalle_diamante.*

class VerDetalleDiamante : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_diamante)
        cargarDetalles()
    }

    private fun cargarDetalles(){
        val diamanteDeItem: DiamanteParcelable? = this.intent.getParcelableExtra("diamante")
        val titulos = "Nombre Diamante: \nQuilate: \nPrecioEstimado: \nNivel de Claridad: "+
                "\nTipo de Corte: \nColor: \nPais de Origen: "
        txv_vista_nombre.text = diamanteDeItem?.nombre.toString()
        txv_vista_titulos.text = titulos
        txv_vista_descrip.text = diamanteDeItem.toString()
        cargarImagen(diamanteDeItem?.imagen)
    }

    private fun cargarImagen(idImagen: Int?){
        when(idImagen){
            1 -> {
                img_ver_diamante.setImageResource(R.drawable.round)
            }
            2 -> {
                img_ver_diamante.setImageResource(R.drawable.princess)
            }
            3 -> {
                img_ver_diamante.setImageResource(R.drawable.marquise)
            }
            4 -> {
                img_ver_diamante.setImageResource(R.drawable.pear)
            }
            5 -> {
                img_ver_diamante.setImageResource(R.drawable.emerald)
            }
        }

    }

}
