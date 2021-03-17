package dev.emg.tasktracker.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dev.emg.tasktracker.TestUtils
import dev.emg.tasktracker.data.db.Database
import dev.emg.tasktracker.data.db.TasksListDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class TasksListDaoTest {

  private lateinit var database: Database
  private lateinit var dao: TasksListDao

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @Before
  fun createDb() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    database = Room.inMemoryDatabaseBuilder(
      context, Database::class.java
    ).build()
    dao = database.tasksListDao()
  }

  @After
  @Throws(IOException::class)
  fun closeDb() {
    database.close()
  }

  @Test
  fun insertTasksListItemToDatabase() = runBlockingTest {
    val tasksList = TestUtils.tasksListItem
    dao.insert(tasksList)

    val result = dao.getAllTasks()
    assertThat(result).contains(tasksList)
  }

  @Test
  fun insertMultipleTasksListItemToDatabase() = runBlockingTest {
    val listOfTasksList = TestUtils.listOfTasksListItem
    dao.insertAll(listOfTasksList)

    val result = dao.getAllTasks()
    assertThat(result).isEqualTo(listOfTasksList)
  }

  @Test
  fun deleteTasksListItemFromDatabase() = runBlockingTest {
    val tasksList = TestUtils.tasksListItem
    dao.insert(tasksList)
    dao.delete(tasksList)

    val result = dao.getAllTasks()
    assertThat(result).isEmpty()
  }

  @Test
  fun updateTasksListItemInDatabase() = runBlockingTest {
    val tasksList = TestUtils.tasksListItem
    val updatedItem = tasksList
    updatedItem.name = "Updated name"
    dao.insert(tasksList)
    dao.update(updatedItem)

    val result = dao.getAllTasks()
    assertThat(result).contains(updatedItem)
  }
}