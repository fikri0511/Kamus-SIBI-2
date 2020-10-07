package com.dicoding.kamus.maps

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.dicoding.core.data.Resource
import kotlinx.android.synthetic.main.activity_maps.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MapsActivity : AppCompatActivity() {
    private val mapsViewModel : MapsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        supportActionBar?.title="Kamus Map"

        loadKoinModules(mapsModule)

        //fungsi ambil data kamus
        getKamusData()
    }

    @SuppressLint("SetTextI18n")
    private fun getKamusData() {
        mapsViewModel.kamus.observe(this, Observer { kamus ->
            if (kamus != null) {
                when (kamus) {
                    is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progress_bar.visibility = View.GONE
                        tv_maps.text = "Ini adalah tempat dari ${kamus.data?.get(0)?.namakata}"
                    }
                    is Resource.Error -> {
                        progress_bar.visibility = View.GONE
                        tv_error.visibility = View.VISIBLE
                        tv_error.text = kamus.message
                    }
                }
            }
        })
    }
}