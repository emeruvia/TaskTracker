package dev.emg.tasktracker.ui.detailedtasklist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import dev.emg.tasktracker.App
import dev.emg.tasktracker.data.vo.ItemUiState
import dev.emg.tasktracker.data.vo.TaskItem
import dev.emg.tasktracker.data.vo.TasksList
import dev.emg.tasktracker.databinding.FragmentDetailedTasksListBinding
import dev.emg.tasktracker.ui.SwipeToDeleteCallback
import dev.emg.tasktracker.ui.detailedtasklist.TasksAdapter.OnTaskListener
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class DetailedTasksListFragment : Fragment(), OnTaskListener {

  private var _binding: FragmentDetailedTasksListBinding? = null
  private val binding: FragmentDetailedTasksListBinding get() = _binding!!

  private var tasksListId: Long? = null
  private lateinit var taskAdapter: TasksAdapter

  @Inject lateinit var viewModel: TasksViewModel

  override fun onAttach(context: Context) {
    super.onAttach(context)
    (requireActivity().application as App).appComponent.inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentDetailedTasksListBinding.inflate(
      layoutInflater,
      container,
      false
    )
    tasksListId = arguments?.get(ARGS_TASKSLIST) as Long

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.getTasksListById(tasksListId)

    taskAdapter = TasksAdapter(this)

    binding.recyclerview.apply {
      val itemTouchHelper = ItemTouchHelper(
        SwipeToDeleteCallback(
          context = requireContext(),
          taskAdapter = taskAdapter
        )
      )

      this.adapter = taskAdapter
      itemTouchHelper.attachToRecyclerView(this)
    }

    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
      viewModel.taskList.collect {
        when (it) {
          is ItemUiState.Success -> {
            binding.taskTitle.text = it.data.name
            taskAdapter.submitList(it.data.tasksList)
            binding.recyclerview.post {
              if (!binding.recyclerview.isComputingLayout) {
                taskAdapter.notifyDataSetChanged()
              }
            }

          }
          is ItemUiState.Loading -> {
            // TODO Add loading state
          }
          is ItemUiState.Error -> {
            // TODO Add error state
          }
        }
      }
    }
  }

  override fun onAddTask(item: TaskItem) {
    viewModel.addTaskItemToTaskListById(tasksListId, item)
  }

  override fun onDeleteTask(item: TaskItem) {
    viewModel.deleteTaskItemFromTaskListById(tasksListId, item)
  }

  override fun onUpdateTask(item: TaskItem) {

  }

  companion object {
    private const val ARGS_TASKSLIST = "args_task_lists_detailed"

    const val TAG = "detailedTaskList"

    fun create(taskListId: Long): DetailedTasksListFragment {
      val fragment = DetailedTasksListFragment()
      val bundle = Bundle()
      bundle.putLong(ARGS_TASKSLIST, taskListId)
      fragment.arguments = bundle
      return fragment
    }
  }
}