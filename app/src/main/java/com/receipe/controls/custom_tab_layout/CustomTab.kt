package com.receipe.controls.custom_tab_layout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import com.receipe.R

class CustomTab @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyle) {

    private lateinit var view: View

    init {
        init()
    }

    private fun init() {
        view = inflate(context, R.layout.custom_tab_layout, null)
    }
}