package com.dicoding.kamus.maps

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.dicoding.capstoneproject.Sekolah
import com.dicoding.capstoneproject.view.MapsDetail
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
class MapsActivity : AppCompatActivity() {
    private lateinit var mapboxMap: MapboxMap
    companion object {
        private const val ICON_ID = "ICON_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))
        setContentView(R.layout.activity_maps)

        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync { mapboxMap ->
            this.mapboxMap = mapboxMap
            //fungsi ambil data kamus
            showMarker()
        }
        //setup toolbar
        setupToolbar()

    }

    private var listSekolah = listOf(
        Sekolah("SLB Negeri Cicendo Kota Bandung","Jl. Cicendo No.2, Babakan Ciamis, Kec. Sumur Bandung, Kota Bandung, Jawa Barat 40117",-6.910926,107.604730,com.dicoding.capstoneproject.R.drawable.cicendo),
        Sekolah("Sekolah Luar Biasa - Tuna Grahita Karya Ibu"," Jl. Sosial No.510, Ario Kemuning, Kec. Sukarami, Kota Palembang, Sumatera Selatan 30151",-2.949630,104.738618,com.dicoding.capstoneproject.R.drawable.karyaibu),
        Sekolah("SLB Negeri  7 Jakarta"," Jl. Griya Wartawan, RT.8/RW.5, Cipinang Besar Sel., Kecamatan Jatinegara, Kota Jakarta Timur, Daerah Khusus Ibukota Jakarta 13410",-6.232527,106.882938,com.dicoding.capstoneproject.R.drawable.tujuhjakarta),
        Sekolah("SLB Yakut Tuna Rungu"," Jl. Kolonel Sugiri No.10, Brubahan, Kranji, Kec. Purwokerto Tim., Kabupaten Banyumas, Jawa Tengah 53116",-7.424970,109.235170,com.dicoding.capstoneproject.R.drawable.yakut)
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
            listSekolah.forEach { data ->
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
            symbolManager.addClickListener { symbol ->
                val data = Gson().fromJson(symbol.data, Sekolah::class.java)
                val intent = Intent(this, MapsDetail::class.java)
                intent.putExtra(MapsDetail.EXTRA_DATA, data)
                startActivity(intent)
                true
            }

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


    //setup Toolbar
    private fun setupToolbar() {
        supportActionBar?.apply {
            this.title = "Map Sekolah SLB Tuna Rungu"
            this.setDisplayHomeAsUpEnabled(true)
            this.setDisplayShowHomeEnabled(true)
        }
    }

    //Handler Toolbar Menu
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}