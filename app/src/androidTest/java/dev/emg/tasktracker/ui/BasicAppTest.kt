package dev.emg.tasktracker.ui

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import dev.emg.tasktracker.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class BasicAppTest {

  @Rule
  @JvmField
  var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

  @Test
  fun basicAppTest() {
    val floatingActionButton = onView(
      allOf(
        withId(R.id.add_task_fab), withContentDescription("Add Task"),
        childAtPosition(
          childAtPosition(
            withId(R.id.container),
            0
          ),
          1
        ),
        isDisplayed()
      )
    )
    floatingActionButton.perform(click())

    val appCompatEditText = onView(
      allOf(
        withId(R.id.tasks_name_et),
        childAtPosition(
          childAtPosition(
            withId(R.id.design_bottom_sheet),
            0
          ),
          0
        ),
        isDisplayed()
      )
    )
    appCompatEditText.perform(replaceText("ADDING A TASK"), closeSoftKeyboard())

    val appCompatEditText2 = onView(
      allOf(
        withId(R.id.tasks_detail_et),
        childAtPosition(
          childAtPosition(
            withId(R.id.design_bottom_sheet),
            0
          ),
          1
        ),
        isDisplayed()
      )
    )
    appCompatEditText2.perform(replaceText("WITH DETAIL"), closeSoftKeyboard())

    val materialTextView = onView(
      allOf(
        withId(R.id.save), withText("Save"),
        childAtPosition(
          childAtPosition(
            withId(R.id.design_bottom_sheet),
            0
          ),
          2
        ),
        isDisplayed()
      )
    )
    materialTextView.perform(click())
  }

  private fun childAtPosition(
    parentMatcher: Matcher<View>, position: Int
  ): Matcher<View> {

    return object : TypeSafeMatcher<View>() {
      override fun describeTo(description: Description) {
        description.appendText("Child at position $position in parent ")
        parentMatcher.describeTo(description)
      }

      public override fun matchesSafely(view: View): Boolean {
        val parent = view.parent
        return parent is ViewGroup && parentMatcher.matches(parent)
            && view == parent.getChildAt(position)
      }
    }
  }
}
