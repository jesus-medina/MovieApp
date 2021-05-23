package com.backbase.assignment.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.databinding.ItemMovieBinding
import com.backbase.assignment.presentation.UIMovie

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

class MoviesAdapter : ListAdapter<UIMovie.UIMostPopularMovie, MovieViewHolder>(MoviesDiffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = parent.inflate(R.layout.item_movie, false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val uiMostPopularMovie = getItem(position)
        holder bind uiMostPopularMovie
    }
}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding: ItemMovieBinding = ItemMovieBinding.bind(view)

    infix fun bind(uiMostPopularMovie: UIMovie.UIMostPopularMovie) {
        binding bind uiMostPopularMovie
    }

    private infix fun ItemMovieBinding.bind(uiMostPopularMovie: UIMovie.UIMostPopularMovie) {
        titleTextView.text = uiMostPopularMovie.title
    }

}

object MoviesDiffUtilCallback : DiffUtil.ItemCallback<UIMovie.UIMostPopularMovie>() {
    override fun areItemsTheSame(oldItem: UIMovie.UIMostPopularMovie, newItem: UIMovie.UIMostPopularMovie): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: UIMovie.UIMostPopularMovie, newItem: UIMovie.UIMostPopularMovie): Boolean =
        oldItem == newItem
}