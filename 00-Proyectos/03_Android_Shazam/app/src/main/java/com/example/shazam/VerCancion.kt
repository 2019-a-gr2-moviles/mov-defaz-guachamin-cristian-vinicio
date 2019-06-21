package com.example.shazam

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ver_cancion.*

class VerCancion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_cancion)

        val cancion: ParcelableMusica? = this.intent.getParcelableExtra("cancion")

        when {
            cancion?.idCancion == 1 -> img_verCaratula.setImageResource(R.mipmap.caracal)
            cancion?.idCancion == 2 -> img_verCaratula.setImageResource(R.mipmap.uglyisbeauty)
            cancion?.idCancion == 3 -> {img_verCaratula.setImageResource(R.mipmap.urbanflora)}
            cancion?.idCancion == 4 -> {img_verCaratula.setImageResource(R.mipmap.wallflower)}
            cancion?.idCancion == 5 -> {img_verCaratula.setImageResource(R.mipmap.mini)}
            cancion?.idCancion == 6 -> {img_verCaratula.setImageResource(R.mipmap.feli)}
            cancion?.idCancion == 7 -> {img_verCaratula.setImageResource(R.mipmap.xanax)}
            cancion?.idCancion == 8 -> {img_verCaratula.setImageResource(R.mipmap.caracal)}
            cancion?.idCancion == 9 -> {img_verCaratula.setImageResource(R.mipmap.brave)}
            cancion?.idCancion == 10 -> {img_verCaratula.setImageResource(R.mipmap.homie)}
            else -> img_verCaratula.setImageResource(R.mipmap.shadow)
        }

        txv_ver_Artista.text = cancion?.artista
        txv_ver_Titulo.text = cancion?.titulo

        btn_atras.setOnClickListener {
            volverALista()
        }
    }

    private fun volverALista(){
        val intentExplicito = Intent(
            this,
            MusicaRecyclerView::class.java
        )
        intentExplicito.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intentExplicito)
    }
}
