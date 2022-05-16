package com.varani.gtmotors.ui.searchfilter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.varani.gtmotors.R

class SearchFilterFragment : BottomSheetDialogFragment() {

    private lateinit var bottomSheet: View
    private lateinit var filterNameView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_filter_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
    }

    private fun setupViews(view: View) {
        filterNameView = view.findViewById(R.id.txt_filter_name)
        bottomSheet = view.findViewById(R.id.search_filter_bottom_sheet)

        setupSafeArgs()
        setupBottomSheet()
    }

    private fun setupSafeArgs() {
        val safeArgs: SearchFilterFragmentArgs by navArgs()
        val chosenFilter = safeArgs.filter
        filterNameView.text = chosenFilter.name
    }

    private fun setupBottomSheet() {
//        val behavior = BottomSheetBehavior.from(bottomSheet)
//        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
}

enum class Filter {
    MAKE_MODEL,
    YEAR
}