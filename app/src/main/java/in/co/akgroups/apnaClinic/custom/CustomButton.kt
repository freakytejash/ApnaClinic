package `in`.co.akgroups.apnaClinic.custom

import `in`.co.akgroups.apnaClinic.R
import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.AppCompatButton
import android.text.TextUtils
import android.util.AttributeSet

class CustomButton : AppCompatButton {

    private val PATH_FONT_BLACK = "Avenir-Black.ttf"
    private val PATH_FONT_HEAVY = "Avenir-Heavy.ttf"
    private val PATH_FONT_ROMAN = "Avenir-Roman.ttf"


    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs)
    }

    private fun init(context: Context, attributeSet: AttributeSet?) {

        if (attributeSet != null) {
            val arr = context.obtainStyledAttributes(attributeSet, R.styleable.CustomTextView)
            val value = arr.getString(R.styleable.CustomTextView_type_face)

            var typeface = PATH_FONT_ROMAN
            if (!TextUtils.isEmpty(value)) {
                if (context.getString(R.string.font_roman) == value) {
                    typeface = PATH_FONT_ROMAN
                } else if (context.getString(R.string.font_heavy) == value) {
                    typeface = PATH_FONT_HEAVY
                } else if (context.getString(R.string.font_black) == value) {
                    typeface = PATH_FONT_BLACK
                }

                var tf: Typeface? = null
                try {
                    tf = Typeface.createFromAsset(context.assets, typeface)
                    setTypeface(tf)

                } catch (e: Exception) {
                    return
                }

            }

            arr.recycle()
        }
    }
}