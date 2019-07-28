package com.example.app_pokemon

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsPokemon : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    //  private var tienePermisosLocalizacion = false
    private var bddPokemonesPar = arrayListOf<ClasePokemonParcelable>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //solicitarPermisosLocalizacion()
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
        establecerConfiguracionMaps(mMap)
        /*
        val foch = LatLng(-0.202178, -78.491622)
        val titulo = "Plaza Foch"
        val foch2 = LatLng(-0.209171, -78.497844)
        val titulo2 = "Ejido"
        val zoom = 15f
        anadirMarcador(foch,titulo)
        anadirMarcador(foch2,titulo2)
        */
        bddPokemonesPar.forEach {
            val lat = it.latitud.toDouble()
            val long = it.longitud.toDouble()
            val coordenadas = LatLng(lat, long)
            anadirMarcador(coordenadas, it.nombrePokemon)
        }
        // moverCamaraConZom(foch, zoom)
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

    private fun establecerConfiguracionMaps(mapa: GoogleMap) {

        val contexto = this.applicationContext // Guardando el contexto de  la actividad
        // Para que no de el error en el caso de no tener permisos.
        with(mapa) {

            // Copiando aqui:
            val permisoFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermiso = permisoFineLocation == PackageManager.PERMISSION_GRANTED
            // Siempre hay que revisar que si realmenet tenemos permisos para acceder a la localización.
            if (tienePermiso) {
                mapa.isMyLocationEnabled = true
            }
            this.uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }

    /*
    private fun solicitarPermisosLocalizacion(){
        val permisoFineLocation = ContextCompat
            .checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        val tienePermiso = permisoFineLocation == PackageManager.PERMISSION_GRANTED

        if(tienePermiso){
            Log.i("mapa","Tiene permisos de FINE_LOCATION")
            this.tienePermisosLocalizacion = true
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf( // Arreglo de permisos
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1 // Código que vamps a esperar
            )
        }

    }
    */
}
