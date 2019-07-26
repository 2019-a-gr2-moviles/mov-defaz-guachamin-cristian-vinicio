package com.example.app_pokemon

class ClaseServidorBackend {

    companion object{
        private const val IP = "192.168.1.30"
        private const val puerto = "1433"

        fun getURL(parametro: String): String{
            return "$IP:$puerto/$parametro"
        }
    }

}
