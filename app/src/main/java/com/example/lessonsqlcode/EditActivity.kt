package com.example.lessonsqlcode

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.voice.VoiceInteractionSession
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.lessonsqlcode.db.MyDbManager
import com.example.lessonsqlcode.db.MyIntentConstants

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
        getMyIntents()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        this.contentResolver.takePersistableUriPermission(
            data?.data!!,
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
        if (resultCode == Activity.RESULT_OK && requestCode == imageRequestCode) {

            val imMainImage = findViewById<ImageView>(R.id.imMainImage)
            imMainImage.setImageURI(data?.data)
            tempImageUri = data?.data.toString()

        }
    }

    fun onClickAddImage(view: View) {
        val constraintLayout = findViewById<ConstraintLayout>(R.id.mainImageLayoutout)
        constraintLayout.visibility = View.VISIBLE
        val fbAddImage = findViewById<ImageButton>(R.id.fbAddImage)
        fbAddImage.visibility = View.GONE
    }

    fun onClickDeleteImage(view: View) {
        val constraintLayout = findViewById<ConstraintLayout>(R.id.mainImageLayoutout)
        constraintLayout.visibility = View.GONE
        val fbAddImage = findViewById<ImageButton>(R.id.fbAddImage)
        fbAddImage.visibility = View.VISIBLE
    }

    fun onClickChooseImage(view: View) {

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        startActivityForResult(intent, imageRequestCode)

    }

    fun onClickSave(view: View){
        val myTitle = findViewById<EditText>(R.id.edTitle).text.toString()
        val myDesc = findViewById<EditText>(R.id.edDesc).text.toString()

        if (myTitle.isNotEmpty() && myDesc.isNotEmpty()) {
            myDbManager.insertToDb(myTitle, myDesc, tempImageUri)
            finish()
        }

    }

    fun getMyIntents(){

        val i = intent

        if (i != null) {
            if (i.getStringExtra(MyIntentConstants.I_TITLE_KEY) != null) {

                val fbAddImage = findViewById<ImageButton>(R.id.fbAddImage)
                fbAddImage.visibility = View.GONE

                val edTitle: EditText = findViewById(R.id.edTitle)
                edTitle.setText(i.getStringExtra(MyIntentConstants.I_TITLE_KEY))

                val edDesc: EditText = findViewById(R.id.edDesc)
                edDesc.setText(i.getStringExtra(MyIntentConstants.I_DESC_KEY))

                if (i.getStringExtra(MyIntentConstants.I_URI_KEY) != "empty") {
                    val constraintLayout = findViewById<ConstraintLayout>(R.id.mainImageLayoutout)
                    constraintLayout.visibility = View.VISIBLE

                    val imMainImage: ImageView = findViewById(R.id.imMainImage)
                    imMainImage.setImageURI(Uri.parse(i.getStringExtra(MyIntentConstants.I_URI_KEY)))

                    val imButtonDeleteImage: ImageButton = findViewById(R.id.imButtonDeleteImage)
                    imButtonDeleteImage.visibility = View.GONE

                    val imButtonEditImage: ImageButton = findViewById(R.id.imButtonEditImage)
                    imButtonEditImage.visibility = View.GONE
                }
            }
        }

    }
}