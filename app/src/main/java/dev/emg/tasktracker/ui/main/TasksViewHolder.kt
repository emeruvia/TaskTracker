package dev.emg.tasktracker.ui.main

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.emg.tasktracker.data.vo.TasksList
import dev.emg.tasktracker.databinding.ItemTasksBinding
import dev.emg.tasktracker.ui.goneView
import dev.emg.tasktracker.ui.main.TasksAdapter.OnTasksListListener
import dev.emg.tasktracker.ui.showView

class TasksViewHolder(private val binding: ItemTasksBinding) : ViewHolder(binding.root) {

  fun bind(tasksList: TasksList, listener: OnTasksListListener) {
    binding.nameTv.text = tasksList.name
    if (tasksList.detail.isNotEmpty()) {
      binding.detailsTv.text = tasksList.detail
      binding.detailsTv.showView()
    } else {
      binding.detailsTv.goneView()
    }

//    when (tasksList.wasCompleted) {
//      true -> {
//        binding.taskCheck.isChecked = true
//      }
//      false -> {
//        binding.taskCheck.isChecked = false
//      }
//    }
//
//    binding.taskCheck.setOnCheckedChangeListener { compoundButton, isChecked ->
//      tasksList.wasCompleted = isChecked
//      listener.onTasksListWasCompleted(tasksList)
//    }
  }

  fun unbind() {
    binding.detailsTv.goneView()
  }

  companion object {
    fun from(parent: ViewGroup): TasksViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
      val binding = ItemTasksBinding.inflate(layoutInflater)
      return TasksViewHolder(binding)
    }
  }
}