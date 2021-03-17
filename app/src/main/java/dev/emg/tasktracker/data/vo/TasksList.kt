package dev.emg.tasktracker.data.vo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tasks_table")
data class TasksList(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0L,
  var name: String = "",
  var detail: String = "",
  var wasCompleted: Boolean = false,
  var position: Int = 0,
  var tasksList: List<TaskItem> = emptyList()
) : Parcelable