package dev.emg.tasktracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import dev.emg.tasktracker.R.layout
import dev.emg.tasktracker.databinding.ActivityMainBinding
import dev.emg.tasktracker.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    if (supportFragmentManager.findFragmentByTag(MainFragment.TAG) == null) {
      supportFragmentManager.beginTransaction()
        .replace(binding.root.id, MainFragment(), MainFragment.TAG)
        .commit()
    }
  }
}