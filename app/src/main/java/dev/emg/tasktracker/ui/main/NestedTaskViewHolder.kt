package dev.emg.tasktracker.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.emg.tasktracker.data.vo.TaskItem
import dev.emg.tasktracker.data.vo.TasksList
import dev.emg.tasktracker.databinding.FragmentDetailedTasksListBinding
import dev.emg.tasktracker.ui.SwipeToDeleteCallback
import dev.emg.tasktracker.ui.detailedtasklist.TasksAdapter
import dev.emg.tasktracker.ui.detailedtasklist.TasksAdapter.OnTaskListener

class NestedTaskViewHolder(
  private val binding: FragmentDetailedTasksListBinding
) : ViewHolder(binding.root) {


  fun bind(task: TasksList, listener: OnTaskListener) {
    binding.taskTitle.text = task.name
    val taskAdapter = TasksAdapter(task.id, listener)
    binding.recyclerview.apply {
      val itemTouchHelper = ItemTouchHelper(
        SwipeToDeleteCallback(
          context = binding.root.context,
          taskAdapter = taskAdapter
        )
      )

      this.adapter = taskAdapter
      itemTouchHelper.attachToRecyclerView(this)
    }

    taskAdapter.submitList(task.tasksList)
  }

  fun unbind() {

  }

  companion object {
    fun from(parent: ViewGroup): NestedTaskViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
      val binding = FragmentDetailedTasksListBinding.inflate(layoutInflater, parent, false)
      return NestedTaskViewHolder(binding)
    }
  }
}