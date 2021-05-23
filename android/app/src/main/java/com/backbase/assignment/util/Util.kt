package com.backbase.assignment.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle

inline fun <reified T : Activity> Activity.startActivity(options: Bundle?) {
    val startMovieDetailsActivityIntent = Intent(this, T::class.java).apply {
        options?.let {
            putExtras(it)
        }
    }
    startActivity(startMovieDetailsActivityIntent)
}