package com.example.lessonsqlcode.db

import android.provider.BaseColumns

object DbNameClass: BaseColumns {
    const val TABLE_NAME = "Test_Table"
    const val COLUMN_NAME_TITLE = "Title"
    const val COLUMN_NAME_CONTENT = "Content"
    const val DATABASE_VER = 1
    const val DATABASE_NAME = "MyTestDB.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_NAME_TITLE TEXT, $COLUMN_NAME_CONTENT TEXT)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}