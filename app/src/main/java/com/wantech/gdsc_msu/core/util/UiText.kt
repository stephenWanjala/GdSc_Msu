package com.wantech.gdsc_msu.core.util

import androidx.annotation.StringRes
import com.wantech.gdsc_msu.R

sealed class UiText {
    data class DynamicString(val value: String): UiText()
    data class StringResource(@StringRes val id: Int): UiText()

    companion object {
        fun unknownError(): UiText {
            return StringResource(R.string.error_unknown)
        }
    }
}