package com.example.diamanond_manager

class ServidorBackend {

    companion object{
        private const val IP = "192.168.1.30"
        private const val puerto = "1337"

        fun getURL(parametro: String): String{
            return "http://$IP:$puerto/$parametro"
        }
    }
}