package com.dicoding.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.core.R
import com.dicoding.core.domain.model.Kamus
import kotlinx.android.synthetic.main.item_list_kamus.view.*
import java.util.ArrayList


class KamusAdapter : RecyclerView.Adapter<KamusAdapter.ListViewHolder>() {

    private var listData = ArrayList<Kamus>()
    var onItemClick: ((Kamus) -> Unit)? = null

    fun setData(newListData: List<Kamus>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_kamus, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Kamus) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(iv_item_image)
                tv_item_title.text = data.namakata
                tv_item_subtitle.text = data.deskripsi
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}