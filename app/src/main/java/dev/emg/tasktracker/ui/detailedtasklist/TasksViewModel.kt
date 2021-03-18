package dev.emg.tasktracker.ui.detailedtasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ItemTouchUIUtil
import dev.emg.tasktracker.data.repository.Repository
import dev.emg.tasktracker.data.vo.ItemUiState
import dev.emg.tasktracker.data.vo.TaskItem
import dev.emg.tasktracker.data.vo.TasksList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.NullPointerException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

  private val _taskList = MutableStateFlow<ItemUiState<TasksList>>(ItemUiState.Loading)
  val taskList: StateFlow<ItemUiState<TasksList>> get() = _taskList

  fun getTasksListById(id: Long?) {
    viewModelScope.launch(Dispatchers.IO) {
      _taskList.value = ItemUiState.Loading
      try {
        id?.let {
          _taskList.value = ItemUiState.Success(repository.getTasksListById(id))
        } ?: throw NullPointerException("TasksList id can't be null")
      } catch (e: Exception) {
        _taskList.value = ItemUiState.Error(e)
      }
    }
  }

  fun addTaskItemToTaskListById(id: Long?, item: TaskItem) {
    viewModelScope.launch {
      _taskList.value = ItemUiState.Loading
      try {
        id?.let {
          _taskList.value = ItemUiState.Success(repository.addTaskItemToTaskListById(id, item))
        } ?: throw NullPointerException("TasksList id can't be null")
      } catch (e: Exception) {
        _taskList.value = ItemUiState.Error(e)
      }
    }
  }

  fun updateTaskItem(item: TaskItem) {

  }

  fun deleteTaskItem(item: TaskItem) {

  }

}