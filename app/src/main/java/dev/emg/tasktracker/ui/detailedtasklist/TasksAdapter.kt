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

  override fun onViewRecycled(holder: ViewHolder) {
    super.onViewRecycled(holder)
    when (holder) {
      is TaskViewHolder -> holder.unbind()
      is AddTaskViewHolder -> holder.unbind()
    }
  }

  override fun getItem(position: Int): TaskItem {
    return super.getItem(calculatePosition(position))
  }

  override fun getItemCount(): Int {
    return when (val count = super.getItemCount()) {
      0 -> 1
      else -> count + 1
    }
  }

  override fun getItemViewType(position: Int): Int {
    val lastItemInList = currentList.size + 1
    return when (position) {
      0 -> VIEW_ADD_TASK
      lastItemInList -> VIEW_ADD_TASK
      else -> VIEW_TASK
    }
  }

  private fun calculatePosition(position: Int): Int {
    return if (position != 0) {
      position - 1
    } else 0
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