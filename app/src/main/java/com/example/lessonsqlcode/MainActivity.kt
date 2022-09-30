package com.example.lessonsqlcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lessonsqlcode.db.MyAdapter
import com.example.lessonsqlcode.db.MyDbManager

class MainActivity : AppCompatActivity() {
    private val myDbManager = MyDbManager(this)
    private val myAdapter = MyAdapter(ArrayList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        fillAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

    fun onClickNew(view: View) {
        val i = Intent(this, EditActivity::class.java)
        startActivity(i)
    }

    fun init() {
        val rcView: RecyclerView = findViewById(R.id.rcView)
        rcView.layoutManager = LinearLayoutManager(this) //this means that all elements in rcv will stay vertically
        rcView.adapter = myAdapter
    }

    fun fillAdapter() {
        myAdapter.updateAdapter(myDbManager.readDbData())
    }
}