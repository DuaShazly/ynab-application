package com.dua.ynabapplication.data.local.dao

//@Dao
//abstract class BillDataDao: BaseDao<BillData> {
//
//    @Query("SELECT * FROM bills")
//    abstract suspend fun getBill(): List<BillData>
//
//    @Query("SELECT COUNT(*) FROM bills")
//    abstract suspend fun getBillCount(): Long
//
//    @Query("DELETE FROM bills WHERE billId = :billId")
//    abstract fun deleteBillById(billId: Long): Int
//
//    @Query("SELECT * FROM bills WHERE billId = :billId")
//    abstract fun getBillById(billId: Long): BillData
//
//    @Query("DELETE FROM bills")
//    abstract suspend fun deleteAllBills(): Int
//
//    @Query("SELECT DISTINCT(name) FROM bills")
//    abstract fun getAllBillName(): Flow<List<String>>
//}
