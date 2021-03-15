package dev.emg.tasktracker.ui.addtask

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dev.emg.tasktracker.R
import dev.emg.tasktracker.data.vo.TasksList
import dev.emg.tasktracker.databinding.FragmentAddTaskBinding

class AddTaskDialogFragment : BottomSheetDialogFragment() {

  private lateinit var binding: FragmentAddTaskBinding
  private var listener: OnTasksListAdded? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentAddTaskBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val task = TasksList()
    binding.tasksNameEt.doAfterTextChanged {
      task.name = binding.tasksNameEt.text.toString()
    }
    binding.tasksDetailEt.doAfterTextChanged {
      task.detail = binding.tasksDetailEt.text.toString()
    }

    binding.save.setOnClickListener {
      if (task.name.isNotEmpty()) {
        listener?.tasksListAdded(task)
        dismiss()
      }
    }
  }

  interface OnTasksListAdded {
    fun tasksListAdded(item: TasksList)
  }

  companion object {
    private const val TAG = "addTask"

    fun show(fragmentManager: FragmentManager, listener: OnTasksListAdded) {
      val fragment = AddTaskDialogFragment()
      fragment.listener = listener
      fragment.show(fragmentManager, TAG)
    }
  }
}