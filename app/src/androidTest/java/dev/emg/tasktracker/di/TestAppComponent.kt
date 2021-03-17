package dev.emg.tasktracker.di

import dagger.Component
import dev.emg.tasktracker.data.di.AppComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [TestRepositoryModule::class, TestStorageModule::class])
interface TestAppComponent : AppComponent