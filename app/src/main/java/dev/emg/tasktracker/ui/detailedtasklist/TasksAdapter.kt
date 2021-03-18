package dev.emg.tasktracker.ui.detailedtasklist

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.emg.tasktracker.data.vo.TaskItem
import java.lang.Exception

class TasksAdapter(
  private val listener: OnTaskListener
) : ListAdapter<TaskItem, ViewHolder>(DIFF_UTIL) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return when (viewType) {
      VIEW_TASK -> TaskViewHolder.from(parent)
      VIEW_ADD_TASK -> AddTaskViewHolder.from(parent)
      else -> throw Exception("Invalid ViewType for viewHolder")
    }
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    when (holder) {
      is TaskViewHolder -> holder.bind(getItem(position))
      is AddTaskViewHolder -> holder.bind(listener)
    }
  }

  override fun getItemCount(): Int {
    return when (super.getItemCount()) {
      0 -> 1
      else -> super.getItemCount() + 1
    }
  }

  override fun getItemViewType(position: Int): Int {
    return when (itemCount) {
      1 -> VIEW_ADD_TASK
      itemCount -> VIEW_ADD_TASK
      else -> VIEW_TASK
    }
  }

  interface OnTaskListener {
    fun onAddTask(item: TaskItem)
    fun onDeleteTask(item: TaskItem)
    fun onUpdateTask(item: TaskItem)
  }

  companion object {
    private val VIEW_TASK = 0
    private val VIEW_ADD_TASK = 1

    private val DIFF_UTIL = object : DiffUtil.ItemCallback<TaskItem>() {
      override fun areContentsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
        return oldItem.id == newItem.id
      }

      override fun areItemsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
        return oldItem.id == newItem.id
      }
    }
  }
}