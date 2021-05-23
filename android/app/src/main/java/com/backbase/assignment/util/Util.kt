package com.backbase.assignment.util

import android.app.Activity
import android.content.Intent

inline fun <reified T: Activity> Activity.startActivity() {
    val startMovieDetailsActivityIntent = Intent(this, T::class.java)
    startActivity(startMovieDetailsActivityIntent)
}