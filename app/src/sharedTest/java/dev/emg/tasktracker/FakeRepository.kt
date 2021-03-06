package dev.emg.tasktracker.di

import dev.emg.tasktracker.data.repository.Repository
import dev.emg.tasktracker.data.vo.TaskItem
import dev.emg.tasktracker.data.vo.TasksList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeRepository @Inject constructor() : Repository {
  override suspend fun updateTasksList(item: TasksList) {
    TODO("Not yet implemented")
  }

  override suspend fun getAllTasks(): List<TasksList> {
    TODO("Not yet implemented")
  }

  override suspend fun insertTasksListInDbAndFetchThem(item: TasksList): List<TasksList> {
    TODO("Not yet implemented")
  }

  override suspend fun deleteTasksListInDbAndFetch(item: TasksList): List<TasksList> {
    TODO("Not yet implemented")
  }

  override suspend fun getTasksListById(id: Long): TasksList {
    TODO("Not yet implemented")
  }

  override suspend fun updateTasksListItemInDbAndFetch(item: TasksList): List<TasksList> {
    TODO("Not yet implemented")
  }

  override suspend fun addTaskItemToTaskListById(id: Long, item: TaskItem): TasksList {
    TODO("Not yet implemented")
  }

  override suspend fun deleteTaskItemFromTaskListById(id: Long, item: TaskItem): TasksList {
    TODO("Not yet implemented")
  }
}