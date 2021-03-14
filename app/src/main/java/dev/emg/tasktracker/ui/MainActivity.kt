package dev.emg.tasktracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.emg.tasktracker.R.layout

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
  }
}