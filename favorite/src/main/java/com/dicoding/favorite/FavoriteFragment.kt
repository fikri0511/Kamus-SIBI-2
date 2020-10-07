package com.dicoding.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.core.ui.KamusAdapter
import com.dicoding.capstoneproject.view.detail.DetailKamusActivity
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadKoinModules(favoriteModule)

        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val kamusAdapter = KamusAdapter()
            kamusAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailKamusActivity::class.java)
                intent.putExtra(DetailKamusActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            favoriteViewModel.favoriteKamus.observe(viewLifecycleOwner, Observer { dataKamus ->
                kamusAdapter.setData(dataKamus)
                view_kosong.visibility = if (dataKamus.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(rv_kamus) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = kamusAdapter
            }
        }
    }
}
