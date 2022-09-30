package com.example.lessonsqlcode.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDbManager(context: Context) {
    val myDbHelper = MyDbHelper(context)
    var db: SQLiteDatabase ?= null

    fun openDb() {
        db = myDbHelper.writableDatabase
    }

    fun insertToDb(title: String, content: String, uri: String) {
        val values = ContentValues().apply {
            put(DbNameClass.COLUMN_NAME_TITLE, title)
            put(DbNameClass.COLUMN_NAME_CONTENT, content)
            put(DbNameClass.COLUMN_NAME_IMAGE_URI, uri)
        }
        db?.insert(DbNameClass.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun readDbData() : ArrayList<ListItem> {
        val dataList = ArrayList<ListItem>()
        val cursor = db?.query(DbNameClass.TABLE_NAME, null, null,
            null, null, null, null)
        with(cursor) {
            while (this?.moveToNext()!!) {
                val dataText = cursor?.getString(cursor.getColumnIndex(DbNameClass.COLUMN_NAME_TITLE))
                val dataDesc = cursor?.getString(cursor.getColumnIndex(DbNameClass.COLUMN_NAME_CONTENT))
                val dataUri = cursor?.getString(cursor.getColumnIndex(DbNameClass.COLUMN_NAME_IMAGE_URI))
                dataList.add(ListItem(dataText.toString(), dataDesc.toString(), dataUri.toString()))
            }
        }
        return dataList
    }

    fun closeDb(){
        myDbHelper.close()
    }
}