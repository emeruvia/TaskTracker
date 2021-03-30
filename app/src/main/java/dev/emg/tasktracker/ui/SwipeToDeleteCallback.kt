package dev.emg.tasktracker.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.emg.tasktracker.R
import dev.emg.tasktracker.ui.detailedtasklist.TasksAdapter
import dev.emg.tasktracker.ui.main.TasksListAdapter

class SwipeToDeleteCallback(
  context: Context,
//  private val tasksListAdapter: TasksListAdapter? = null,
  private val taskAdapter: TasksAdapter? = null
) : ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT, ItemTouchHelper.LEFT) {

  private val accentColor = context.getColorStateList(R.color.color_state_list).defaultColor
  private val colorDrawable = ColorDrawable(accentColor)
  private val drawable: Drawable =
    ContextCompat.getDrawable(context, R.drawable.ic_delete_sweep_24)!!

  override fun onMove(
    recyclerView: RecyclerView,
    viewHolder: ViewHolder,
    target: ViewHolder
  ): Boolean {
    return false
  }

  override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
    val position = viewHolder.adapterPosition
//    tasksListAdapter?.deleteItemAtPosition(position)
    taskAdapter?.deleteItemAtPosition(position)
  }

  override fun onChildDraw(
    c: Canvas,
    recyclerView: RecyclerView,
    viewHolder: ViewHolder,
    dX: Float,
    dY: Float,
    actionState: Int,
    isCurrentlyActive: Boolean
  ) {
    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    val itemView = viewHolder.itemView
    val backgroundCornerOffset = 20
    val iconMargin = (itemView.height - drawable.intrinsicHeight) / 2
    val iconTop = itemView.top + (itemView.height - drawable.intrinsicHeight)
    val iconBottom = iconTop + drawable.intrinsicHeight
    when {
      dX > 0 -> {
        val iconLeft = itemView.left + iconMargin + drawable.intrinsicWidth
        val iconRight = itemView.left + iconMargin
        drawable.setBounds(iconLeft, iconTop, iconRight, iconBottom)
        colorDrawable.setBounds(
          itemView.left,
          itemView.top,
          itemView.left + dX.toInt() + backgroundCornerOffset,
          itemView.bottom
        )
      }
      dX < 0 -> {
        val iconLeft = itemView.right - iconMargin - drawable.intrinsicWidth
        val iconRight = itemView.right - iconMargin
        drawable.setBounds(iconLeft, iconTop, iconRight, iconBottom)
        colorDrawable.setBounds(
          itemView.right + dX.toInt() - backgroundCornerOffset,
          itemView.top,
          itemView.right,
          itemView.bottom
        )
      }
      else -> {
        colorDrawable.setBounds(0, 0, 0, 0)
      }
    }
    colorDrawable.draw(c)
    drawable.draw(c)
  }
}