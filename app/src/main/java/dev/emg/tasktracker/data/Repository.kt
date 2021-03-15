package dev.emg.tasktracker.data

import dev.emg.tasktracker.data.db.Database
import dev.emg.tasktracker.data.vo.TasksList
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val database: Database) {

  suspend fun addTasksListItem(item: TasksList) {
    database.tasksDao().insert(item)
  }

  suspend fun getAllTasks(): List<TasksList> {
    return database.tasksDao().getAllTasks()
  }

  suspend fun insertTaskInDbAndFetch(item: TasksList) = flow<List<TasksList>> {
    addTasksListItem(item)
    val result = getAllTasks()
    emit(result)
  }

}