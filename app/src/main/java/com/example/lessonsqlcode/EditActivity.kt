package com.example.lessonsqlcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_activity)
    }

    fun onClickAddImage(view: View) {
        val constraintLayoutLayout = findViewById<ConstraintLayout>(R.id.mainImageLayoutout)
        constraintLayoutLayout.visibility = View.VISIBLE
        val imageButton = findViewById<ImageButton>(R.id.fbAddImage)
        imageButton.visibility = View.GONE
    }

    fun onClickDeleteImage(view: View) {
        val constraintLayoutLayout = findViewById<ConstraintLayout>(R.id.mainImageLayoutout)
        constraintLayoutLayout.visibility = View.GONE
        val imageButton = findViewById<ImageButton>(R.id.fbAddImage)
        imageButton.visibility = View.VISIBLE
    }


}