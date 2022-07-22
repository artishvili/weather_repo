package com.devshish.mvvm.text

import android.content.Context

@SuppressWarnings("SpreadOperator")
fun NativeText.toCharSequence(context: Context): CharSequence {
    return when (this) {
        is NativeText.Resource -> context.getString(id)
        is NativeText.Simple -> text
    }
}
