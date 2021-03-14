package dev.emg.tasktracker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.emg.tasktracker.data.vo.Task

@Database(entities = [Task::class], version = 1)
abstract class Database : RoomDatabase() {
}