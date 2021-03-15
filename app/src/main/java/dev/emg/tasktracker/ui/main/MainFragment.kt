package dev.emg.tasktracker.ui.main

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.Orientation
import dev.emg.tasktracker.App
import dev.emg.tasktracker.R
import dev.emg.tasktracker.data.vo.TasksList
import dev.emg.tasktracker.databinding.FragmentMainBinding
import dev.emg.tasktracker.ui.addtask.AddTaskDialogFragment
import dev.emg.tasktracker.ui.addtask.AddTaskDialogFragment.OnTasksListAdded
import javax.inject.Inject

class MainFragment : Fragment() {

  private var _binding: FragmentMainBinding? = null
  private val binding: FragmentMainBinding get() = _binding!!

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

    val adapter = TasksAdapter()
    val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    val itemDecorator = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)

    binding.recyclerview.apply {
      this.adapter = adapter
      this.layoutManager = layoutManager
      this.addItemDecoration(itemDecorator)
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

    viewModel.tasksList.observe(viewLifecycleOwner) {
      adapter.submitList(it)
    }
  }

  companion object {
    const val TAG = "mainFragment"
  }
}