package com.example.diamanond_manager

import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class ConexionesHTTP {

    companion object {

        private var bddDiamantes = arrayListOf<Diamante>()
        private var bddClaridad = arrayListOf<FKClaridad>()
        private var bddColor = arrayListOf<FKColor>()
        private var bddCorte = arrayListOf<FKCut>()
        private var bddPaises = arrayListOf<FKPais>()
        private const val IP = "http://192.168.137.1:1337"
        //     private const val IP = "http://192.168.1.25:1337"

        fun listarDatosDiamantes(): List<Diamante> {
            val url = "$IP/diamond"
            url.httpGet().responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("httpError", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        bddDiamantes.clear()
                        val data = result.get()
                        val datosParseados = Klaxon().parseArray<Diamante>(data)
                        datosParseados?.forEach {
                            bddDiamantes.add(it)
                        }
                    }
                }
            }
            return bddDiamantes
        }

        fun listarDatosClaridad(): List<FKClaridad> {
            val url = "$IP/clarity"
            url.httpGet().responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("httpError", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        bddClaridad.clear()
                        val data = result.get()
                        val datosParseados = Klaxon().parseArray<FKClaridad>(data)
                        datosParseados?.forEach {
                            bddClaridad.add(it)
                        }
                    }
                }
            }
            return bddClaridad
        }

        fun listarDatosColor(): List<FKColor> {
            val url = "$IP/color"
            url.httpGet().responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("httpError", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        bddColor.clear()
                        val data = result.get()
                        val datosParseados = Klaxon().parseArray<FKColor>(data)
                        datosParseados?.forEach {
                            bddColor.add(it)
                        }
                    }
                }
            }
            return bddColor
        }

        fun listarDatosCorte(): List<FKCut> {
            val url = "$IP/cut"
            url.httpGet().responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("httpError", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        bddCorte.clear()
                        val data = result.get()
                        val datosParseados = Klaxon().parseArray<FKCut>(data)
                        datosParseados?.forEach {
                            bddCorte.add(it)
                        }
                    }
                }
            }
            return bddCorte
        }

        fun listarDatosPaises(): List<FKPais> {
            val url = "$IP/country"
            url.httpGet().responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("httpError", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        bddPaises.clear()
                        val data = result.get()
                        val datosParseados = Klaxon().parseArray<FKPais>(data)
                        datosParseados?.forEach {
                            bddPaises.add(it)
                        }
                    }
                }
            }
            return bddPaises
        }

        fun buscarID(caracteristica: String, itemSeleccionado: String): Int? {
            when(caracteristica){
                "claridad" -> {
                    bddClaridad.forEach {
                        if(it.clarityName == itemSeleccionado){
                            return it.id
                        }
                    }
                }
                "color" -> {
                    bddColor.forEach {
                        if(it.colorName == itemSeleccionado){
                            return it.id
                        }
                    }
                }
                "corte" -> {
                    bddCorte.forEach {
                        if(it.cutName == itemSeleccionado){
                            return it.id
                        }
                    }
                }
                "pais" -> {
                    bddPaises.forEach {
                        if(it.countryName == itemSeleccionado){
                            return it.id
                        }
                    }
                }
            }
            return null
        }

        /*
        fun insertarDiamante(data: String){
            val url = "$IP/diamond"
            url.httpPost(data)
                .responseString { request, response, result ->
                    when(result){
                        is Failure -> {
                            val error = result.getException()
                            Log.i("http","Error: $error")
                        }
                        is Success -> {
                            val empresaString = result.get();
                            Log.i("http", "Mensaje: $empresaString")
                        }
                    }
                }
        }
        */

        /*
              fun conectarABackend(peticion: String): String {
            val url = "$IP$peticion"
            Log.i("http: ",url)
            var data = ""
            url.httpGet().responseString { _, _, result ->
               when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("httpError", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        data = result.get()
                        Log.i("http","InicioBackend:$data:fin")
                   //     Log.i("httpExito", data)
                    }
                }
            }
            Log.i("http","IniciodeReturn:$data:fin")
            return data
        }

        fun listarDatosDiamantes(): List<Diamante> {
            Log.i("http","IniciodeFuncion:${conectarABackend("diamond")}:fin")
            val datosParseados = Klaxon().parseArray<Diamante>(conectarABackend("diamond"))
            datosParseados?.forEach {
                bddDiamantes.add(it)
            }
            return bddDiamantes
        }


        fun listarDatosClaridad(): List<FKClaridad> {
            val datosParseados = Klaxon().parseArray<FKClaridad>(conectarABackend("clarity")) as ArrayList<FKClaridad>
            datosParseados.forEach {
                bddClaridad.add(it)
            }
            return bddClaridad
        }

        fun listarDatosColor(): List<FKColor> {
            val datosParseados = Klaxon().parseArray<FKColor>(conectarABackend("color")) as ArrayList<FKColor>
            datosParseados.forEach {
                bddColor.add(it)
            }
            return bddColor
        }

        fun listarDatosCorte(): List<FKCut> {
            val datosParseados = Klaxon().parseArray<FKCut>(conectarABackend("cut")) as ArrayList<FKCut>
            datosParseados.forEach {
                bddCorte.add(it)
            }
            return bddCorte
        }

        fun listarDatosPaises(): List<FKPais> {
            val datosParseados = Klaxon().parseArray<FKPais>(conectarABackend("country")) as ArrayList<FKPais>
            datosParseados.forEach {
                bddPaises.add(it)
            }
            return bddPaises
        }
         */
    }

}