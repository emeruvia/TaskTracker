package dev.emg.tasktracker.ui.detailedtasklist

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.emg.tasktracker.data.vo.TaskItem
import java.lang.Exception

class TasksAdapter(
  private val id: Long,
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
    if (position < currentList.size) {
      return super.getItem(position)
    } else {
      throw ArrayIndexOutOfBoundsException("Position exceeds the size of the list")
    }
  }

  override fun getItemCount(): Int {
    return when (val count = super.getItemCount()) {
      0 -> 1
      else -> count + 1
    }
  }

  override fun getItemViewType(position: Int): Int {
    val lastItemInList = currentList.size
    return when {
      currentList.isEmpty() -> VIEW_ADD_TASK
      position == lastItemInList -> VIEW_ADD_TASK
      else -> VIEW_TASK
    }
  }

  fun deleteItemAtPosition(position: Int) {
    listener.onDeleteTask(id, getItem(position))
  }

  interface OnTaskListener {
    fun onAddTask(id: Long, item: TaskItem)
    fun onDeleteTask(id: Long, item: TaskItem)
    fun onUpdateTask(item: TaskItem)
  }

  companion object {
    private const val VIEW_TASK = 0
    private const val VIEW_ADD_TASK = 1

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