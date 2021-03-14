package dev.emg.tasktracker.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
  @PrimaryKey(autoGenerate = true)
  val id: Long
)