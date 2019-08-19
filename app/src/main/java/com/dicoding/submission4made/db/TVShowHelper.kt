package com.dicoding.submission4made.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.sql.SQLException
import android.content.ContentValues
import android.util.Log
import com.dicoding.submission4made.model.TVShow
import com.dicoding.submission4made.db.DatabaseContract.MovieColumns.Companion.BACKDROP
import com.dicoding.submission4made.db.DatabaseContract.MovieColumns.Companion.DESCRIPTION
import com.dicoding.submission4made.db.DatabaseContract.MovieColumns.Companion.ID
import com.dicoding.submission4made.db.DatabaseContract.MovieColumns.Companion.POSTER
import com.dicoding.submission4made.db.DatabaseContract.MovieColumns.Companion.SCORE
import com.dicoding.submission4made.db.DatabaseContract.MovieColumns.Companion.TITLE
import com.dicoding.submission4made.db.DatabaseContract.TVShowColumns.Companion.FIRST_AIR_DATE


class TVShowHelper(context: Context) {
    val DATABASE_TABLE = DatabaseContract().TABLE_TV_SHOW
    var databaseHelper = DatabaseHelper(context)
    lateinit var database: SQLiteDatabase

    companion object {

        @Volatile private var INSTANCE: TVShowHelper? = null

        fun getInstance(context: Context): TVShowHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: TVShowHelper(context)
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

    fun getAllTVShow(): ArrayList<TVShow> {
        val arrayList = ArrayList<TVShow>()
        val cursor = database.query(
            DATABASE_TABLE,
            null, null, null, null, null,
            null, null
        )
        cursor.moveToFirst()
        var tvShow: TVShow
        if (cursor.count > 0) {
            do {
                tvShow = TVShow(
                    cursor.getString(cursor.getColumnIndexOrThrow(ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(FIRST_AIR_DATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SCORE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(POSTER)),
                    cursor.getString(cursor.getColumnIndexOrThrow(BACKDROP))
                )

                arrayList.add(tvShow)
                cursor.moveToNext()

            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayList
    }

    fun isTvShowFavorited(id: String): Boolean {
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

    fun insertTVShow(tvShow: TVShow): Long {
        val args = ContentValues()
        Log.d("TES123", "id insert = "+tvShow.id)
        args.put(ID, tvShow.id)
        args.put(TITLE, tvShow.title)
        args.put(FIRST_AIR_DATE, tvShow.releaseDate)
        args.put(SCORE, tvShow.score)
        args.put(DESCRIPTION, tvShow.description)
        args.put(POSTER, tvShow.poster)
        args.put(BACKDROP, tvShow.backdrop)
        return database.insert(DATABASE_TABLE, null, args)
    }

    fun deleteTVShow(id: String): Int {
        return database.delete(DatabaseContract().TABLE_TV_SHOW, "$ID = '$id'", null)
    }

}

