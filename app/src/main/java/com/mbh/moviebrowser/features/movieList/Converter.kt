package com.mbh.moviebrowser.features.movieList

import com.mbh.moviebrowser.model.Genre
import com.mbh.moviebrowser.model.Movie
import java.util.stream.Collectors

class Converter {
    companion object {
        fun convertGenreIdsToString(movie: Movie, genreMap: Map<Int, Genre>): String {
            return movie.genreIds
                .stream()
                .map { id -> genreMap[id]?.name ?: "" }
                .filter { str -> str.isNotEmpty() }
                .collect(Collectors.joining(", "))
        }
    }

}