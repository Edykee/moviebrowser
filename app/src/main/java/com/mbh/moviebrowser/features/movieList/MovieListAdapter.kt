package com.mbh.moviebrowser.features.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbh.moviebrowser.databinding.ItemMovieBinding
import com.mbh.moviebrowser.domain.Genre
import com.mbh.moviebrowser.domain.Movie
import java.util.stream.Collectors

class MovieListAdapter(
    private var movies: List<Movie>,
    private var genreMap: Map<Int, Genre>,
    private var movieClickHandler: MovieClickHandler
) : RecyclerView.Adapter<MovieListViewHolder>() {
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
        holder.itemMovieBinding.setGenres(convertGenreIdsToString(movie, genreMap))
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

    private fun convertGenreIdsToString(movie: Movie, genreMap: Map<Int, Genre>): String {
        return movie.genreIds
            .stream()
            .map { id -> genreMap[id]?.name ?: "" }
            .filter { str -> str.isNotEmpty() }
            .collect(Collectors.joining(", "))
    }
}