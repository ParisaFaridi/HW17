package com.example.homework17

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.homework17.data.model.Movie
import com.example.homework17.databinding.MovieItemBinding
import com.example.homework17.network.POSTER_PATH

typealias ClickHandler = (Movie) -> Unit

class MovieAdapter(private val clickHandler: ClickHandler):
    ListAdapter<Movie, MovieAdapter.ItemHolder>(FormulaDiffCallBack) {

    object FormulaDiffCallBack: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ItemHolder(val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding: MovieItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.movie_item,
            parent,
            false
        )
        return ItemHolder(binding)
    }
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.binding.movie = getItem(position)
        Glide.with(holder.binding.imageView.context).load(POSTER_PATH + getItem(position).poster_path)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(holder.binding.imageView)
        holder.binding.imageView.setOnClickListener {
            clickHandler.invoke(getItem(position))
        }
    }
}
