package dev.emg.tasktracker.data.db

import androidx.room.Dao
import dev.emg.tasktracker.data.vo.TaskItem

@Dao
interface TaskItemDao: BaseDao<TaskItem> {
}