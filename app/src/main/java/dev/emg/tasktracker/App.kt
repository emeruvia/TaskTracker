package dev.emg.tasktracker

import android.app.Application
import dev.emg.tasktracker.data.di.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : Application() {

  val appComponent by lazy {
    DaggerAppComponent.factory().create(applicationContext)
  }

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    }
  }

}