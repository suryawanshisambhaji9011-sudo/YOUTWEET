package com.youtweet

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.youtweet.models.VideoModel

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
            Toast.makeText(this, "Video selected: ${selectedVideoUri?.lastPathSegment}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadVideo() {
        val title = titleInput.text.toString().trim()
        val desc = descInput.text.toString().trim()

        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show()
            return
        }

        if (selectedVideoUri == null) {
            Toast.makeText(this, "Select a video first", Toast.LENGTH_SHORT).show()
            return
        }

        val video = VideoModel(
            id = "",
            title = title,
            description = desc,
            creatorId = "user_${System.currentTimeMillis()}",
            creatorName = "YOUTWEET Creator",
            videoUrl = selectedVideoUri.toString(),
            thumbnailUrl = "",
            likes = 0,
            views = 0,
            createdAt = System.currentTimeMillis()
        )

        saveVideoToFirebase(video)
    }

    private fun saveVideoToFirebase(video: VideoModel) {
        val db = FirebaseDatabase.getInstance().getReference("videos")
        val newKey = db.push().key

        if (newKey != null) {
            val videoWithId = video.copy(id = newKey)
            db.child(newKey).setValue(videoWithId)
                .addOnSuccessListener {
                    Toast.makeText(this, "Video uploaded successfully!", Toast.LENGTH_SHORT).show()
                    titleInput.setText("")
                    descInput.setText("")
                    selectedVideoUri = null
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Upload failed. Please try again.", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
