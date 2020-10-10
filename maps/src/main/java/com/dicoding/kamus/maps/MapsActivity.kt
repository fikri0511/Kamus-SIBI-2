package com.dicoding.kamus.maps

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.capstoneproject.Sekolah
import com.google.gson.Gson
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.geometry.LatLngBounds
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import kotlinx.android.synthetic.main.activity_maps.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MapsActivity : AppCompatActivity() {
    private lateinit var mapboxMap: MapboxMap
    companion object {
        private const val ICON_ID = "ICON_ID"
    }

    private val mapsViewModel : MapsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))
        setContentView(R.layout.activity_maps)

        supportActionBar?.title="Kamus Map"

        loadKoinModules(mapsModule)


        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync { mapboxMap ->
            this.mapboxMap = mapboxMap
            //fungsi ambil data kamus
            showMarker()
        }

    }

    var listSekolah = listOf(
        Sekolah("SLB Negeri Cicendo Kota Bandung","Jl. Cicendo No.2, Babakan Ciamis, Kec. Sumur Bandung, Kota Bandung, Jawa Barat 40117",-2.949630,104.738618),
        Sekolah("Sekolah Luar Biasa - Tuna Grahita Karya Ibu"," Jl. Sosial No.510, Ario Kemuning, Kec. Sukarami, Kota Palembang, Sumatera Selatan 30151",-6.910926,107.604730),
        Sekolah("SLB Negeri  7 Jakarta"," Jl. Griya Wartawan, RT.8/RW.5, Cipinang Besar Sel., Kecamatan Jatinegara, Kota Jakarta Timur, Daerah Khusus Ibukota Jakarta 13410",-6.232527,106.882938),
        Sekolah("SLB Yakut Tuna Rungu"," Jl. Kolonel Sugiri No.10, Brubahan, Kranji, Kec. Purwokerto Tim., Kabupaten Banyumas, Jawa Tengah 53116",-7.424970,109.235170)
    )


    private fun showMarker() {
        mapboxMap.setStyle(Style.MAPBOX_STREETS) { style ->
            style.addImage(
                ICON_ID,
                BitmapFactory.decodeResource(resources, R.drawable.mapbox_marker_icon_default)
            )
            val latLngBoundsBuilder = LatLngBounds.Builder()
            val symbolManager = SymbolManager(mapView, mapboxMap, style)
            symbolManager.iconAllowOverlap = true
            val options = ArrayList<SymbolOptions>()
            listSekolah?.forEach { data ->
                latLngBoundsBuilder.include(LatLng(data.latitude, data.longitude))
                options.add(
                    SymbolOptions()
                        .withLatLng(LatLng(data.latitude, data.longitude))
                        .withIconImage(ICON_ID)
                        .withData(Gson().toJsonTree(data))
                )
            }
            symbolManager.create(options)
            val latLngBounds = latLngBoundsBuilder.build()
            mapboxMap.easeCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 50), 5000)
//            symbolManager.addClickListener { symbol ->
//                val data = Gson().fromJson(symbol.data, Sekolah::class.java)
//                val intent = Intent(this, De::class.java)
//                intent.putExtra(DetailTourismActivity.EXTRA_DATA, data)
//                startActivity(intent)
//            }
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }
    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }
    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }



}