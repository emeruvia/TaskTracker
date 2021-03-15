package dev.emg.tasktracker.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_table")
data class Tasks(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0L,
  val name: String,
  val tasksList: List<TaskItem> = emptyList()
)