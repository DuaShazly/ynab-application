package com.dua.ynabapplication.data.local.dao


//@Dao
//abstract class CategoryDataDao: BaseDao<CategoryData> {
//
//    @Query("DELETE FROM category WHERE categoryId = :categoryId")
//    abstract fun deleteCategoryById(categoryId: Long): Int
//
//    @Query("DELETE FROM category")
//    abstract suspend fun deleteAllCategory(): Int
//
//    @Query("SELECT * FROM category order by categoryId desc limit :limitNumber")
//    abstract fun getPaginatedCategory(limitNumber: Int): Flow<MutableList<CategoryData>>
//
//    @Query("SELECT * FROM category order by categoryId")
//    abstract suspend fun getCategory(): List<CategoryData>
//
//    @Query("SELECT COUNT(*) FROM category")
//    abstract suspend fun getCategoryCount(): Int
//
//    @Query("SELECT * FROM category JOIN categoryFts ON category.categoryId == categoryFts.categoryId WHERE categoryFts MATCH :categoryName GROUP BY categoryFts.categoryId")
//    abstract suspend fun searchCategory(categoryName: String): List<CategoryData>
//
//    @Query("SELECT * FROM category WHERE categoryId =:categoryId")
//    abstract fun getCategoryById(categoryId: Long): CategoryData
//}