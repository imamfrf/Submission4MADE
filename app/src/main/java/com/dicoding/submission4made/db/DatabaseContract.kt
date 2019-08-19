package com.dicoding.submission4made.db

import android.provider.BaseColumns



class DatabaseContract {
    var TABLE_MOVIE = "movie"
    var TABLE_TV_SHOW = "tv_show"

    internal class MovieColumns : BaseColumns {
        companion object {
            var ID = "id"
            var TITLE = "title"
            var RELEASE_DATE = "release_date"
            var SCORE = "score"
            var DESCRIPTION = "description"
            var POSTER = "poster"
            var BACKDROP = "backdrop"
        }
    }

    internal class TVShowColumns : BaseColumns {
        companion object {
            var ID = "id"
            var TITLE = "title"
            var FIRST_AIR_DATE ="first_air_date"
            var SCORE = "score"
            var DESCRIPTION = "description"
            var POSTER = "poster"
            var BACKDROP = "backdrop"
        }
    }
}