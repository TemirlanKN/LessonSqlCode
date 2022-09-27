package com.example.lessonsqlcode

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.voice.VoiceInteractionSession
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.lessonsqlcode.db.MyDbManager

class EditActivity : AppCompatActivity() {
    private val myDbManager = MyDbManager(this)
    private val imageRequestCode = 10
    var tempImageUri = "empty"

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_activity)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == imageRequestCode) {

            val imMainImage = findViewById<ImageView>(R.id.imMainImage)
            imMainImage.setImageURI(data?.data)
            tempImageUri = data?.data.toString()

        }
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

    fun onClickChooseImage(view: View) {

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, imageRequestCode)

    }

    fun onClickSave(view: View){
        val myTitle = findViewById<EditText>(R.id.edTitle).text.toString()
        val myDesc = findViewById<EditText>(R.id.edDesc).text.toString()

        if (myTitle.isNotEmpty() && myDesc.isNotEmpty()) {
            myDbManager.insertToDb(myTitle, myDesc, tempImageUri)
        }

    }

}