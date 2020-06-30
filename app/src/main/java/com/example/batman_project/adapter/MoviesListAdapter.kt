package com.example.batman_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.example.batman_project.model.Search
import com.example.batman_project.databinding.ListItemsBinding

class MoviesListAdapter() : ListAdapter<Search, MoviesListAdapter.MoviesViewHolder>(
    MoviesDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder.createViewHolder(
            parent
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MoviesViewHolder private constructor(private val binding: ListItemsBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun createViewHolder(parent: ViewGroup): MoviesViewHolder {
                val binding = ListItemsBinding.
                    inflate(LayoutInflater.from(parent.context), parent, false)

                return MoviesViewHolder(
                    binding
                )
            }
        }

        fun bind(search: Search){
            binding.movieTxt.text = search.Title
            binding.movieImg.load(search.Poster) {
                crossfade(false)
                scale(Scale.FILL)
                transformations(RoundedCornersTransformation(4f))
            }
        }

    }

}

class MoviesDiffUtil : DiffUtil.ItemCallback<Search>() {
    override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
        return oldItem.imdbID == newItem.imdbID
    }

    override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
        return newItem == oldItem
    }
}