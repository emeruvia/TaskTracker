package dev.emg.tasktracker.data.repository

import dev.emg.tasktracker.data.db.Database
import dev.emg.tasktracker.data.vo.TasksList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksRepository @Inject constructor(private val database: Database) : Repository {

  override suspend fun updateTasksList(item: TasksList) {
    database.tasksListDao().update(item)
  }

  override suspend fun getAllTasks(): List<TasksList> {
    return database.tasksListDao().getAllTasks()
  }

  override suspend fun insertTasksListInDbAndFetchThem(item: TasksList): List<TasksList> {
    addTasksListItem(item)
    return getAllTasks()
  }

  override suspend fun deleteTasksListInDbAndFetch(item: TasksList): List<TasksList> {
    deleteTasksList(item)
    return getAllTasks()
  }

  override suspend fun updateTasksListItemInDbAndFetch(item: TasksList): List<TasksList> {
    updateTasksList(item)
    return getAllTasks()
  }

  private suspend fun addTasksListItem(item: TasksList) {
    database.tasksListDao().insert(item)
  }

  private suspend fun deleteTasksList(item: TasksList) {
    database.tasksListDao().delete(item)
  }
}