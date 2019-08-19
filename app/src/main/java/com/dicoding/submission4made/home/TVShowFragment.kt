package com.dicoding.submission4made.home


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submission4made.*
import com.dicoding.submission4made.model.TVShow
import kotlinx.android.synthetic.main.fragment_tvshow.*

class TVShowFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mAdapter: TVShowAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        mainViewModel.tvShows.observe(this, getTVShows)
        mainViewModel.setMoviesTVShows("tv")

        progressBar_tv_show.visibility = View.VISIBLE

    }

    private val getTVShows =
        Observer<ArrayList<TVShow>> { tvShows ->
            if (tvShows != null) {
                mAdapter = TVShowAdapter(tvShows, context,  object : TVShowAdapter.OnItemClicked {
                    override fun onItemClick(position: Int){
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("extra_tv_show_id", tvShows[position].id)
                        intent.putExtra("extra_type", "tv")
                        startActivity(intent)
                    }
                })

                recyclerV_tv_show.apply {
                    adapter = mAdapter

                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                }

                progressBar_tv_show.visibility = View.GONE

            }
        }


}
