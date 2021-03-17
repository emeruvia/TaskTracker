package dev.emg.tasktracker

import dev.emg.tasktracker.data.vo.TaskItem
import dev.emg.tasktracker.data.vo.TasksList

object TestUtils {

  val listOfTasks: List<TaskItem> = listOf(
    TaskItem(1, "Task 1", false, 0),
    TaskItem(2, "Task 2", false, 1),
    TaskItem(3, "Task 3", false, 2),
    TaskItem(4, "Task 4", false, 3),
  )

  val tasksListItem: TasksList = TasksList(
    id = 1,
    name = "Task list 1",
    detail = "Task list 1 detail",
    wasCompleted = false,
    position = 0,
    tasksList = listOfTasks
  )

  val listOfTasksListItem: List<TasksList> = listOf(
    TasksList(1, "List 1"),
    TasksList(2, "List 2"),
    TasksList(3, "List 3")
  )

}