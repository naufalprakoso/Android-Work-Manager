package com.naufalprakoso.researchworkmanager.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.naufalprakoso.researchworkmanager.database.entity.HeroEntity

@Dao
interface HeroDao {
    @Query("SELECT * FROM superhero")
    fun getHeroes(): LiveData<List<HeroEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHeroes(data: List<HeroEntity>)
}