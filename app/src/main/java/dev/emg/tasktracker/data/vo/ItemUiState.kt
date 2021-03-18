package dev.emg.tasktracker.data.vo

sealed class ItemUiState<out T> {
  data class Success<T>(val data: T) : ItemUiState<T>()
  object Loading : ItemUiState<Nothing>()
  data class Error(
    val exception: Throwable,
    val msg: String? = exception.message
  ) : ItemUiState<Nothing>()
}