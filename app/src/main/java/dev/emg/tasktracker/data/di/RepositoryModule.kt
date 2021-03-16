package dev.emg.tasktracker.data.di

import dagger.Binds
import dagger.Module
import dev.emg.tasktracker.data.repository.Repository
import dev.emg.tasktracker.data.repository.TasksRepository
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

  @Singleton
  @Binds
  abstract fun provideRepository(tasksRepository: TasksRepository): Repository

}