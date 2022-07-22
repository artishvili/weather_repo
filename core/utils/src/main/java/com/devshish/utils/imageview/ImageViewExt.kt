package com.devshish.utils.imageview

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load

/**
 * Loads drawable resource [picture] into this [ImageView]
 */
fun ImageView.loadDrawableRes(@DrawableRes picture: Int) {
    load(picture) {
        crossfade(true)

        placeholder(picture)
        error(picture)
        fallback(picture)
    }
}
