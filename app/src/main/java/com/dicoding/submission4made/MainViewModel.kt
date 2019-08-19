package com.dicoding.submission4made

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submission4made.model.Movie
import com.dicoding.submission4made.model.TVShow
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

import java.util.ArrayList

class MainViewModel : ViewModel() {
    private val listMovies = MutableLiveData<ArrayList<Movie>>()
    private val listTVShows = MutableLiveData<ArrayList<TVShow>>()

    internal val movies: LiveData<ArrayList<Movie>>
        get() = listMovies

    internal val tvShows: LiveData<ArrayList<TVShow>>
        get() = listTVShows


    internal fun setMoviesTVShows(type: String) {
        val listItemsMovie = ArrayList<Movie>()
        val listItemsTV = ArrayList<TVShow>()

        val params = RequestParams()
        params.put("api_key", BuildConfig.TMDB_API_KEY)
        params.put("language", "en-US")
        val client = AsyncHttpClient()
        val url = "https://api.themoviedb.org/3/discover/"+type
        client.get(url, params, object : AsyncHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("results")
                    for (i in 0 until list.length()) {
                        if (type == "movie"){
                            val movie = list.getJSONObject(i)
                            val movieItems = Movie(movie)
                            listItemsMovie.add(movieItems)
                            listMovies.postValue(listItemsMovie)

                        }
                        else if (type == "tv"){
                            val tvShow = list.getJSONObject(i)
                            val tvShowItems = TVShow(tvShow)
                            listItemsTV.add(tvShowItems)
                            listTVShows.postValue(listItemsTV)

                        }

                    }
                } catch (e: Exception) {
                    Log.d("Exception", e.message)
                }

            }

            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Log.d("onFailure", error.message)
            }
        })
    }

}
