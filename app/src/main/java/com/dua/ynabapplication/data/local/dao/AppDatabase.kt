package com.dua.ynabapplication.data.local.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dua.ynabapplication.repository.models.account.AccountData
import com.dua.ynabapplication.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor



//@Database(entities = [PiggyData::class, PiggyFts::class, BillData::class, AccountData::class, CurrencyData::class,
//    Transactions::class, TransactionIndex::class, CategoryData::class, CategoryFts::class, BudgetData::class,
//    BudgetListData::class, BudgetListFts::class, TagsData::class, AttachmentData::class,
//    Spent::class, BudgetLimitData::class, BillPaidDates::class, BillPayDates::class],
//        version = 24, exportSchema = false)
@Database(entities = [AccountData::class],version = 1)
//@TypeConverters(TypeConverterUtil::class)
abstract class AppDatabase: RoomDatabase() {
//
//    abstract fun billDataDao(): BillDataDao
//    abstract fun billPaidDao(): BillPaidDao
//    abstract fun billPayDao(): BillPayDao
//    abstract fun piggyDataDao(): PiggyDataDao
    abstract fun accountDataDao(): AccountsDataDao
    abstract fun currencyDataDao(): CurrencyDataDao
    abstract fun transactionDataDao(): TransactionDataDao
//    abstract fun categoryDataDao(): CategoryDataDao
    abstract fun budgetDataDao(): BudgetDataDao
    abstract fun budgetListDataDao(): BudgetListDataDao
//    abstract fun tagsDataDao(): TagsDataDao
//    abstract fun attachmentDataDao(): AttachmentDataDao
    abstract fun spentDataDao(): SpentDataDao
    abstract fun budgetLimitDao(): BudgetLimitDao
//
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(context,
                        AppDatabase::class.java, Constants.DB_NAME)
                        .setQueryExecutor(Dispatchers.IO.asExecutor())
                        .fallbackToDestructiveMigration()
                        .build().also { INSTANCE = it }
            }
        }
//
        fun clearDb(context: Context) = getInstance(context).clearAllTables()
    }
}