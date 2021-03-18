package dev.emg.tasktracker.ui.detailedtasklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.emg.tasktracker.data.vo.TaskItem
import dev.emg.tasktracker.databinding.ItemTaskBinding

class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {

  fun bind(task: TaskItem) {
    binding.taskName.text = task.name
  }

  fun unbind() {
    binding.taskName.text = ""
  }

  companion object {
    fun from(parent: ViewGroup): TaskViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
      val binding = ItemTaskBinding.inflate(layoutInflater)
      return TaskViewHolder(binding)
    }
  }
}