package com.dua.ynabapplication.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDao<T>{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: T)
}