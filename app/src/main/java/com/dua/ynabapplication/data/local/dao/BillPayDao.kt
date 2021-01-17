package com.dua.ynabapplication.data.local.dao


//@Dao
//abstract class BillPayDao: BaseDao<BillPayDates> {
//
//    @Query("DELETE FROM billPayList")
//    abstract suspend fun deleteAllPayList(): Int
//
//    @Query("SELECT * FROM billPayList WHERE id =:billId AND strftime('%s', payDates) BETWEEN strftime('%s', :startDate) AND strftime('%s', :endDate)")
//    abstract suspend fun getBillByDateAndId(billId: Long, startDate: String, endDate: String): List<BillPayDates>
//
//}
