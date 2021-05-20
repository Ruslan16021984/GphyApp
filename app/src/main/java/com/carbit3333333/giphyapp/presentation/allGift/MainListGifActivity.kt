package com.carbit3333333.giphyapp.presentation.allGift

import android.app.SearchManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.carbit3333333.adapter.TrendingGiftPagedListAdapter
import com.carbit3333333.giphyapp.R
import com.carbit3333333.giphyapp.presentation.BaseActivity
import com.carbit3333333.giphyapp.presentation.detailGift.GiftDetailActivity
import com.carbit3333333.giphyapp.repository.NetworkState
import kotlinx.android.synthetic.main.activity_main.*


class MainListGifActivity : BaseActivity() {
    lateinit var giftsAdapter: TrendingGiftPagedListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        giftsAdapter = this.let { TrendingGiftPagedListAdapter(it) }
        val gridLayoutManager = GridLayoutManager(this, 3)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = giftsAdapter.getItemViewType(position)
                if (viewType == giftsAdapter.MOVIE_VIEW_TYPE) return 1
                else return 3
            }
        };
        rv_gift_list.layoutManager = gridLayoutManager
        rv_gift_list.setHasFixedSize(true)
        rv_gift_list.adapter = giftsAdapter
        viewModel.moviePagedList.observe(this, Observer {
            giftsAdapter.submitList(it)
            giftsAdapter.onGifClickListener =
                object : TrendingGiftPagedListAdapter.OnGigClickListener {
                    override fun onGiftClick(position: Int) {
                        Log.e("onGiftClickListener", "$position")
//                    val bundle = Bundle()
//                    bundle.putString("MyArg", singleGiphy.images.original.url)
//                    Log.e("AllGiphsFragment", singleGiphy.images.original.url)
                        val intent =
                            GiftDetailActivity.newIntent(this@MainListGifActivity, position)
                        startActivity(intent)
                    }

                }
        })
        viewModel.networkState.observe(this, Observer {
            progress_bar_popular.visibility =
                if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_errorm.visibility =
                if (viewModel.listIsEmpty() && it == NetworkState.ERORR) View.VISIBLE else View.GONE
            if (!viewModel.listIsEmpty()) {
                giftsAdapter.setNetworkState(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        val searchItem: MenuItem? = menu?.findItem(R.id.menu_save)
        val serchView: SearchView = MenuItemCompat.getActionView(searchItem) as SearchView
        serchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.getSearchPagedList(it) }
                    ?.observe(this@MainListGifActivity, Observer {
                        giftsAdapter.submitList(it)

                    })
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        serchView.setOnCloseListener {
            viewModel.moviePagedList.observe(this, Observer {
                giftsAdapter.submitList(it)
            })
            return@setOnCloseListener false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_save -> {

                true
            }
            else -> false
        }
    }

}