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

  suspend fun insertTasksListInDbAndFetchThem(item: TasksList) = flow<List<TasksList>> {
    addTasksListItem(item)
    emit(getAllTasks())
  }

  suspend fun deleteTasksListInDbAndFetch(item: TasksList) = flow<List<TasksList>> {
    deleteTasksList(item)
    emit(getAllTasks())
  }

  private suspend fun addTasksListItem(item: TasksList) {
    database.tasksDao().insert(item)
  }

  private suspend fun deleteTasksList(item: TasksList) {
    database.tasksDao().delete(item)
  }
}