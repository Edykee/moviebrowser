package com.mbh.moviebrowser.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val showLoadingSpinner: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
}