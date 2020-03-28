//package `in`.co.akgroups.apnaClinic.doctor.schedule.adapter
//
//import android.support.v4.app.Fragment
//import android.support.v4.app.FragmentManager
//import android.support.v4.app.FragmentPagerAdapter
//
//class PickerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
//
//    lateinit var timePickerFragment: Fragment
//    lateinit var datePickerFragment: Fragment
//    val NUM_PAGES = 2
//
//    override fun getItem(position: Int): Fragment {
//        return when (position) {
//            0 -> TimePickerFragment()
//            1 -> DatePickerFragment()
//            else -> DatePickerFragment()
//        }
//    }
//
//
//    override fun getCount(): Int {
//        return NUM_PAGES
//    }
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        return super.getPageTitle(position)
//    }
//}