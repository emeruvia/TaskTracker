package dev.emg.tasktracker.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dev.emg.tasktracker.data.db.Database
import javax.inject.Singleton

@Module
class TestStorageModule {

  @Singleton
  @Provides
  fun providesInMemoryRoomDb(context: Context): Database {
    return Room.inMemoryDatabaseBuilder(
      context,
      Database::class.java
    ).build()
  }

}