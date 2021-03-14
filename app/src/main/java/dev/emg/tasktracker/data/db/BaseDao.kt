package dev.emg.tasktracker.data.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(obj: T)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(obj: List<T>)

  @Update
  suspend fun update(obj: T)

  @Delete
  suspend fun delete(obj: T)

}