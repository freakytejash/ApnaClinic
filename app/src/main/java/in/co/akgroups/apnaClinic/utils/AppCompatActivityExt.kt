package `in`.co.akgroups.apnaClinic.utils

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

/**
 * The `fragment` is added to the container view with contentId `frameId`. The operation is
 * performed by the `fragmentManager`.
 */
fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, @IdRes frameId: Int) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
    }
}

/**
 * The `fragment` is added to the container view with tag. The operation is
 * performed by the `fragmentManager`.
 */
fun AppCompatActivity.addFragmentToActivity(fragment: Fragment, tag: String) {
    supportFragmentManager.transact {
        add(fragment, tag)
    }
}


/**
 * Runs a FragmentTransaction, then calls commit().
 */
private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

fun Fragment.replaceFragmentInFragmnent(fragment: Fragment, @IdRes frameId: Int) {
    fragmentManager!!.transact {
        replace(frameId, fragment)
    }
}

fun Fragment.replaceFragmentInFragmnentWithTag(
    fragment: Fragment, @IdRes frameId: Int,
    tag: String
) {
    fragmentManager!!.transact {
        replace(frameId, fragment, tag).addToBackStack(tag)
    }
}