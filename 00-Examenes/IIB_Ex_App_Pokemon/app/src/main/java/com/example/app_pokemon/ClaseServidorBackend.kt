package com.example.app_pokemon

class ClaseServidorBackend {

    companion object{
      //  private const val IP = "192.168.1.30"
        private const val IP = "192.168.1.25"
        private const val puerto = "1337"

        fun getURL(parametro: String): String{
            return "http://$IP:$puerto/$parametro"
        }
    }

}
