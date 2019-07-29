package com.example.diamanond_manager

import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class CargadorSpinners {

    companion object {

      //  private var bddDiamantes = arrayListOf<Diamante>()
        private var bddClaridad = arrayListOf<FKClaridad>()
        private var bddColor = arrayListOf<FKColor>()
        private var bddCorte = arrayListOf<FKCut>()
        private var bddPaises = arrayListOf<FKPais>()


        fun listarDatosClaridad(): List<FKClaridad> {
            val url = ServidorBackend.getURL("clarity")
            Log.i("http", "Mi URL: $url")
            url.httpGet().responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http", "Error listando claridad: $error")
                    }
                    is Result.Success -> {
                        bddClaridad.clear()
                        val data = result.get()
                        val datosParseados = Klaxon().parseArray<FKClaridad>(data)
                        datosParseados?.forEach {
                            bddClaridad.add(it)
                        }
                        Log.i("http", "${bddClaridad[0].id} - ${bddClaridad[0].clarityName}")
                    }
                }
            }
            return bddClaridad
        }

        fun listarDatosColor(): List<FKColor> {
            val url = ServidorBackend.getURL("color")
            Log.i("http", "Mi URL: $url")
            url.httpGet().responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http", "Error listando colores: $error")
                    }
                    is Result.Success -> {
                        bddColor.clear()
                        val data = result.get()
                        val datosParseados = Klaxon().parseArray<FKColor>(data)
                        datosParseados?.forEach {
                            bddColor.add(it)
                        }
                        Log.i("http", "${bddColor[0].id} - ${bddColor[0].colorName}")
                    }
                }
            }
            return bddColor
        }

        fun listarDatosCorte(): List<FKCut> {
            val url = ServidorBackend.getURL("cut")
            Log.i("http", "Mi URL: $url")
            url.httpGet().responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http", "Error listando cortes: $error")
                    }
                    is Result.Success -> {
                        bddCorte.clear()
                        val data = result.get()
                        val datosParseados = Klaxon().parseArray<FKCut>(data)
                        datosParseados?.forEach {
                            bddCorte.add(it)
                        }
                        Log.i("http", "${bddCorte[0].id} - ${bddCorte[0].cutName}")
                    }
                }
            }
            return bddCorte
        }

        fun listarDatosPaises(): List<FKPais> {
            val url = ServidorBackend.getURL("country")
            Log.i("http", "Mi URL: $url")
            url.httpGet().responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http", "Error listando paises: $error")
                    }
                    is Result.Success -> {
                        bddPaises.clear()
                        val data = result.get()
                        val datosParseados = Klaxon().parseArray<FKPais>(data)
                        datosParseados?.forEach {
                            bddPaises.add(it)
                        }
                        Log.i("http", "${bddPaises[0].id} - ${bddPaises[0].countryName}")
                    }
                }
            }
            return bddPaises
        }

        fun buscarID(caracteristica: String, itemSeleccionado: String): Int {
            var idBuscado = 0
            when (caracteristica) {
                "claridad" -> {
                    bddClaridad.forEach {
                        if (it.clarityName == itemSeleccionado) {
                            idBuscado = it.id
                        }
                    }
                }
                "color" -> {
                    bddColor.forEach {
                        if (it.colorName == itemSeleccionado) {
                            idBuscado = it.id
                        }
                    }
                }
                "corte" -> {
                    bddCorte.forEach {
                        if (it.cutName == itemSeleccionado) {
                            idBuscado = it.id
                        }
                    }
                }
                "pais" -> {
                    bddPaises.forEach {
                        if (it.countryName == itemSeleccionado) {
                            idBuscado = it.id
                        }
                    }
                }
            }
            return idBuscado
        }
    }

}