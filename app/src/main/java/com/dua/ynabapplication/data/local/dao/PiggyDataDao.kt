package com.dua.ynabapplication.data.local.dao


//@Dao
//abstract class PiggyDataDao: BaseDao<PiggyData> {
//
//    @Query("SELECT * FROM piggy")
//    abstract suspend fun getAllPiggy(): List<PiggyData>
//
//    @Query("SELECT COUNT(*) FROM piggy")
//    abstract suspend fun getAllPiggyCount(): Int
//
//    @Query("DELETE FROM piggy WHERE piggyId = :piggyId")
//    abstract fun deletePiggyById(piggyId: Long): Int
//
//    @Query("DELETE FROM piggy")
//    abstract suspend fun deleteAllPiggyBank(): Int
//
//    @Query("SELECT DISTINCT piggy.name FROM piggy JOIN piggyFts ON (piggy.piggyId = piggyFts.piggyId)")
//    abstract fun getAllPiggyName(): Flow<List<String>>
//
//    @Query("SELECT * FROM piggy WHERE piggyId = :piggyId")
//    abstract fun getPiggyFromId(piggyId: Long): PiggyData
//
//}