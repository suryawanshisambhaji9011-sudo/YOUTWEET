package com.youtweet

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UploadActivity : AppCompatActivity() {

    private lateinit var titleInput: EditText
    private lateinit var descInput: EditText
    private var selectedVideoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        titleInput = findViewById(R.id.titleInput)
        descInput = findViewById(R.id.descriptionInput)

        findViewById<Button>(R.id.selectVideoBtn).setOnClickListener {
            selectVideo()
        }

        findViewById<Button>(R.id.uploadBtn).setOnClickListener {
            uploadVideo()
        }
    }

    private fun selectVideo() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "video/*"
        startActivityForResult(intent, 1001)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            selectedVideoUri = data?.data
            Toast.makeText(this, "Video selected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadVideo() {
        if (selectedVideoUri == null) {
            Toast.makeText(this, "Select a video first", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "Video ready for upload", Toast.LENGTH_SHORT).show()
    }
}
