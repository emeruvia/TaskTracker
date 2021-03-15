package dev.emg.tasktracker.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dev.emg.tasktracker.data.db.Database
import javax.inject.Singleton

@Module
class StorageModule {

  @Singleton
  @Provides
  fun providesRoomDb(context: Context): Database {
    return Room.databaseBuilder(
      context,
      Database::class.java,
      "task-tracker-db"
    ).build()
  }

}