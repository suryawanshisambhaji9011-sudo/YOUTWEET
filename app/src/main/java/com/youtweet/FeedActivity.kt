package com.youtweet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.youtweet.adapters.VideoAdapter
import com.youtweet.models.VideoModel

class FeedActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val videoList = mutableListOf<VideoModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadVideos()
    }

    private fun loadVideos() {
        val videosRef = FirebaseDatabase
            .getInstance()
            .getReference("videos")

        videosRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                videoList.clear()

                for (child in snapshot.children) {
                    val video = child.getValue(VideoModel::class.java)
                    if (video != null) {
                        videoList.add(video)
                    }
                }

                recyclerView.adapter = VideoAdapter(videoList)
            }

            override fun onCancelled(error: DatabaseError) {
                // Firebase read error
            }
        })
    }
}
