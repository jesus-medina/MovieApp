package com.backbase.assignment.utils.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)