package com.mbh.moviebrowser.features.movieList

import kotlin.math.roundToInt

object ConversionUtil {

    fun convertRatingToPercent(rating: Float): Int {
        return (rating * 10).roundToInt()
    }
}