package com.mbh.moviebrowser

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mbh.moviebrowser.common.SharedViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var sharedViewModel: SharedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]
        initObserver()

    }

    fun initObserver() {
        sharedViewModel.showLoadingSpinner.observe(this, Observer {
            sharedViewModel.showLoadingSpinner.value
                ?.let { showLoadingSpinner ->
                    if (showLoadingSpinner) {
                        showLoadingSpinner()
                    } else {
                        hideLoadingSpinner()
                    }
                }
        })
    }

    fun removeObserver() {
        sharedViewModel.showLoadingSpinner.removeObservers(this)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        removeObserver()
    }

    fun showBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun hideBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun showLoadingSpinner() {
        findViewById<ProgressBar>(R.id.loadingSpinner).visibility = View.VISIBLE
    }

    private fun hideLoadingSpinner() {
        findViewById<ProgressBar>(R.id.loadingSpinner).visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
