package com.example.lessonsqlcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.lessonsqlcode.db.MyDbManager

class MainActivity : AppCompatActivity() {
    val myDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        myDbManager.openDb()
        val textRes = findViewById<TextView>(R.id.tvTest)
        val dataList = myDbManager.readDbData()
        for (item in dataList) {
            textRes.append(item)
            textRes.append("\n")
        }
    }
    fun onClickSave(view: View) {
        val textRes = findViewById<TextView>(R.id.tvTest)
        textRes.text = ""
        myDbManager.insertToDb(
            findViewById<EditText>(R.id.edTitle).text.toString(),
            findViewById<EditText>(R.id.edContent).text.toString()
        )
        val dataList = myDbManager.readDbData()
        for (item in dataList) {
            textRes.append(item)
            textRes.append("\n")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
}