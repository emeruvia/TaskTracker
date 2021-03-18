package dev.emg.tasktracker.data.vo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "task_item_table")
data class TaskItem(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0L,
  var name: String,
  var wasCompleted: Boolean = false,
  val position: Int = 0
) : Parcelable
