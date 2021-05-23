package com.backbase.assignment

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.endsWith
import org.hamcrest.CoreMatchers.startsWith
import org.hamcrest.Description
import org.hamcrest.Matcher


infix fun ViewInteraction.matchesWithText(text: String): ViewInteraction =
    check(matches(withText(text)))

infix fun ViewInteraction.startsWithText(text: String): ViewInteraction =
    check(matches(withText(startsWith(text))))

infix fun ViewInteraction.endsWithText(text: String): ViewInteraction =
    check(matches(withText(endsWith(text))))

infix fun ViewInteraction.matchesWithTextColor(expectedId: Int): ViewInteraction =
    check(matches(withTextColor(expectedId)))

fun ViewInteraction.isDisplayed(): ViewInteraction = check(matches(ViewMatchers.isDisplayed()))

private fun withTextColor(expectedId: Int): Matcher<View?> =
    object : BoundedMatcher<View?, TextView>(TextView::class.java) {
        override fun matchesSafely(textView: TextView): Boolean {
            val colorId = ContextCompat.getColor(textView.context, expectedId)
            return textView.currentTextColor == colorId
        }

        override fun describeTo(description: Description) {
            description.appendText("with text color: ")
            description.appendValue(expectedId)
        }
    }