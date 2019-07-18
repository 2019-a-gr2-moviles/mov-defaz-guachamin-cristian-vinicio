package com.example.json_http

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fragmentos.*

class FragmentosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragmentos)

        btn_primero.setOnClickListener {
            abrirPrimerFragmento()
        }
    }

    private fun abrirPrimerFragmento(){
        // 1) Manager
        val fragmentManager = supportFragmentManager
        // 2) Empezar transacción
        val transaccion = fragmentManager.beginTransaction()
        // 3) Definir la instancia del fragmento
        val primerFragmento = PrimerFragmento()
        // 4) Reemplezamos el fragmento
        transaccion.replace(R.id.layout_frag, primerFragmento)
        // 5) Terminar la transaccion
        transaccion.commit()
    }

    fun abrirSegundoFragmento(){
        // 1) Manager
        val fragmentManager = supportFragmentManager
        // 2) Empezar transacción
        val transaccion = fragmentManager.beginTransaction()
        // 3) Definir la instancia del fragmento
        val segundoFragmento = SegundoFragmento()
        // 4) Reemplezamos el fragmento
        transaccion.replace(R.id.layout_frag, segundoFragmento)
        // 5) Terminar la transaccion
        transaccion.commit()
    }
}
