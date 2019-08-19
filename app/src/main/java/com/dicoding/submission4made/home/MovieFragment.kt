package com.dicoding.submission4made.home


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submission4made.*
import com.dicoding.submission4made.model.Movie
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mAdapter: MovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProviders.of(activity as FragmentActivity).get(MainViewModel::class.java)
        mainViewModel.movies.observe(this, getMovies)
        mainViewModel.setMoviesTVShows("movie")

        progressBar_movie.visibility = View.VISIBLE

    }

    private val getMovies =
        Observer<ArrayList<Movie>> { movies ->
            if (movies != null) {
                mAdapter = MovieAdapter(movies, context,  object : MovieAdapter.OnItemClicked {
                    override fun onItemClick(position: Int){
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("extra_movie_id", movies[position].id)
                        intent.putExtra("extra_type", "movie")
                        startActivity(intent)
                    }
                })

                recyclerV_movie.apply {
                    adapter = mAdapter

                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                }

                progressBar_movie.visibility = View.GONE

            }
        }


}
