package com.dua.ynabapplication.ui.dashbord

import android.app.Application
import androidx.lifecycle.MutableLiveData

import com.dua.ynabapplication.data.local.dao.AppDatabase
import com.dua.ynabapplication.data.remote.api.BudgetService
import com.dua.ynabapplication.repository.BaseViewModel
import com.dua.ynabapplication.repository.models.budget.BudgetRepository
import com.dua.ynabapplication.utils.DateTimeUtil
import com.dua.ynabapplication.utils.network.Sixple

import java.math.BigDecimal

class DashboardViewModel(application: Application): BaseViewModel(application) {


    private val budgetRepository = BudgetRepository(
            AppDatabase.getInstance(application).budgetDataDao(),
            AppDatabase.getInstance(application).budgetListDataDao(),
            AppDatabase.getInstance(application).spentDataDao(),
            AppDatabase.getInstance(application).budgetLimitDao(),
            genericService().create(BudgetService::class.java)
    )

    private val transactionDao = AppDatabase.getInstance(application).transactionDataDao()
//    private val transactionRepository = TransactionRepository(
//    )

    private lateinit var currencyCode: String
    private var currencySymbolLocationIsAtEnd = false
    val currencySymbol: MutableLiveData<String> = MutableLiveData()
    val networthValue: MutableLiveData<String> = MutableLiveData()
    val leftToSpendValue: MutableLiveData<String> = MutableLiveData()
    val balanceValue: MutableLiveData<String> = MutableLiveData()
    val earnedValue: MutableLiveData<String> = MutableLiveData()
    val spentValue: MutableLiveData<String> = MutableLiveData()
    val billsToPay: MutableLiveData<String> = MutableLiveData()
    val billsPaid: MutableLiveData<String> = MutableLiveData()
    val leftToSpendDay: MutableLiveData<String> = MutableLiveData()
    val currentMonthSpentValue: MutableLiveData<String> = MutableLiveData()
    val currentMonthBudgetValue: MutableLiveData<String> = MutableLiveData()
    val budgetLeftPercentage: MutableLiveData<BigDecimal> = MutableLiveData()
    val budgetSpentPercentage: MutableLiveData<BigDecimal> = MutableLiveData()
    val currentMonthWithdrawalLiveData: MutableLiveData<BigDecimal> = MutableLiveData()
    val currentMonthDepositLiveData: MutableLiveData<BigDecimal> = MutableLiveData()
    val lastMonthWithdrawalLiveData: MutableLiveData<BigDecimal> = MutableLiveData()
    val lastMonthDepositLiveData: MutableLiveData<BigDecimal> = MutableLiveData()
    val twoMonthsAgoDepositLiveData: MutableLiveData<BigDecimal> = MutableLiveData()
    val twoMonthsAgoWithdrawalLiveData: MutableLiveData<BigDecimal> = MutableLiveData()
    val sixDayWithdrawalLiveData: MutableLiveData<Sixple<BigDecimal, BigDecimal,
            BigDecimal, BigDecimal, BigDecimal, BigDecimal>> = MutableLiveData()
    var currentMonthNetBigDecimal = 0.toBigDecimal()
        private set
    var lastMonthNetBigDecimal = 0.toBigDecimal()
        private set
    var twoMonthAgoNetBigDecimal = 0.toBigDecimal()
        private set
    var currentMonthNetString = ""
        private set
    var lastMonthNetString = ""
        private set
    var twoMonthAgoNetString = ""
        private set
    var sixDaysAverage: String = ""
        private set
    var thirtyDayAverage: String = ""
        private set
    var currentMonthWithdrawal: String = ""
        private set
    var currentMonthDeposit: String = ""
        private set
    var lastMonthWithdrawal: String = ""
        private set
    var lastMonthDeposit: String = ""
        private set
    var twoMonthsAgoDeposit: String = ""
        private set
    var twoMonthsAgoWithdrawal: String = ""
        private set


    private suspend fun getAllBudgetData(currencyCode: String, currencySymbol: String){
        val budgetSpent = budgetRepository.getAllBudget()
        if(currencySymbolLocationIsAtEnd){
            currentMonthSpentValue.postValue(budgetSpent.toString() + currencySymbol)
        } else {
            currentMonthSpentValue.postValue(currencySymbol + budgetSpent)
        }
        budgetRepository.getAllBudget()
        val budgeted = budgetRepository.retrieveConstraintBudgetWithCurrency(
                DateTimeUtil.getStartOfMonth(),
                DateTimeUtil.getEndOfMonth(), currencyCode)
        if(currencySymbolLocationIsAtEnd){
            currentMonthBudgetValue.postValue(budgeted.toString() + currencySymbol)
        } else {
            currentMonthBudgetValue.postValue(currencySymbol + budgeted)
        }
//

    }




}