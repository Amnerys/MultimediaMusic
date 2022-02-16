package com.example.pac_uf2_ferrermonica

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pac_uf2_ferrermonica.SongListAdapter.SongViewHolder
import com.example.pac_uf2_ferrermonica.database.Song

class SongListAdapter : ListAdapter<Song, SongViewHolder>(SongComparator()) {

    // Adaptador para las canciones, usaremos ViewHolder y RecylerView para inflar la lista con los datos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val artistaItemView: TextView = itemView.findViewById(R.id.textView_artista)
        private val cancionItemView: TextView = itemView.findViewById(R.id.textView_cancion)
        private val albumItemView: TextView = itemView.findViewById(R.id.textView_album)

        fun bind(text: Song?) {
            if (text != null) {
                artistaItemView.text = text.artista
            }
            if (text != null) {
                cancionItemView.text = text.cancion
            }
            if (text != null) {
                albumItemView.text = text.album
            }
        }

        companion object {
            fun create(parent: ViewGroup): SongViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return SongViewHolder(view)
            }
        }
    }

    class SongComparator : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.cancion == newItem.cancion
        }
    }
}