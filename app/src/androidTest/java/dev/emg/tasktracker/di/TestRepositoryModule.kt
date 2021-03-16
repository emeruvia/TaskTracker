package dev.emg.tasktracker.di

import dagger.Binds
import dagger.Module
import dev.emg.tasktracker.data.repository.Repository

@Module
abstract class TestRepositoryModule {

  @Binds
  abstract fun provideRepository(repository: FakeRepository): Repository

}