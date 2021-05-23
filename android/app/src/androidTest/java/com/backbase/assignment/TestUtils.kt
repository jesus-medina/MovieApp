package com.backbase.assignment

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.endsWith
import org.hamcrest.CoreMatchers.startsWith
import org.hamcrest.CoreMatchers.containsString


infix fun ViewInteraction.matchesWithText(text: String): ViewInteraction =
    check(matches(withText(text)))

infix fun ViewInteraction.startsWithText(text: String): ViewInteraction =
    check(matches(withText(startsWith(text))))

infix fun ViewInteraction.endsWithText(text: String): ViewInteraction =
    check(matches(withText(endsWith(text))))

infix fun ViewInteraction.containsText(text: String): ViewInteraction =
    check(matches(withText(containsString(text))))