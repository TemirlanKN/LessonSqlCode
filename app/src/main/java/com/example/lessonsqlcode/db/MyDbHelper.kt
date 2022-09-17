package com.example.lessonsqlcode.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(context: Context) : SQLiteOpenHelper(context,
    DbNameClass.DATABASE_NAME, null, DbNameClass.DATABASE_VER) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DbNameClass.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DbNameClass.SQL_DELETE_TABLE)
        onCreate(db)
    }

}