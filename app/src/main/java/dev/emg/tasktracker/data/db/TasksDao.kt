package dev.emg.tasktracker.data.db

import androidx.room.Dao
import androidx.room.Query
import dev.emg.tasktracker.data.vo.Tasks

@Dao
interface TasksDao : BaseDao<Tasks> {

  @Query("SELECT * FROM tasks_table")
  suspend fun getAllTasks(): List<Tasks>

}