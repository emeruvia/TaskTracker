package dev.emg.tasktracker.data.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.emg.tasktracker.ui.MainActivity
import dev.emg.tasktracker.ui.main.MainFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [StorageModule::class, RepositoryModule::class])
interface AppComponent {

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance context: Context): AppComponent
  }

  fun inject(activity: MainActivity)
  fun inject(fragment: MainFragment)

}