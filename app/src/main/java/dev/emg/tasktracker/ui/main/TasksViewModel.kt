package dev.emg.tasktracker.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.emg.tasktracker.data.Repository
import dev.emg.tasktracker.data.vo.TasksList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

  private val _tasksList = MutableLiveData<List<TasksList>>()
  val tasksList: LiveData<List<TasksList>> get() = _tasksList

  init {
    viewModelScope.launch(Dispatchers.IO) {
      try {
        _tasksList.postValue(repository.getAllTasks())
      } catch (e: Exception) {
        Timber.e(e, "init -> ${e.message}")
      }
    }
  }

  fun addTask(item: TasksList) {
    viewModelScope.launch(Dispatchers.IO) {
      try {
        val result = repository.insertTaskInDbAndFetch(item)
        result.collect {
          _tasksList.postValue(it)
        }
      } catch (e: Exception) {
        Timber.e(e, "addTask() -> ${e.message}")
      }
    }
  }

}