package com.abdullah.weatherapp.util

import android.content.Context
import android.content.res.Resources
import android.support.annotation.StringRes
import android.support.v4.content.res.ResourcesCompat

/**
 * Created by Abdullah on 1.2.2018.
 */
class StringHelper {
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun getStringById(@StringRes id: Int): String {
        return context.resources.getString(id)
    }
}