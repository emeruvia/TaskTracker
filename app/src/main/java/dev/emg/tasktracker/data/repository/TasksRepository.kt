package dev.emg.tasktracker.data.repository

import dev.emg.tasktracker.data.db.Database
import dev.emg.tasktracker.data.vo.TasksList
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksRepository @Inject constructor(private val database: Database) : Repository {

  override suspend fun updateTasksList(item: TasksList) {
    database.tasksDao().update(item)
  }

  override suspend fun getAllTasks(): List<TasksList> {
    return database.tasksDao().getAllTasks()
  }

  override suspend fun insertTasksListInDbAndFetchThem(item: TasksList): List<TasksList> {
    addTasksListItem(item)
    return getAllTasks()
  }

  override suspend fun deleteTasksListInDbAndFetch(item: TasksList): List<TasksList> {
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