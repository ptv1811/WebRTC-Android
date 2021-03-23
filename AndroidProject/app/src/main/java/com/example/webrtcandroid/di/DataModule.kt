package com.example.webrtcandroid.di

import android.content.Context
import com.example.webrtcandroid.utils.preference.PreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Singleton
    @Provides
    fun providePreferencesHelper(@ApplicationContext context: Context) : PreferencesHelper {
        return PreferencesHelper(context)
    }
}