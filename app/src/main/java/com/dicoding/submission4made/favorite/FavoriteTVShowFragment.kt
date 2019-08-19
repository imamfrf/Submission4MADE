package com.dicoding.submission4made.favorite


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submission4made.DetailActivity
import com.dicoding.submission4made.R
import com.dicoding.submission4made.TVShowAdapter
import com.dicoding.submission4made.db.TVShowHelper
import com.dicoding.submission4made.model.TVShow
import kotlinx.android.synthetic.main.fragment_favorite_tvshow.*


class FavoriteTVShowFragment : Fragment() {

    lateinit var tvShowHelper: TVShowHelper
    lateinit var favoriteTVShow: ArrayList<TVShow>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_favorite_tvshow, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShowHelper = TVShowHelper.getInstance(context!!)
        tvShowHelper.open()

        favoriteTVShow = tvShowHelper.getAllTVShow()

        recyclerV_favorite_tv_show.apply {
            adapter = TVShowAdapter(
                favoriteTVShow,
                context,
                object : TVShowAdapter.OnItemClicked {
                    override fun onItemClick(position: Int) {
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("extra_type", "tv_show_favorite")
                        intent.putExtra("fav_tv_show", favoriteTVShow[position])
                        startActivity(intent)
                    }
                })

            val reverseLinearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,
                true)
            reverseLinearLayoutManager.stackFromEnd = true
            layoutManager = reverseLinearLayoutManager

        }
        progressBar_favorite_tv_show.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        favoriteTVShow.clear()
        favoriteTVShow.addAll(tvShowHelper.getAllTVShow())
        recyclerV_favorite_tv_show.adapter?.notifyDataSetChanged()
    }
}
