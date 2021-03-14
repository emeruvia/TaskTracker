package dev.emg.tasktracker.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dev.emg.tasktracker.data.db.Database

@Module
class StorageModule {

  fun providesRoomDb(context: Context): Database {
    return Room.databaseBuilder(
      context,
      Database::class.java,
      "task-tracker-db"
    ).build()
  }

}