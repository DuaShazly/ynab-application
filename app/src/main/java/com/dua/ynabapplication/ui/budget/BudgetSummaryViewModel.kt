package com.dua.ynabapplication.ui.budget

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.dua.ynabapplication.data.local.dao.AppDatabase
import com.dua.ynabapplication.data.remote.api.BudgetService
import com.dua.ynabapplication.repository.BaseViewModel
import com.dua.ynabapplication.repository.models.budget.BudgetRepository
import com.dua.ynabapplication.repository.models.transaction.SplitSeparator
import com.dua.ynabapplication.utils.DateTimeUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import java.math.BigDecimal
import kotlin.math.abs

class BudgetSummaryViewModel(application: Application): BaseViewModel(application) {



    private val transactionDataDao = AppDatabase.getInstance(application).transactionDataDao()

    private val spentDao = AppDatabase.getInstance(application).spentDataDao()
    private val budgetLimitDao = AppDatabase.getInstance(application).budgetLimitDao()
    private val budgetDao = AppDatabase.getInstance(application).budgetDataDao()
    private val budgetListDao = AppDatabase.getInstance(application).budgetListDataDao()
    private val budgetService = genericService().create(BudgetService::class.java)

    private val budgetRepository = BudgetRepository(budgetDao, budgetListDao, spentDao, budgetLimitDao, budgetService)
    private lateinit var startOfMonth: String
    private lateinit var endOfMonth: String

    private var defaultCurrency = ""
    private var originalBudget: BigDecimal = 0.toBigDecimal()
    var originalRemainderString = ""
    var originalBudgetString = ""
    var originalSpentString = ""

    private var sumOfWithdrawal: BigDecimal = 0.toBigDecimal()
    private val modifiedList = arrayListOf<String>()
    var currencySymbol = ""
    val totalTransaction: MutableLiveData<String> = MutableLiveData()
    val availableBudget: MutableLiveData<String> = MutableLiveData()
    val balanceBudget: MutableLiveData<String> = MutableLiveData()
    val uniqueBudgets: MutableLiveData<List<String>> = MutableLiveData()
    val pieChartData: MutableLiveData<List<Triple<Float, String, BigDecimal>>> = MutableLiveData()

    var monthCount: Long = 0

    fun getCurrency(): LiveData<List<String>> {
        val data: MutableLiveData<List<String>> = MutableLiveData()
        viewModelScope.launch(Dispatchers.IO) {

        }
        return data
    }

    private suspend fun getTransaction(){}

    fun getTransactionList(budget: String?): LiveData<PagingData<SplitSeparator>>? {

        return null;
    }

    fun changeCurrency(position: Int){
        val regex = "\\([^()]*\\)".toRegex()
        val regexReplaced = regex.find(modifiedList[position])
        val replacedCurrency = modifiedList[position].replace(regexReplaced?.value ?: "", "").trim()
        viewModelScope.launch(Dispatchers.IO){

        }
    }

    fun getBalance(budget: String){
        viewModelScope.launch(Dispatchers.IO){
            if (budget.isNotEmpty()){
//                val transactionBudget = getBudget(startOfMonth, endOfMonth,
//                        defaultCurrency, budget)
//                totalTransaction.postValue("$currencySymbol $transactionBudget")
//                balanceBudget.postValue("$currencySymbol ${originalBudget.minus(transactionBudget)}")
            }
        }
    }

    fun setDisplayDate(): LiveData<String>{
        val data: MutableLiveData<String> = MutableLiveData()
        when {
            monthCount == 0L -> {
                // 0 -> current month
                data.postValue(DateTimeUtil.getMonthAndYear(DateTimeUtil.getTodayDate()))
                startOfMonth = DateTimeUtil.getStartOfMonth()
                endOfMonth = DateTimeUtil.getEndOfMonth()
            }
            monthCount < 1L -> {

                data.postValue(DateTimeUtil.getMonthAndYear(DateTimeUtil.getStartOfMonth(abs(monthCount))))
                startOfMonth = DateTimeUtil.getStartOfMonth(abs(monthCount))
                endOfMonth = DateTimeUtil.getEndOfMonth(abs(monthCount))
            }
            else -> {

                data.postValue(DateTimeUtil.getMonthAndYear(DateTimeUtil.getFutureStartOfMonth(monthCount)))
                startOfMonth = DateTimeUtil.getFutureStartOfMonth(monthCount)
                endOfMonth = DateTimeUtil.getFutureEndOfMonth(monthCount)
            }
        }
        viewModelScope.launch(Dispatchers.IO){
            getTransaction()
        }
        return data
    }
}