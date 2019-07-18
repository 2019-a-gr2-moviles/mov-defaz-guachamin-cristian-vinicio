package com.example.json_http

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.ColorRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(),
    OnMapReadyCallback,
    GoogleMap.OnCameraMoveStartedListener,
    GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraIdleListener,
    GoogleMap.OnPolylineClickListener,
    GoogleMap.OnPolygonClickListener{

    override fun onPolygonClick(p0: Polygon?) {
        Log.i("map","Poligono ${p0.toString()}")
    }

    override fun onPolylineClick(p0: Polyline?) {
        Log.i("map","Polylinea ${p0.toString()}")
    }

    // Detectar si va en bus, o caminando
    override fun onCameraMove() {
        Log.i("map","Me estoy moviendo")
    }

    override fun onCameraIdle() {
        Log.i("map","Me quuedé quieto")
    }

    override fun onCameraMoveStarted(p0: Int) {
        Log.i("map","Me voy a empezar a mover")
    }

    private lateinit var mMap: GoogleMap
    private var tienePermisosLocalizacion = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        solicitarPermisosLocalizacion()
        setContentView(R.layout.activity_maps)
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
        establecerListeners(mMap)

        // Add a marker in Sydney and move the camera
        val foch = LatLng(-0.209369, -78.489530)
        val titulo = "Plaza Foch"
        val zoom = 17f
        anadirMarcador(foch,titulo)
        moverCamaraConZom(foch, zoom)

        val poliLineaUno = googleMap
            .addPolyline(
            PolylineOptions()
                .clickable(true) // Lineas que se pueden clikear
                .add(
                LatLng(-0.210462, -78.493948),
                LatLng(-0.208218, -78.490163),
                LatLng(-0.208583, -78.488940),
                LatLng(-0.209377, -78.490303)
            )
        )

        val poligonoUno = googleMap
            .addPolygon(
            PolygonOptions()
                .clickable(true) // Lineas que se pueden clikear
                .add(
                    LatLng(-0.209431, -78.490078),
                    LatLng(-0.208734, -78.488951),
                    LatLng(-0.209431, -78.488286),
                    LatLng(-0.210085, -78.489745)
                )
        )
        poligonoUno.fillColor = -0xc771c4
    }

    private fun establecerListeners(map: GoogleMap){
        with(map){
            setOnCameraIdleListener(this@MapsActivity)
            setOnCameraMoveStartedListener(this@MapsActivity)
            setOnCameraMoveListener(this@MapsActivity)
            setOnPolylineClickListener(this@MapsActivity)
            setOnPolygonClickListener(this@MapsActivity)

        }
    }
    // Crear marcador
    private fun anadirMarcador(latLng: LatLng, title:String){
        mMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )
    }

    private fun moverCamaraConZom(latLng: LatLng, zoom:Float = 10f){
        mMap.moveCamera(CameraUpdateFactory
            .newLatLngZoom(latLng,zoom)
        )
    }

    private fun establecerConfiguracionMaps(mapa: GoogleMap){

        val contexto = this.applicationContext // Guardando el contexto de  la actividad
        // Para que no de el error en el caso de no tener permisos.
        with(mapa){

            // Copiando aqui:
            val permisoFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermiso = permisoFineLocation == PackageManager.PERMISSION_GRANTED
            // Siempre hay que revisar que si realmenet tenemos permisos para acceder a la localización.
            if(tienePermiso){
                mapa.isMyLocationEnabled = true
            }
            this.uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled= true        }
    }

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

}
