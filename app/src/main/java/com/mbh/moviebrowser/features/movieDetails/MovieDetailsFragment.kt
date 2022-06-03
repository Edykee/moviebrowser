package com.mbh.moviebrowser.features.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mbh.moviebrowser.MainActivity
import com.mbh.moviebrowser.R

class MovieDetailsFragment : Fragment(){
    val args: MovieDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val movieId = args.movieId;
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBackButton(true)
    }
}
