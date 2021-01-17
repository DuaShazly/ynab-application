package com.dua.ynabapplication.data.local.dao

//@Dao
//abstract class TagsDataDao: BaseDao<TagsData> {
//
//    @Query("SELECT * FROM tags")
//    abstract fun getAllTags(): MutableList<TagsData>
//
//    @Query("DELETE FROM tags WHERE tag = :tagName")
//    abstract fun deleteTagByName(tagName: String): Int
//
//    @Query("SELECT * FROM tags WHERE tagsId = :tagId")
//    abstract fun getTagById(tagId: Long): TagsData
//
//    @Query("SELECT * FROM tags WHERE tag = :nameOfTag")
//    abstract fun getTagByName(nameOfTag: String): TagsData
//
//    @Query("SELECT description FROM tags WHERE description LIKE :name")
//    abstract fun searchTagByName(name: String): List<String>
//
//    @Query("DELETE FROM tags")
//    abstract fun deleteTags(): Int
//
//}