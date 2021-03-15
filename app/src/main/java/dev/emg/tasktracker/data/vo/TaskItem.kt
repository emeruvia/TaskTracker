package dev.emg.tasktracker.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_item_table")
data class TaskItem(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0L,
  var name: String,
  var wasCompleted: Boolean,
  val position: Int
)
