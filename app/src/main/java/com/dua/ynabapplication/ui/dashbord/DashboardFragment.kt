package com.dua.ynabapplication.ui.dashbord

import android.animation.ObjectAnimator
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dua.ynabapplication.R
import com.dua.ynabapplication.base.BaseFragment
import com.dua.ynabapplication.ui.budget.BudgetRecyclerAdapter
import com.dua.ynabapplication.utils.DateTimeUtil
import com.dua.ynabapplication.utils.extension.*
import com.dua.ynabapplication.utils.extension.getImprovedViewModel

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome
import com.mikepenz.iconics.utils.colorRes
import com.mikepenz.iconics.utils.sizeDp
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dashboard.*

import java.math.BigDecimal


class DashboardFragment: BaseFragment() {

    private val transactionExtendedFab by bindView<ExtendedFloatingActionButton>(R.id.addTransactionExtended)
    private val dashboardView by lazy { getImprovedViewModel(DashboardViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.create(R.layout.fragment_dashboard,container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setSummary()
        dashboardView.currencySymbol.observe(viewLifecycleOwner){ symbol ->
            setPieChart()
            setNetIncome(symbol)
            setAverage()
            loadRecentTransaction()
        }
        setExtendedFab()
        budgetCard.setOnClickListener {
            parentFragmentManager.commit {
//                replace(R.id.fragment_container, BudgetSummaryFragment())
                addToBackStack(null)
            }
        }
        setIcon()
        dashboardView.apiResponse.observe(viewLifecycleOwner){ response ->
            Snackbar.make(coordinatorlayout, response, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun setExtendedFab(){
        transactionExtendedFab.isVisible = true
        dashboardNested.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                transactionExtendedFab.shrink()
            } else {
                transactionExtendedFab.extend()
            }
        }
        transactionExtendedFab.setOnClickListener {
//            requireActivity().startActivity(Intent(requireContext(), AddTransactionActivity::class.java))
        }
    }

    private fun setSummary(){
        dashboardView.networthValue.observe(viewLifecycleOwner){ money ->
//             networthAmount.text = money
        }

        dashboardView.leftToSpendValue.observe(viewLifecycleOwner){ money ->
//            leftToSpendAmountText.text = money
        }
        dashboardView.balanceValue.observe(viewLifecycleOwner){ money ->
//            balanceText.text = money
//            updateHomeScreenWidget(BalanceWidget::class.java)
        }
        dashboardView.earnedValue.observe(viewLifecycleOwner){ money ->
//            balanceEarnedText.text = money + " + "
        }
        dashboardView.spentValue.observe(viewLifecycleOwner){ money ->
//            balanceSpentText.text = money
        }
        dashboardView.billsToPay.observe(viewLifecycleOwner){ money ->
//            billsText.text = money
//            updateHomeScreenWidget(BillsToPayWidget::class.java)
        }

        dashboardView.billsPaid.observe(viewLifecycleOwner){ money ->
//            billsPaidText.text = money
        }

        dashboardView.leftToSpendDay.observe(viewLifecycleOwner){ money ->
//            leftToSpendAmount.text = money
        }
    }

    private fun updateHomeScreenWidget(className: Class<*>){
        val updateIntent = Intent(requireContext(), className)
        updateIntent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        val ids = AppWidgetManager.getInstance(requireContext())
                .getAppWidgetIds(ComponentName(requireContext(), className))
        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        requireActivity().sendBroadcast(updateIntent)
    }

    private fun setIcon(){
//        balanceIcon.setImageDrawable(IconicsDrawable(requireContext()).apply {
//            icon = FontAwesome.Icon.faw_balance_scale
//            colorRes = R.color.md_white_1000
//            sizeDp = 32
//        })
//        billsIcon.setImageDrawable(IconicsDrawable(requireContext()).apply {
//            icon = FontAwesome.Icon.faw_calendar
//            colorRes =R.color.md_white_1000
//            sizeDp = 32
//        })
//        leftToSpendIcon.setImageDrawable(IconicsDrawable(requireContext()).apply {
//            icon = FontAwesome.Icon.faw_money_bill
//            colorRes = R.color.md_white_1000
//            sizeDp = 32
//        })
//        networthIcon.setImageDrawable(IconicsDrawable(requireContext()).apply {
//            icon = FontAwesome.Icon.faw_chart_line
//            colorRes = R.color.md_white_1000
//            sizeDp = 32
//        })
    }

    private fun setNetIncome(currencySymbol: String){
        zipLiveData(dashboardView.currentMonthDepositLiveData, dashboardView.currentMonthWithdrawalLiveData,
                dashboardView.lastMonthDepositLiveData, dashboardView.lastMonthWithdrawalLiveData,
                dashboardView.twoMonthsAgoDepositLiveData,
                dashboardView.twoMonthsAgoWithdrawalLiveData).observe(viewLifecycleOwner){ value ->

        }
    }



    private fun setAverage(){
        dashboardView.sixDayWithdrawalLiveData.observe(viewLifecycleOwner){ value ->

            }
            sixDaysAverage.text = dashboardView.sixDaysAverage
            thirtyDaysAverage.text = dashboardView.thirtyDayAverage
        }
    }

    private fun setPieChart() {

    }

    private fun loadRecentTransaction(){
//        val recyclerAdapter = TransactionAdapter{ data -> itemClicked(data) }
//        recentTransactionList.layoutManager = LinearLayoutManager(requireContext())
//        recentTransactionList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
//        recentTransactionList.adapter = recyclerAdapter
//        transactionLoader.show()
//        dashboardView.getRecentTransactions().observe(viewLifecycleOwner){ pagingData ->
//            recyclerAdapter.submitData(lifecycle, pagingData)
//        }
//
//        recyclerAdapter.loadStateFlow.asLiveData().observe(viewLifecycleOwner){ loadStates ->
//            if(loadStates.refresh !is LoadState.Loading) {
//                transactionLoader.hide()
//                if(recyclerAdapter.itemCount < 1) {
//                    recentTransactionList.isGone = true
//                    noTransactionText.isVisible = true
//                } else {
//                    recentTransactionList.isVisible = true
//                    noTransactionText.isGone = true
//                }
//            }
//        }
    }






//    override fun onAttach(context: Context){
//        super.onAttach(context)
//        activity?.activity_toolbar?.title = resources.getString(R.string.dashboard)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        activity?.activity_toolbar?.title = resources.getString(R.string.dashboard)
//    }
