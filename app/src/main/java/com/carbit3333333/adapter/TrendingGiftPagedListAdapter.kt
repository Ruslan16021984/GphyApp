package com.carbit3333333.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carbit3333333.giphyapp.R
import com.carbit3333333.giphyapp.entity.SingleGiphy
import com.carbit3333333.giphyapp.presentation.detailGift.GiftDetailActivity
import com.carbit3333333.giphyapp.repository.NetworkState
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.giphy_list_item.view.*
import kotlinx.android.synthetic.main.network_state_item.view.*

class TrendingGiftPagedListAdapter(val context: Context): PagedListAdapter<SingleGiphy, RecyclerView.ViewHolder>(MovieDiffCallback()) {
    val MOVIE_VIEW_TYPE = 1
    val NETWORK_VIEW_TYPE = 2
    private var networkState: NetworkState? = null
    var onGifClickListener : OnGigClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View
        if(viewType == MOVIE_VIEW_TYPE){
            view = layoutInflater.inflate(R.layout.giphy_list_item, parent, false)
            return GifItemViewHolder(view)
        }else{
            view = layoutInflater.inflate(R.layout.network_state_item, parent, false)
            return NetworkStateItemViewHolder(view)
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == MOVIE_VIEW_TYPE){
            (holder as GifItemViewHolder).bind(getItem(position), position,context)
        }else{
            (holder as NetworkStateItemViewHolder).bind(networkState)
        }
    }
    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtractRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtractRow() && position == itemCount -1){
            NETWORK_VIEW_TYPE
        }else{
            MOVIE_VIEW_TYPE
        }
    }
    private fun hasExtractRow(): Boolean{
        return networkState != null && networkState!= NetworkState.LOADED
    }
   class MovieDiffCallback: DiffUtil.ItemCallback<SingleGiphy>(){
        override fun areItemsTheSame(oldItem: SingleGiphy, newItem: SingleGiphy): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SingleGiphy, newItem: SingleGiphy): Boolean {
            return oldItem == newItem
        }

    }
   inner class GifItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(gif: SingleGiphy?, position: Int,context: Context){
            val gifURL = gif?.images?.original?.url
            Glide.with(itemView.context)
                .load(gifURL)
                .into(itemView.iv_gift)
            itemView.setOnClickListener {
                gif?.let { it1 -> onGifClickListener?.onGiftClick(position) }
            }
        }
    }
   inner class NetworkStateItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(networkState: NetworkState?){
            if (networkState!= null && networkState == NetworkState.LOADING){
                itemView.progress_bar_item.visibility = View.VISIBLE
            }else{
                itemView.progress_bar_item.visibility = View.GONE
            }
            if (networkState!= null && networkState == NetworkState.ERORR){
                itemView.txt_errorm.visibility = View.VISIBLE
                itemView.txt_errorm.text = networkState.msg
            }else if(networkState!=null && networkState == NetworkState.ENDOFLIST){
                itemView.txt_errorm.visibility = View.VISIBLE
                itemView.txt_errorm.text = networkState.msg
            }else{
                itemView.txt_errorm.visibility = View.GONE
            }
        }
    }
    fun setNetworkState(newNetworkState: NetworkState){
        val previousState = this.networkState
        val hadExtraRow = hasExtractRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtractRow()

        if (hadExtraRow !=hasExtraRow){
            if (hadExtraRow){
                notifyItemRemoved(super.getItemCount())
            }else{
                notifyItemInserted(super.getItemCount())
            }
        }else if(hasExtraRow && previousState != newNetworkState){
            notifyItemChanged(itemCount -1)
        }

    }
    interface OnGigClickListener{
        fun onGiftClick(position: Int)
    }
}