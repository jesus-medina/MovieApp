package com.backbase.assignment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesAppLocale(): Locale = Locale.getDefault()

    @Provides
    fun providesReleaseDateFormat(locale: Locale): DateFormat = SimpleDateFormat("MMMM d, y", locale)
}