package `in`.co.akgroups.apnaClinic.custom

import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.SparseArray
import android.view.ViewGroup
import java.lang.ref.WeakReference

class FragmentPagerAdapter(manager : FragmentManager) : FragmentStatePagerAdapter(manager) {

    private var instantiatedFragments =  SparseArray<WeakReference<Fragment>>()
    private var mFragmentList = ArrayList<Fragment>()
    private var mFragmentTitleList = ArrayList<String>()

    override fun getItem(position : Int) : Fragment {
        return mFragmentList.get(position)
    }

    override fun getCount() : Int {
        return mFragmentList.size
    }

    fun addFragment(fragment : Fragment, title : String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun instantiateItem(container : ViewGroup, position : Int) : Any {
        var fragment =  super.instantiateItem(container, position) as Fragment
        instantiatedFragments.put(position, WeakReference(fragment))
        return fragment
    }

    override fun destroyItem(container : ViewGroup, position : Int, any : Any) {
        instantiatedFragments.remove(position)
        super.destroyItem(container, position, any)
    }

    @Nullable
    fun getFragment(position : Int) : Fragment? {
        var wr = instantiatedFragments.get(position)
        if (wr != null) {
            return wr.get()
        } else {
            return null
        }
    }

    override fun getPageTitle(position : Int) : CharSequence {
        return mFragmentTitleList.get(position)
    }

}