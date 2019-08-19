package com.dicoding.submission4made.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.sql.SQLException
import android.content.ContentValues
import android.util.Log
import com.dicoding.submission4made.model.Movie
import com.dicoding.submission4made.db.DatabaseContract.MovieColumns.Companion.BACKDROP
import com.dicoding.submission4made.db.DatabaseContract.MovieColumns.Companion.DESCRIPTION
import com.dicoding.submission4made.db.DatabaseContract.MovieColumns.Companion.ID
import com.dicoding.submission4made.db.DatabaseContract.MovieColumns.Companion.POSTER
import com.dicoding.submission4made.db.DatabaseContract.MovieColumns.Companion.RELEASE_DATE
import com.dicoding.submission4made.db.DatabaseContract.MovieColumns.Companion.SCORE
import com.dicoding.submission4made.db.DatabaseContract.MovieColumns.Companion.TITLE


class MovieHelper(context: Context) {
    val DATABASE_TABLE = DatabaseContract().TABLE_MOVIE
    var databaseHelper = DatabaseHelper(context)
    lateinit var database: SQLiteDatabase

    companion object {

        @Volatile private var INSTANCE: MovieHelper? = null

        fun getInstance(context: Context): MovieHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MovieHelper(context)
            }

    }


    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()

        if (database.isOpen())
            database.close()
    }

    fun getAllMovie(): ArrayList<Movie> {
        val arrayList = ArrayList<Movie>()
        val cursor = database.query(
            DATABASE_TABLE,
            null, null, null, null, null,
            null, null
        )
        cursor.moveToFirst()
        var movie: Movie
        if (cursor.count > 0) {
            do {
                movie = Movie(
                    cursor.getString(cursor.getColumnIndexOrThrow(ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SCORE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(POSTER)),
                    cursor.getString(cursor.getColumnIndexOrThrow(BACKDROP))
                )

                arrayList.add(movie)
                cursor.moveToNext()

            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayList
    }

    fun isMovieFavorited(id: String): Boolean {
        val cursor = database.query(
            DATABASE_TABLE,
            null, "$ID = '$id'", null, null, null,
            null, null
        )
        cursor.moveToFirst()
        if (cursor.count > 0) {
               return true
        }
        return false

    }

    fun insertMovie(movie: Movie): Long {
        val args = ContentValues()
        Log.d("TES123", "id insert = "+movie.id)
        args.put(ID, movie.id)
        args.put(TITLE, movie.title)
        args.put(RELEASE_DATE, movie.releaseDate)
        args.put(SCORE, movie.score)
        args.put(DESCRIPTION, movie.description)
        args.put(POSTER, movie.poster)
        args.put(BACKDROP, movie.backdrop)
        return database.insert(DATABASE_TABLE, null, args)
    }

    fun deleteMovie(id: String): Int {
        return database.delete(DatabaseContract().TABLE_MOVIE, "$ID = '$id'", null)
    }

}

