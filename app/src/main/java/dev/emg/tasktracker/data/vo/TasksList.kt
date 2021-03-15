package dev.emg.tasktracker.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_table")
data class TasksList(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0L,
  var name: String = "",
  var detail: String = "",
  var wasCompleted: Boolean = false,
  var position: Int = 0,
  var tasksList: List<TaskItem> = emptyList()
)