package com.backbase.assignment.presentation.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.databinding.ItemNowPlayingMovieBinding
import com.backbase.assignment.presentation.UIMovie
import com.backbase.assignment.utils.extension.inflate

class NowPlayingMoviesAdapter :
    ListAdapter<UIMovie.UINowPlayingMovie, NowPlayingMovieViewHolder>(
        NowPlayingMovieDiffUtillCallback
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingMovieViewHolder {
        val view = parent.inflate(R.layout.item_now_playing_movie, false)

        return NowPlayingMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: NowPlayingMovieViewHolder, position: Int) {
        val uiNowPlayingMovie = getItem(position)
        holder bind uiNowPlayingMovie
    }
}

class NowPlayingMovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding: ItemNowPlayingMovieBinding = ItemNowPlayingMovieBinding.bind(view)

    infix fun bind(uiNowPlayingMovie: UIMovie.UINowPlayingMovie) {
        binding bind uiNowPlayingMovie
    }

    private infix fun ItemNowPlayingMovieBinding.bind(uiNowPlayingMovie: UIMovie.UINowPlayingMovie) {
        root.setImageURI(uiNowPlayingMovie.posterImage)
    }
}

object NowPlayingMovieDiffUtillCallback : DiffUtil.ItemCallback<UIMovie.UINowPlayingMovie>() {
    override fun areItemsTheSame(
        oldItem: UIMovie.UINowPlayingMovie,
        newItem: UIMovie.UINowPlayingMovie
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: UIMovie.UINowPlayingMovie,
        newItem: UIMovie.UINowPlayingMovie
    ): Boolean = oldItem == newItem
}