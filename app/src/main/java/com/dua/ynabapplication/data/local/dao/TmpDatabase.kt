package com.dua.ynabapplication.data.local.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor


//@Database(entities = [Transactions::class, TransactionIndex::class, AccountData::class],
//        version = 1, exportSchema = false)
//@TypeConverters(TypeConverterUtil::class)
//abstract class TmpDatabase: RoomDatabase() {
//
//    abstract fun transactionDataDao(): TransactionDataDao
//
//    companion object {
//        @Volatile private var INSTANCE: TmpDatabase? = null
//
//        fun getInstance(context: Context): TmpDatabase {
//            return INSTANCE ?: synchronized(this){
//                INSTANCE ?: Room.databaseBuilder(context,
//                        TmpDatabase::class.java,"temp-"  + Constants.DB_NAME)
//                        .setQueryExecutor(Dispatchers.IO.asExecutor())
//                        .build().also { INSTANCE = it }
//            }
//        }
//    }
//
//}