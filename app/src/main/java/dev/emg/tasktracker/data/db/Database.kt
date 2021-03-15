package dev.emg.tasktracker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.emg.tasktracker.data.vo.TaskItem
import dev.emg.tasktracker.data.vo.TasksList

@Database(entities = [TasksList::class, TaskItem::class], version = 1, exportSchema = false)
@TypeConverters(TaskItemTypeConverter::class)
abstract class Database : RoomDatabase() {
  abstract fun tasksDao(): TasksListDao
  abstract fun taskItemDao(): TaskItemDao
}