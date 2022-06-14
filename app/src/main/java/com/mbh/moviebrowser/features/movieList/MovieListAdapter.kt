package com.mbh.moviebrowser.features.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbh.moviebrowser.databinding.ItemMovieBinding
import com.mbh.moviebrowser.model.Genre
import com.mbh.moviebrowser.model.Movie

class MovieListAdapter(
    private var movies: List<Movie>,
    private var genreMap: Map<Int, Genre>,
    private var movieClickHandler: MovieClickHandler
) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieListViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return movies.size;
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = movies[position]
        holder.itemMovieBinding.movie = movie
        holder.itemMovieBinding.setGenres(Converter.convertGenreIdsToString(movie, genreMap))
        holder.itemMovieBinding.movieClickHandler = this.movieClickHandler
    }

    fun updateMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    fun updateGenres(genreMap: Map<Int, Genre>) {
        this.genreMap = genreMap;
        if (movies.isNotEmpty()) {
            notifyDataSetChanged()
        }
    }

    class MovieListViewHolder(val itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {
    }
}