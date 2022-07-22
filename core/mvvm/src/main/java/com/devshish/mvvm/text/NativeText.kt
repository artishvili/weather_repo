package com.devshish.mvvm.text

import androidx.annotation.StringRes

sealed class NativeText {
    data class Simple(val text: String) : NativeText()
    data class Resource(@StringRes val id: Int) : NativeText()
}
