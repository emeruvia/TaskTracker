package dev.emg.tasktracker.data

import dev.emg.tasktracker.data.db.Database
import dev.emg.tasktracker.data.vo.Tasks
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val database: Database) {

  suspend fun addTasksListItem(item: Tasks) {
    database.tasksDao().insert(item)
  }

  suspend fun insertTaskInDbAndFetch(item: Tasks) = flow<List<Tasks>> {
    addTasksListItem(item)
    val result = database.tasksDao().getAllTasks()
    emit(result)
  }

}