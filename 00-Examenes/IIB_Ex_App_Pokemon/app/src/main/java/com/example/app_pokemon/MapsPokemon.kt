package com.example.app_pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsPokemon : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var bddPokemonesPar = arrayListOf<ClasePokemonParcelable>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps_pokemon)
        this.bddPokemonesPar = intent.getParcelableArrayListExtra("listaPokemon")
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
      //  establecerConfiguracionMaps(mMap)
        bddPokemonesPar.forEach {
            val lat = it.latitud.toDouble()
            val long = it.longitud.toDouble()
            val coordenadas = LatLng(lat, long)
            anadirMarcador(coordenadas, "${it.idPokemon}- ${it.nombrePokemon}")
            moverCamaraConZom(coordenadas, 15f)
        }


        mMap.setOnMarkerClickListener { marker ->
            val id = marker.title.split("-")[0].trim()
            // Iniciar después de 3 segundos
            Handler().postDelayed({
                solicitarDetallesDePokemon(id)
            }, 3000)
            false
        }

    }

    // Crear marcador
    private fun anadirMarcador(latLng: LatLng, title: String) {
        mMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )
    }

    private fun moverCamaraConZom(latLng: LatLng, zoom: Float = 10f) {
        mMap.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng, zoom)
        )
    }

    private fun solicitarDetallesDePokemon(idPokemon: String) {
        val pokemon = buscarPokemon(idPokemon)
        val datosPokemon = ClasePokemonParcelable(
            pokemon.idPokemon,
            pokemon.nombrePokemon,
            pokemon.poderUno,
            pokemon.poderDos,
            pokemon.fechaCaptura,
            pokemon.nivel,
            pokemon.latitud,
            pokemon.longitud,
            0
        )
        val intentExplicito = Intent(
            this,
            VerPokemon::class.java
        )
        intentExplicito.putExtra("pokemon", datosPokemon)
        startActivity(intentExplicito)
    }

    private fun buscarPokemon(id: String): ClasePokemonParcelable {
        return bddPokemonesPar.find {
            it.idPokemon == id.toInt()
        }!!
    }
}
