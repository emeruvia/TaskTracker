package dev.emg.tasktracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.emg.tasktracker.R
import dev.emg.tasktracker.databinding.FragmentMainBinding

class MainFragment : Fragment() {

  private var _binding: FragmentMainBinding? = null
  private val binding: FragmentMainBinding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentMainBinding.inflate(inflater, container, false)
    return binding.root
  }

}