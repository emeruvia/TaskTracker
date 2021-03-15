package dev.emg.tasktracker.data

import dev.emg.tasktracker.data.db.Database
import dev.emg.tasktracker.data.vo.TasksList
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val database: Database) {

  suspend fun updateTasksList(item: TasksList) {
    database.tasksDao().update(item)
  }

  suspend fun getAllTasks(): List<TasksList> {
    return database.tasksDao().getAllTasks()
  }

  suspend fun insertTasksListInDbAndFetchThem(item: TasksList): List<TasksList> {
    addTasksListItem(item)
    return getAllTasks()
  }

  suspend fun deleteTasksListInDbAndFetch(item: TasksList): List<TasksList> {
    deleteTasksList(item)
    return getAllTasks()
  }

  private suspend fun addTasksListItem(item: TasksList) {
    database.tasksDao().insert(item)
  }

  private suspend fun deleteTasksList(item: TasksList) {
    database.tasksDao().delete(item)
  }
}