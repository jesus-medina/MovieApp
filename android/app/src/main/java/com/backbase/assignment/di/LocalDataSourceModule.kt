package com.backbase.assignment.di

import android.content.Context
import com.backbase.assignment.MovieDatabase
import com.mupper.personlist.Movie
import com.mupper.personlist.MovieQueries
import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.EnumColumnAdapter
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {
    @Provides
    fun providesSqliteDriver(
        @ApplicationContext context: Context,
    ): SqlDriver =
        AndroidSqliteDriver(MovieDatabase.Schema, context, "movie.db")

    @Provides
    fun providesDatabase(sqlDriver: SqlDriver): MovieDatabase =
        MovieDatabase(
            sqlDriver, Movie.Adapter(
                EnumColumnAdapter(),
                listOfLocalDataMovieGenreAdapter
            )
        )

    @Provides
    fun providesQueries(movieDatabase: MovieDatabase): MovieQueries =
        movieDatabase.movieQueries

    private val listOfLocalDataMovieGenreAdapter = object : ColumnAdapter<List<String>, String> {
        override fun decode(databaseValue: String) =
            if (databaseValue.isEmpty()) {
                listOf()
            } else {
                databaseValue.split(",")
            }

        override fun encode(value: List<String>) = value.joinToString(separator = ",")
    }
}