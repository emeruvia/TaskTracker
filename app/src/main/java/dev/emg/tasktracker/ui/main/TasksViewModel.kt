package dev.emg.tasktracker.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.emg.tasktracker.data.Repository
import dev.emg.tasktracker.data.vo.TasksList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

  private val _tasksList = MutableStateFlow<TasksListUiState>(TasksListUiState.Success(emptyList()))
  val tasksList: StateFlow<TasksListUiState> get() = _tasksList

  init {
    viewModelScope.launch(Dispatchers.IO) {
      _tasksList.value = TasksListUiState.Loading
      try {
        _tasksList.value = TasksListUiState.Success(repository.getAllTasks())
      } catch (e: Exception) {
        _tasksList.value = TasksListUiState.Error(e)
        Timber.e(e, "init -> ${e.message}")
      }
    }
  }

  fun addTask(item: TasksList) {
    viewModelScope.launch(Dispatchers.IO) {
      _tasksList.value = TasksListUiState.Loading
      try {
        _tasksList.value =
          TasksListUiState.Success(repository.insertTasksListInDbAndFetchThem(item))
      } catch (e: Exception) {
        _tasksList.value = TasksListUiState.Error(e)
        Timber.e(e, "addTask() -> ${e.message}")
      }
    }
  }

  fun deleteTasksList(item: TasksList) {
    viewModelScope.launch {
      _tasksList.value = TasksListUiState.Loading
      try {
        _tasksList.value = TasksListUiState.Success(repository.deleteTasksListInDbAndFetch(item))
      } catch (e: Exception) {
        _tasksList.value = TasksListUiState.Error(e)
        Timber.e(e, "deleteList() -> ${e.message}")
      }
    }
  }
}

sealed class TasksListUiState {
  data class Success(val data: List<TasksList>) : TasksListUiState()
  object Loading : TasksListUiState()
  data class Error(
    val exception: Throwable,
    val msg: String? = exception.message
  ) : TasksListUiState()
}