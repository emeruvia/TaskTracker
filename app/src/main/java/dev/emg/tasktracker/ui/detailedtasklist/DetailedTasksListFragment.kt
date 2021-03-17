package dev.emg.tasktracker.ui.detailedtasklist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.emg.tasktracker.App
import dev.emg.tasktracker.data.vo.TasksList
import dev.emg.tasktracker.databinding.FragmentDetailedTasksListBinding

class DetailedTasksListFragment : Fragment() {

  private var _binding: FragmentDetailedTasksListBinding? = null
  private val binding: FragmentDetailedTasksListBinding get() = _binding!!

  private var tasksList: TasksList? = null

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
    tasksList = arguments?.get(ARGS_TASKSLIST) as TasksList

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.taskTitle.text = tasksList?.name

  }

  companion object {
    private const val ARGS_TASKSLIST = "args_tasklists"

    const val TAG = "detailedTaskList"

    fun create(tasksList: TasksList): DetailedTasksListFragment {
      val fragment = DetailedTasksListFragment()
      val bundle = Bundle()
      bundle.putParcelable(ARGS_TASKSLIST, tasksList)
      fragment.arguments = bundle
      return fragment
    }
  }
}