package dev.emg.tasktracker.data.db

import androidx.room.Dao
import dev.emg.tasktracker.data.vo.Tasks

@Dao
interface TasksDao: BaseDao<Tasks> {

}