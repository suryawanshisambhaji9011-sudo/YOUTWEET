package com.youtweet.firebase

import com.google.firebase.database.FirebaseDatabase
import com.youtweet.models.VideoModel

class FirebaseHelper {
    private val db = FirebaseDatabase.getInstance()
    private val videosRef = db.getReference("videos")

    fun uploadVideo(video: VideoModel, callback: (Boolean) -> Unit) {
        val id = videosRef.push().key ?: return
        val item = video.copy(id = id)

        videosRef.child(id).setValue(item)
            .addOnSuccessListener { callback(true) }
            .addOnFailureListener { callback(false) }
    }
}
