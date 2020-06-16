package com.example.hogwarts.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.hogwarts.R

class ExpandableView : FrameLayout {
    private var titleTextView: TextView
    private var expandingView: LinearLayout
    private var rightChevronImageButton: ImageButton
    private var itemSelectedListener: ((String) -> Unit)? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        val rootView = LayoutInflater.from(context).inflate(R.layout.expandable_view, this)
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.ExpandableViewWidget, defStyleAttr, 0)

        titleTextView = rootView.findViewById(R.id.title)
        expandingView = rootView.findViewById(R.id.expanding_view)
        rightChevronImageButton = rootView.findViewById(R.id.right_chevron)

        try {
            rightChevronImageButton.setImageDrawable(if (typedArray.getDrawable(R.styleable.ExpandableViewWidget_chevron_src)  != null) {
                typedArray.getDrawable(R.styleable.ExpandableViewWidget_chevron_src)
            } else {
                ContextCompat.getDrawable(context, R.drawable.ic_baseline_keyboard_arrow_down_24)
            })
            titleTextView.text = typedArray.getString(R.styleable.ExpandableViewWidget_title_text)
        } finally {
            typedArray.recycle()
        }

        rightChevronImageButton.setOnClickListener { toggle() }
    }

    /**
     * Add all xml specified child views to expandingView instead of root view.
     */
    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams) {

        /**
         * Race condition between addView() execution and constructor view inflation
         * causes null pointer exception for expandingView.
         */
        @Suppress("SENSELESS_COMPARISON")
        if (expandingView != null) {
            expandingView.addView(child, expandingView.childCount, params)
        } else {
            super.addView(child, super.getChildCount(), params)
        }
    }

    /**
     * Remove all child views from expandingView instead of root view.
     */
    override fun removeAllViews() {
        expandingView.removeAllViews()
    }

    fun setTitleText(titleText: String) {
        titleTextView.text = titleText
    }

    fun toggle() {
        expandingView.visibility = if (expandingView.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        val degree = if (expandingView.visibility == View.GONE) 0f else 180f
        rightChevronImageButton.animate().rotation(degree).interpolator = AccelerateDecelerateInterpolator()
        itemSelectedListener?.let {
            it(titleTextView.text.toString())
        }
    }

    fun addItemSelectedListener(itemSelectedListener: (String) -> Unit) {
        this.itemSelectedListener = itemSelectedListener
    }
}