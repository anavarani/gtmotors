package com.varani.gtmotors.ui.searchfilter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.varani.gtmotors.R
import com.varani.gtmotors.ui.search.SearchViewModel
import com.varani.gtmotors.utils.show

class SearchFilterFragment : BottomSheetDialogFragment() {

    private lateinit var filter: Filter
    private lateinit var bottomSheet: View
    private lateinit var makeEditText: EditText
    private lateinit var modelEditText: EditText
    private lateinit var yearEditText: EditText
    private lateinit var confirmButton: ImageView
    private lateinit var makeModelLayout: View
    private lateinit var yearLayout: View

    private val searchViewModel: SearchViewModel by activityViewModels()

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
        observeLiveData()
    }

    private fun setupViews(view: View) {
        bottomSheet = view.findViewById(R.id.search_filter_bottom_sheet)
        makeEditText = view.findViewById(R.id.make_edit_text)
        modelEditText = view.findViewById(R.id.model_edit_text)
        yearEditText = view.findViewById(R.id.year_edit_text)
        confirmButton = view.findViewById(R.id.confirm_button)
        makeModelLayout = view.findViewById(R.id.make_model_filter_layout)
        yearLayout = view.findViewById(R.id.year_filter_layout)

        confirmButton.setOnClickListener {
            when (filter) {
                Filter.MAKE_MODEL -> searchViewModel.setMakeModel(
                    makeEditText.text.toString(),
                    modelEditText.text.toString()
                )
                Filter.YEAR -> searchViewModel.setYear(yearEditText.text.toString())
            }
            findNavController().navigateUp()
        }

        setupSafeArgs()
        setupBottomSheet()
    }

    private fun setupSafeArgs() {
        val safeArgs: SearchFilterFragmentArgs by navArgs()
        filter = safeArgs.filter
        setFilterLayoutVisibility()
    }

    private fun setFilterLayoutVisibility() {
        makeModelLayout.show(filter == Filter.MAKE_MODEL)
        yearLayout.show(filter == Filter.YEAR)
    }

    private fun setupBottomSheet() {
        val behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        behavior.isDraggable = false
        behavior.isHideable = false
    }

    private fun observeLiveData() {
        searchViewModel.apply {
            searchFilterState.observe(
                requireActivity(),
                Observer {
                    onFilterUpdated(it)
                }
            )
        }
    }

    private fun onFilterUpdated(state: SearchViewModel.FilterState) {
        when (filter) {
            Filter.MAKE_MODEL -> {
                makeEditText.setText(state.make)
                modelEditText.setText(state.model)
            }
            Filter.YEAR -> {
                yearEditText.setText(state.year)
            }
        }
    }
}

enum class Filter {
    MAKE_MODEL,
    YEAR
}