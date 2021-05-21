package com.carbit3333333.giphyapp.presentation.detailGift

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carbit3333333.giphyapp.R
import com.carbit3333333.giphyapp.entity.SingleGiphy
import com.carbit3333333.giphyapp.repository.NetworkState
import com.carbit3333333.giphyapp.repository.datasource.GiftDataSource
import kotlinx.android.synthetic.main.fragment_single_giphy.view.*


class SliderAdapter(val context: Context) :
    PagedListAdapter<SingleGiphy, RecyclerView.ViewHolder>(MovieDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View
        Log.e("onBindViewHolder", "onCreateViewHolder")
        view = layoutInflater.inflate(R.layout.fragment_single_giphy, parent, false)
        return GiftItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.e("onBindViewHolder", "$position")
        (holder as SliderAdapter.GiftItemViewHolder).bind(getItem(position), context)
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<SingleGiphy>() {
        override fun areItemsTheSame(oldItem: SingleGiphy, newItem: SingleGiphy): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SingleGiphy, newItem: SingleGiphy): Boolean {
            return oldItem == newItem
        }
    }

    inner class GiftItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(giftUrl: SingleGiphy?, context: Context) {
            val gift = giftUrl?.images?.original?.url
            Glide.with(itemView.context)
                .load(gift)
                .into(itemView.iv_gift_single)

        }
    }

}

