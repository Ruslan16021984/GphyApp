package com.carbit3333333.giphyapp.presentation.detailGift

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.carbit3333333.giphyapp.R
import com.carbit3333333.giphyapp.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_gift_detail.*

class GiftDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gift_detail)
        val giftsAdapter = SliderAdapter(this)
        if (!intent.hasExtra(EXTRA_FROM_GIFT)) {
            finish()
            return
        }
        val fromGiftPosition = intent.getIntExtra(EXTRA_FROM_GIFT, 1)
        pager.adapter = giftsAdapter
        pager.visibility = View.GONE
        viewModel.getPagedList().observe(this, Observer {
            giftsAdapter.submitList(it)
            pager.visibility = View.VISIBLE
            pager.setCurrentItem(fromGiftPosition, false)
        })
        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }
        })
    }

    companion object {
        private const val EXTRA_FROM_GIFT = "fGif"
        fun newIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, GiftDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_GIFT, id)
            return intent
        }
    }

}