package dev.emg.tasktracker.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dev.emg.tasktracker.data.vo.TasksList

class TasksAdapter : ListAdapter<TasksList, TasksViewHolder>(DIFF_UTIL) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
    return TasksViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
    holder.bind(getItem(position))
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