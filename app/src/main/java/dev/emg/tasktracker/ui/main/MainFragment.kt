package dev.emg.tasktracker.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import dev.emg.tasktracker.App
import dev.emg.tasktracker.data.vo.TasksList
import dev.emg.tasktracker.databinding.FragmentMainBinding
import dev.emg.tasktracker.ui.addtask.AddTaskDialogFragment
import dev.emg.tasktracker.ui.addtask.AddTaskDialogFragment.OnTasksListAdded
import dev.emg.tasktracker.ui.main.TasksAdapter.OnTasksListListener
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import javax.inject.Inject

class MainFragment : Fragment(), OnTasksListListener {

  private var _binding: FragmentMainBinding? = null
  private val binding: FragmentMainBinding get() = _binding!!
  private lateinit var taskAdapter: TasksAdapter

  @Inject lateinit var viewModel: TasksViewModel

  override fun onAttach(context: Context) {
    super.onAttach(context)
    (requireActivity().application as App).appComponent.inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentMainBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    taskAdapter = TasksAdapter(this)
    val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    val itemDecorator = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
    val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(taskAdapter, requireContext()))

    binding.recyclerview.apply {
      this.adapter = taskAdapter
      this.layoutManager = layoutManager
      this.addItemDecoration(itemDecorator)
      itemTouchHelper.attachToRecyclerView(this)
    }

    binding.addTaskFab.setOnClickListener {
      AddTaskDialogFragment.show(
        requireFragmentManager(),
        object : OnTasksListAdded {
          override fun tasksListAdded(item: TasksList) {
            viewModel.addTask(item)
          }
        }
      )
    }


    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
      viewModel.tasksList.collect {
        when (it) {
          is TasksListUiState.Success -> {
            taskAdapter.submitList(it.data)
          }
          is TasksListUiState.Loading -> {
            Timber.d("Loading")
          }
          is TasksListUiState.Error -> {
            Timber.e("Error -> ${it.exception}")
          }
        }
      }
    }

  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }

  override fun onTasksListWasCompleted(item: TasksList) {
    viewModel.updateTasksList(item)
    binding.recyclerview.post {
      taskAdapter.notifyDataSetChanged()
    }
  }

  override fun onTasksListDeleted(item: TasksList) {
    viewModel.deleteTasksList(item)
  }

  companion object {
    const val TAG = "mainFragment"
  }
}