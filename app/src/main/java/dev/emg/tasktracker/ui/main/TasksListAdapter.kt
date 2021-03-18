package dev.emg.tasktracker.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dev.emg.tasktracker.data.vo.TasksList

class TasksListAdapter(
  private val listener: OnTasksListListener
) : ListAdapter<TasksList, TasksListViewHolder>(DIFF_UTIL) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksListViewHolder {
    return TasksListViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: TasksListViewHolder, position: Int) {
    holder.bind(getItem(position), listener)
  }

  override fun onViewRecycled(holder: TasksListViewHolder) {
    super.onViewRecycled(holder)
    holder.unbind()
  }

  fun deleteItemAtPosition(position: Int) {
    val tasksListItem = getItem(position)
    listener.onTasksListDeleted(tasksListItem)
  }

  interface OnTasksListListener {
    fun onTasksListWasClicked(item: TasksList)
    fun onTasksListWasCompleted(item: TasksList)
    fun onTasksListDeleted(item: TasksList)
  }

  companion object {
    private val DIFF_UTIL = object : DiffUtil.ItemCallback<TasksList>() {
      override fun areItemsTheSame(oldItem: TasksList, newItem: TasksList): Boolean {
        return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(oldItem: TasksList, newItem: TasksList): Boolean {
        return oldItem.id == newItem.id
      }
    }
  }
}