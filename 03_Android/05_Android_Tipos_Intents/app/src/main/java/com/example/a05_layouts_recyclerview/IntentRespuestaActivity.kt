package com.example.a05_layouts_recyclerview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_respuesta.*

class IntentRespuestaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_respuesta)

        btn_enviar_intent.setOnClickListener{
            enviarIntentConRespuesta()
        }

        btn_enviar_respuesta_propia.setOnClickListener{
            enviarIntentConRespuestPropia()
        }
    }

    private fun enviarIntentConRespuestPropia(){
        val intentPropio = Intent(
            this,
            ResultadoPropioActivity::class.java)
        this.startActivityForResult(intentPropio, 305)
    }

    private fun enviarIntentConRespuesta(){
        val intentConRespuesta = Intent(
            Intent.ACTION_PICK,
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        )
        // Como el intent tienr un resultado:
        this.startActivityForResult(intentConRespuesta,304)
    }

    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // En lugar de if / else, usamos when para las respuestas de los intents
        when(resultCode){
            RESULT_OK -> {
                Log.i("intent-respuesta","LO LOGRAMOS ${Activity.RESULT_OK}")
                when(requestCode){
                    304 ->{
                        Log.i("intent-respuesta","CONTACTO LLEGÓ !")
                        val uri = data?.data
                        val cursor = contentResolver.query(uri!!,
                            null,
                            null,
                            null,
                            null
                            )
                        cursor?.moveToFirst()
                        val indiceTelefono = cursor?.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        val telefono = cursor?.getString(indiceTelefono!!)
                        cursor?.close()
                        Log.i("intent-respuesta","El teléfono es: $telefono")
                    }
                    305 -> {
                        val nombre = data?.getStringExtra("nombreUsuario")
                        val edad = data?.getIntExtra("edadUsuario", 0)
                        Log.i("intent-respuesta","Nombre: $nombre Edad: $edad")
                    }
                }
            }

            RESULT_CANCELED -> {
                Log.i("intent-respuesta","NO ESCOGIÓ :(")
            }
        }
    }

}
