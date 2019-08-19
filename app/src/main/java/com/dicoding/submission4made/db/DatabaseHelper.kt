package com.dicoding.submission4made.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    var context = context
    companion object{
        var DATABASE_NAME = "submission4made"
        private val DATABASE_VERSION = 1
    }
    private val SQL_CREATE_TABLE_MOVIE = String.format(
        "CREATE TABLE %s"
                + " (%s TEXT PRIMARY KEY NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL)",
        DatabaseContract().TABLE_MOVIE,
        DatabaseContract.MovieColumns.ID,
        DatabaseContract.MovieColumns.TITLE,
        DatabaseContract.MovieColumns.RELEASE_DATE,
        DatabaseContract.MovieColumns.SCORE,
        DatabaseContract.MovieColumns.DESCRIPTION,
        DatabaseContract.MovieColumns.POSTER,
        DatabaseContract.MovieColumns.BACKDROP
        )

    private val SQL_CREATE_TABLE_TV_SHOW = String.format(
        "CREATE TABLE %s"
                + " (%s TEXT PRIMARY KEY NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL)",
        DatabaseContract().TABLE_TV_SHOW,
        DatabaseContract.TVShowColumns.ID,
        DatabaseContract.TVShowColumns.TITLE,
        DatabaseContract.TVShowColumns.FIRST_AIR_DATE,
        DatabaseContract.TVShowColumns.SCORE,
        DatabaseContract.TVShowColumns.DESCRIPTION,
        DatabaseContract.TVShowColumns.POSTER,
        DatabaseContract.TVShowColumns.BACKDROP
    )

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_MOVIE)
        db?.execSQL(SQL_CREATE_TABLE_TV_SHOW)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + DatabaseContract().TABLE_MOVIE)
        db?.execSQL("DROP TABLE IF EXISTS " + DatabaseContract().TABLE_TV_SHOW)

    }
}