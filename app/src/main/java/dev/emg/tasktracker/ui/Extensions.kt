package dev.emg.tasktracker.ui

import android.view.View

fun View.showView() {
  this.visibility = View.VISIBLE
}

fun View.goneView() {
  this.visibility = View.GONE
}