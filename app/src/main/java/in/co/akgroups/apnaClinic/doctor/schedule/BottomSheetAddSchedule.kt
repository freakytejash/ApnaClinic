package `in`.co.akgroups.apnaClinic.doctor.schedule

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BottomSheetAddSchedule : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(bundle: Bundle): BottomSheetAddSchedule {
            val showPlatformsInPopupFragment = BottomSheetAddSchedule()
            showPlatformsInPopupFragment.arguments = bundle
            return showPlatformsInPopupFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}