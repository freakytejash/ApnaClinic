package `in`.co.akgroups.apnaClinic.custom

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class CustomViewPager : ViewPager {

    // by default paging disabled
    private var isPagingEnabled: Boolean = false

    constructor(context: Context) : super(context) {
        this.isPagingEnabled = false
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        this.isPagingEnabled = true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return this.isPagingEnabled && super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event)
    }

    fun setPagingEnabled(enabled: Boolean) {
        this.isPagingEnabled = enabled
    }
}