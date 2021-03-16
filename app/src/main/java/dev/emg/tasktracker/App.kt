package dev.emg.tasktracker

import android.app.Application
import dev.emg.tasktracker.data.di.AppComponent
import dev.emg.tasktracker.data.di.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

open class App : Application() {

  val appComponent by lazy {
    initializeAppComponent()
  }

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    }
  }

  open fun initializeAppComponent(): AppComponent {
    return DaggerAppComponent.factory().create(applicationContext)
  }

}