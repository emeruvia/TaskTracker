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
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.snackbar.Snackbar
import dev.emg.tasktracker.App
import dev.emg.tasktracker.R
import dev.emg.tasktracker.data.vo.ItemUiState
import dev.emg.tasktracker.data.vo.TasksList
import dev.emg.tasktracker.databinding.FragmentMainBinding
import dev.emg.tasktracker.ui.SwipeToDeleteCallback
import dev.emg.tasktracker.ui.TasksListViewModel
import dev.emg.tasktracker.ui.addtask.AddTaskDialogFragment
import dev.emg.tasktracker.ui.addtask.AddTaskDialogFragment.OnTasksListAdded
import dev.emg.tasktracker.ui.detailedtasklist.DetailedTasksListFragment
import dev.emg.tasktracker.ui.goneView
import dev.emg.tasktracker.ui.main.TasksListAdapter.OnTasksListListener
import dev.emg.tasktracker.ui.showView
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import javax.inject.Inject

class MainFragment : Fragment(), OnTasksListListener {

  private var _binding: FragmentMainBinding? = null
  private val binding: FragmentMainBinding get() = _binding!!
  private lateinit var tasksListAdapter: TasksListAdapter

  @Inject lateinit var viewModel: TasksListViewModel

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

    tasksListAdapter = TasksListAdapter(this)
    val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    val itemDecorator = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
    val itemTouchHelper = ItemTouchHelper(
      SwipeToDeleteCallback(
        context = requireContext(),
        tasksListAdapter = tasksListAdapter
      )
    )

    binding.recyclerview.apply {
      this.adapter = tasksListAdapter
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
          is ItemUiState.Success -> {
            binding.lottieAnimation.apply {
              this.pauseAnimation()
              this.goneView()
            }
            binding.recyclerview.showView()
            tasksListAdapter.submitList(it.data)
          }
          is ItemUiState.Loading -> {
            Timber.d("Loading")
            binding.recyclerview.goneView()
            binding.lottieAnimation.apply {
              this.showView()
              this.setAnimation(R.raw.loading_anim)
              this.repeatCount = LottieDrawable.INFINITE
              this.playAnimation()
            }
          }
          is ItemUiState.Error -> {
            Timber.e("Error -> ${it.exception}")
            binding.recyclerview.goneView()
            binding.lottieAnimation.apply {
              this.showView()
              this.setAnimation(R.raw.error_anim)
              this.repeatCount = 0
              this.playAnimation()
            }
          }
        }
      }
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }

  override fun onTasksListWasClicked(item: TasksList) {
//    val transaction = requireActivity().supportFragmentManager.beginTransaction()
//    transaction.addToBackStack(TAG)
//    transaction.replace(
//      binding.root.id,
//      DetailedTasksListFragment.create(item.id),
//      DetailedTasksListFragment.TAG
//    )
//    transaction.commit()
  }

  override fun onTasksListWasCompleted(item: TasksList) {
//    viewModel.updateTasksList(item)
//    binding.recyclerview.post {
//      tasksListAdapter.notifyDataSetChanged()
//    }
  }

  override fun onTasksListDeleted(item: TasksList) {
    viewModel.deleteTasksList(item)

    val snackBar = Snackbar.make(binding.root, R.string.undo, Snackbar.LENGTH_LONG)
    snackBar.setAction(R.string.undo) { v -> viewModel.addTask(item) }
    snackBar.show()
  }

  companion object {
    const val TAG = "mainFragment"
  }
}