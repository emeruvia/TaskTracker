package dev.emg.tasktracker.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.emg.tasktracker.data.vo.TaskItem

class TaskItemTypeConverter {

  private val gson = Gson()

  @TypeConverter
  fun fromStringToTaskItemList(data: String?): List<TaskItem> {
    data?.let {
      val listType = object : TypeToken<List<TaskItem?>?>() {}.type
      return gson.fromJson(data, listType)
    } ?: return emptyList()
  }

  @TypeConverter
  fun fromTaskItemListToString(items: List<TaskItem>): String {
    return gson.toJson(items)
  }

}