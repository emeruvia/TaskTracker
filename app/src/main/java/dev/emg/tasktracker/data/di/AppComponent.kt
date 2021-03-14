package dev.emg.tasktracker.data.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.emg.tasktracker.ui.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [StorageModule::class])
interface AppComponent {

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance context: Context): AppComponent
  }

  fun inject(activity: MainActivity)

}