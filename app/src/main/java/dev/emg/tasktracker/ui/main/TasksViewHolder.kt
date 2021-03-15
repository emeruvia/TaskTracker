package dev.emg.tasktracker.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.emg.tasktracker.data.vo.TasksList
import dev.emg.tasktracker.databinding.ItemTasksBinding

class TasksViewHolder(private val binding: ItemTasksBinding) : ViewHolder(binding.root) {

  fun bind(tasksList: TasksList) {
    binding.nameTv.text = tasksList.name
  }

  companion object {
    fun from(parent: ViewGroup): TasksViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
      val binding = ItemTasksBinding.inflate(layoutInflater)
      return TasksViewHolder(binding)
    }
  }
}