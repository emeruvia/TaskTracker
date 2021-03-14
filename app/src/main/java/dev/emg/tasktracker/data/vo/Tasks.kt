package dev.emg.tasktracker.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tasks(
  @PrimaryKey(autoGenerate = true)
  val id: Long,
  val name: String,
  val tasksList: List<TaskItem>
)