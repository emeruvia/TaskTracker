package dev.emg.tasktracker.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.emg.tasktracker.data.vo.TasksList
import dev.emg.tasktracker.databinding.ItemTasksListBinding
import dev.emg.tasktracker.ui.goneView
import dev.emg.tasktracker.ui.main.TasksListAdapter.OnTasksListListener
import dev.emg.tasktracker.ui.showView

class TasksListViewHolder(private val binding: ItemTasksListBinding) : ViewHolder(binding.root) {

  fun bind(tasksList: TasksList, listener: OnTasksListListener) {
    binding.nameTv.text = tasksList.name
    if (tasksList.detail.isNotEmpty()) {
      binding.detailsTv.text = tasksList.detail
      binding.detailsTv.showView()
    } else {
      binding.detailsTv.goneView()
    }

    binding.root.setOnClickListener {
      listener.onTasksListWasClicked(tasksList)
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
    fun from(parent: ViewGroup): TasksListViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
      val binding = ItemTasksListBinding.inflate(layoutInflater)
      return TasksListViewHolder(binding)
    }
  }
}