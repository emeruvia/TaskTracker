package dev.emg.tasktracker.data.db

import androidx.room.Dao
import androidx.room.Query
import dev.emg.tasktracker.data.vo.TasksList

@Dao
interface TasksListDao : BaseDao<TasksList> {

  @Query("SELECT * FROM tasks_table WHERE id = :id")
  suspend fun getTaskListById(id: Long): TasksList

  @Query("SELECT * FROM tasks_table")
  suspend fun getAllTasks(): List<TasksList>

}