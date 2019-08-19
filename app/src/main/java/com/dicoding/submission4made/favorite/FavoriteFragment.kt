package com.dicoding.submission4made.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.submission4made.R
import com.dicoding.submission4made.TabAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*


class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TabAdapter(activity!!.supportFragmentManager)
        adapter.addFragment(FavoriteMovieFragment(), getString(R.string.movie))
        adapter.addFragment(FavoriteTVShowFragment(), getString(R.string.tv_show))

        view_pager_favorite.adapter = adapter
        tab_layout_favorite.setupWithViewPager(view_pager_favorite)

    }


}
