package dev.emg.tasktracker.data.repository

import dev.emg.tasktracker.data.db.Database
import dev.emg.tasktracker.data.vo.TasksList
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
interface Repository {

  suspend fun updateTasksList(item: TasksList)

  suspend fun getAllTasks(): List<TasksList>

  suspend fun insertTasksListInDbAndFetchThem(item: TasksList): List<TasksList>

  suspend fun deleteTasksListInDbAndFetch(item: TasksList): List<TasksList>

  suspend fun updateTasksListItemInDbAndFetch(item: TasksList): List<TasksList>

}