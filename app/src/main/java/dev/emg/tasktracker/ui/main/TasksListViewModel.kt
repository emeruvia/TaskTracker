package dev.emg.tasktracker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.emg.tasktracker.data.repository.Repository
import dev.emg.tasktracker.data.vo.ItemUiState
import dev.emg.tasktracker.data.vo.TaskItem
import dev.emg.tasktracker.data.vo.TasksList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

  private val _tasksList = MutableStateFlow<ItemUiState<List<TasksList>>>(ItemUiState.Success(emptyList()))
  val tasksList: StateFlow<ItemUiState<List<TasksList>>> get() = _tasksList

  init {
    val testList = mutableListOf<TaskItem>()
    for(i in 1..10) {
      testList.add(TaskItem(name = "Task number #${i}"))
    }
    val fakeData = TasksList(
      id = 1,
      name = "Task 1",
      tasksList = testList
    )
    val fakeData2 = TasksList(
      id = 2,
      name = "Task 2",
      tasksList = testList
    )

    viewModelScope.launch(Dispatchers.IO) {
      _tasksList.value = ItemUiState.Loading
      try {
        repository.insertTasksListInDbAndFetchThem(fakeData)
        repository.insertTasksListInDbAndFetchThem(fakeData2)
        delay(3000) // Manual delay to show loading animation
        _tasksList.value = ItemUiState.Success(repository.getAllTasks())
      } catch (e: Exception) {
        _tasksList.value = ItemUiState.Error(e)
        Timber.e(e, "init -> ${e.message}")
      }
    }
  }

  fun addTask(item: TasksList) {
    viewModelScope.launch(Dispatchers.IO) {
      _tasksList.value = ItemUiState.Loading
      try {
        _tasksList.value =
          ItemUiState.Success(repository.insertTasksListInDbAndFetchThem(item))
      } catch (e: Exception) {
        _tasksList.value = ItemUiState.Error(e)
        Timber.e(e, "addTask() -> ${e.message}")
      }
    }
  }

  fun addTaskItem(id: Long, item: TaskItem) {
    viewModelScope.launch(Dispatchers.IO) {
      _tasksList.value = ItemUiState.Loading
      try {
        _tasksList.value =
          ItemUiState.Success(repository.addTaskItemToTaskListById(id, item))
      } catch (e: Exception) {
        _tasksList.value = ItemUiState.Error(e)
        Timber.e(e, "addTask() -> ${e.message}")
      }
    }
  }

  fun updateTasksList(item: TasksList) {
    viewModelScope.launch(Dispatchers.IO) {
      _tasksList.value = ItemUiState.Loading
      try {
        _tasksList.value = ItemUiState.Success(repository.updateTasksListItemInDbAndFetch(item))
      } catch (e: Exception) {
        _tasksList.value = ItemUiState.Error(e)
        Timber.e(e, "deleteList() -> ${e.message}")
      }
    }
  }

  fun deleteTasksList(item: TasksList) {
    viewModelScope.launch(Dispatchers.IO) {
      _tasksList.value = ItemUiState.Loading
      try {
        _tasksList.value = ItemUiState.Success(repository.deleteTasksListInDbAndFetch(item))
      } catch (e: Exception) {
        _tasksList.value = ItemUiState.Error(e)
        Timber.e(e, "deleteList() -> ${e.message}")
      }
    }
  }

  fun deleteTaskItemFromTaskListById(id: Long?, item: TaskItem) {
    viewModelScope.launch {
      _tasksList.value = ItemUiState.Loading
      try {
        id?.let {
          _tasksList.value = ItemUiState.Success(repository.deleteTaskItemFromTaskListById(id, item))
        } ?: throw NullPointerException("TasksList id can't be null")
      } catch (e: Exception) {
        _tasksList.value = ItemUiState.Error(e)
      }
    }
  }

}