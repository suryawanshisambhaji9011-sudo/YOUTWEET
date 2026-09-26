package com.youtweet.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.youtweet.R
import com.youtweet.models.VideoModel

class VideoAdapter(
    private val videos: List<VideoModel>
) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.videoTitle)
        val description: TextView = itemView.findViewById(R.id.videoDescription)
        val creatorName: TextView? = itemView.findViewById(R.id.creatorName)
        val likesText: TextView? = itemView.findViewById(R.id.likesText)
        val viewsText: TextView? = itemView.findViewById(R.id.viewsText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videos[position]
        holder.title.text = video.title
        holder.description.text = video.description
        holder.creatorName?.text = "by ${video.creatorName}"
        holder.likesText?.text = "❤️ ${video.likes}"
        holder.viewsText?.text = "👁️ ${video.views}"
    }

    override fun getItemCount(): Int = videos.size
}
