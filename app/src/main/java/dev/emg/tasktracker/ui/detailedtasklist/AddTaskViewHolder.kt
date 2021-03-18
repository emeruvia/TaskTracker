package dev.emg.tasktracker.ui.detailedtasklist

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.emg.tasktracker.data.vo.TaskItem
import dev.emg.tasktracker.databinding.ItemTaskAddBinding
import dev.emg.tasktracker.ui.detailedtasklist.TasksAdapter.OnTaskListener

class AddTaskViewHolder(private val binding: ItemTaskAddBinding) : ViewHolder(binding.root) {

  fun bind(listener: OnTaskListener) {
    binding.root.setOnClickListener {
      binding.root.requestFocus()
    }

    binding.taskName.setOnEditorActionListener { textView, actionId, keyEvent ->
      if (actionId == EditorInfo.IME_ACTION_DONE
        || actionId == EditorInfo.IME_ACTION_SEARCH
        || keyEvent != null
        && keyEvent.action == KeyEvent.ACTION_DOWN
        && keyEvent.keyCode == KeyEvent.KEYCODE_ENTER
      ) {
        if (keyEvent == null || !keyEvent.isShiftPressed) {

          val newTask = TaskItem(name = binding.taskName.text.toString())
          listener.onAddTask(newTask)

          return@setOnEditorActionListener true
        }
      }
      return@setOnEditorActionListener false
    }
  }

  companion object {
    fun from(parent: ViewGroup): AddTaskViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
      val binding = ItemTaskAddBinding.inflate(layoutInflater)
      return AddTaskViewHolder(binding)
    }
  }
}