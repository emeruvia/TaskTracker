package dev.emg.tasktracker.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskItem(
  @PrimaryKey(autoGenerate = true)
  val id: Long,
  var name: String,
  var isCompleted: Boolean,
  val order: Int
)
