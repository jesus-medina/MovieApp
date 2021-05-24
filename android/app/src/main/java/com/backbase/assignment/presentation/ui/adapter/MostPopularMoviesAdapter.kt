package com.backbase.assignment.presentation.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.databinding.ItemMostPopularMovieBinding
import com.backbase.assignment.presentation.UIMovie
import com.backbase.assignment.utils.extension.inflate
import java.text.DateFormat

class MostPopularMoviesAdapter(
    private val releaseDateFormat: DateFormat,
    private val itemClickCallback: (UIMovie.UIMostPopularMovie) -> Unit
) : ListAdapter<UIMovie.UIMostPopularMovie, MostPopularMovieViewHolder>(MoviesDiffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostPopularMovieViewHolder {
        val view = parent.inflate(R.layout.item_most_popular_movie, false)

        return MostPopularMovieViewHolder(view, releaseDateFormat, itemClickCallback)
    }

    override fun onBindViewHolder(holderMostPopular: MostPopularMovieViewHolder, position: Int) {
        val uiMostPopularMovie = getItem(position)
        holderMostPopular bind uiMostPopularMovie
    }
}

class MostPopularMovieViewHolder(view: View, private val releaseDateFormat: DateFormat, private val itemClickCallback: (UIMovie.UIMostPopularMovie) -> Unit) :
    RecyclerView.ViewHolder(view) {
    private val binding: ItemMostPopularMovieBinding = ItemMostPopularMovieBinding.bind(view)

    infix fun bind(uiMostPopularMovie: UIMovie.UIMostPopularMovie) {
        binding bind uiMostPopularMovie
    }

    private infix fun ItemMostPopularMovieBinding.bind(uiMostPopularMovie: UIMovie.UIMostPopularMovie) {
        with(uiMostPopularMovie) {
            root.setOnClickListener { itemClickCallback(uiMostPopularMovie) }
            posterSimpleDraweeView.setImageURI(posterImage)
            titleTextView.text = title
            subtitleTextView.text = root.context.getString(R.string.most_popular_movies_subtitle).format(releaseDateFormat.format(releaseDate), duration)
            ratingView.rating = rating
        }
    }
}

object MoviesDiffUtilCallback : DiffUtil.ItemCallback<UIMovie.UIMostPopularMovie>() {
    override fun areItemsTheSame(
        oldItem: UIMovie.UIMostPopularMovie,
        newItem: UIMovie.UIMostPopularMovie
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: UIMovie.UIMostPopularMovie,
        newItem: UIMovie.UIMostPopularMovie
    ): Boolean =
        oldItem == newItem
}