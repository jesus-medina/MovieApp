package com.backbase.assignment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesAppLocale(): Locale = Locale.getDefault()

    @Provides
    @Named("uiReleaseDateFormat")
    fun providesUIReleaseDateFormat(locale: Locale): DateFormat = SimpleDateFormat("MMMM d, y", locale)

    @Provides
    @Named("dataReleaseDateFormat")
    fun providesDataReleaseDateFormat(locale: Locale): DateFormat = SimpleDateFormat("yyyy-MM-dd", locale)
}