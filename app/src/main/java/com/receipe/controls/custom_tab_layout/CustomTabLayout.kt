package com.receipe.controls.custom_tab_layout

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.receipe.R


class CustomTabLayout @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle), View.OnClickListener {

    private lateinit var view: View
    private lateinit var def: ColorStateList
    private lateinit var select: TextView
    private val listTabs: MutableList<TextView> = mutableListOf()
    private lateinit var linearLayoutSelect: LinearLayout
    private lateinit var linearLayoutTabs: LinearLayout

    init {
        init()
    }

    private fun init() {
        view = inflate(context, R.layout.custom_tab_layout, this)

        linearLayoutTabs = view.findViewById(R.id.linTabs)
        linearLayoutSelect = view.findViewById(R.id.linTabsSelect)

        getAllChildElements(linearLayoutTabs as ViewGroup)

        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f)

        repeat(listTabs.size - 1) {
            val textView = TextView(context)
            textView.layoutParams = layoutParams
            linearLayoutSelect.addView(textView)
        }

        select = view.findViewById(R.id.select)
        def = listTabs[1].textColors
    }

    override fun onClick(v: View?) {
        if (v == null) return

        listTabs.forEachIndexed { index, textView ->
            if(textView == v){
                val size = textView.width * index
                select.animate().x(size.toFloat()).duration = 100
                textView.setTextColor(Color.WHITE)
            } else {
                textView.setTextColor(def)
            }
        }
    }

    private fun getAllChildElements(layoutCont: ViewGroup){
        val mCount = layoutCont.childCount

        for (i in 0 until mCount){
            val mChild = layoutCont.getChildAt(i)
            if(mChild is TextView){
                mChild.setOnClickListener(this)
                listTabs.add(mChild)
            }
        }
    }

}