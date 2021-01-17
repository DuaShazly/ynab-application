package com.dua.ynabapplication.ui.budget

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.fragment.app.commit
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dua.ynabapplication.R
import com.dua.ynabapplication.base.BaseFragment
import com.dua.ynabapplication.repository.models.transaction.Transactions
import com.dua.ynabapplication.utils.DateTimeUtil
import com.dua.ynabapplication.utils.extension.bindView
import com.dua.ynabapplication.utils.extension.create
import com.dua.ynabapplication.utils.extension.getImprovedViewModel

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.googlematerial.GoogleMaterial
import com.mikepenz.iconics.utils.colorRes
import com.mikepenz.iconics.utils.sizeDp
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_budget_summary.*
import java.math.BigDecimal
import java.util.*

class BudgetSummaryFragment: BaseFragment(), AdapterView.OnItemSelectedListener {

    private val transactionExtendedFab by bindView<ExtendedFloatingActionButton>(R.id.addTransactionExtended)
    private val budgetSummaryViewModel by lazy { getImprovedViewModel(BudgetSummaryViewModel::class.java) }
//    private val transactionAdapter by lazy { TransactionSeparatorAdapter{ data -> itemClicked(data) } }
    private val coloring = arrayListOf<Int>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.create(R.layout.fragment_budget_summary, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSpinner()
        setText()
        loadTransactionList(null)
        budgetSummaryViewModel.pieChartData.observe(viewLifecycleOwner){ list ->
            setPieChart(list)
        }
    }

//    private fun setWidget(){
//        transactionExtendedFab.isGone = true
//        for (col in ColorTemplate.COLORFUL_COLORS) {
//            coloring.add(col)
//        }
//        for (col in ColorTemplate.JOYFUL_COLORS){
//            coloring.add(col)
//        }
//        monthAndYearText.text = DateTimeUtil.getMonthAndYear(DateTimeUtil.getTodayDate())
//        transactionList.layoutManager = LinearLayoutManager(requireContext())
//        transactionList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
//        transactionList.adapter = transactionAdapter
//        budgetSummaryPieChart.isDrawHoleEnabled = false
//        previousMonthArrow.setImageDrawable(IconicsDrawable(requireContext()).apply {
//            icon = GoogleMaterial.Icon.gmd_keyboard_arrow_left
//            sizeDp = 24
//            colorRes = R.color.colorPrimary
//        })
//        nextMonthArrow.setImageDrawable(IconicsDrawable(requireContext()).apply {
//            icon = GoogleMaterial.Icon.gmd_keyboard_arrow_right
//            sizeDp = 24
//            colorRes = R.color.colorPrimary
//        })
//        previousMonthArrow.setOnClickListener {
//            budgetSummaryViewModel.monthCount -=1
//            setDate()
//            transactionAdapter.notifyDataSetChanged()
//            budgetSummaryPieChart.clear()
//        }
//        nextMonthArrow.setOnClickListener {
//            budgetSummaryViewModel.monthCount +=1
//            setDate()
//            transactionAdapter.notifyDataSetChanged()
//            budgetSummaryPieChart.clear()
//        }
//        setDate()
//    }

    private fun setDate(){
        budgetSummaryViewModel.setDisplayDate().observe(viewLifecycleOwner){ dateToDisplay ->
            monthAndYearText.text = dateToDisplay
        }
    }

    private fun setSpinner(){
        currencySpinner.onItemSelectedListener = this
        budgetSummaryViewModel.getCurrency().observe(viewLifecycleOwner){ currencyDataList ->
            val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, currencyDataList)
            currencySpinner.adapter = arrayAdapter
        }
    }

    private fun setText(){
        budgetSummaryViewModel.availableBudget.observe(viewLifecycleOwner){ available ->
            budgetAmountValue.text = available
        }
        budgetSummaryViewModel.totalTransaction.observe(viewLifecycleOwner){ total ->
            actualAmountValue.text = total
        }
        budgetSummaryViewModel.balanceBudget.observe(viewLifecycleOwner){ balance ->
            remainingAmountValue.text = balance.toString()
        }
    }

    private fun setPieChart(pieChartData: List<Triple<Float, String, BigDecimal>>){



        loadTransactionList(null)
    }

    private fun itemClicked(data: Transactions){
        parentFragmentManager.commit {
//            replace(R.id.fragment_container, TransactionDetailsFragment().apply {
//                arguments = bundleOf("transactionJournalId" to data.transaction_journal_id)
//            })
            addToBackStack(null)
        }
    }

    private fun loadTransactionList(budget: String?){
       budgetSummaryViewModel.getTransactionList(budget)?.observe(viewLifecycleOwner){ transactionList ->
//           transactionAdapter.submitData(lifecycle,transactionList)
       }
    }

    override fun onAttach(context: Context){
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        budgetSummaryViewModel.changeCurrency(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }


}