package me.theek.memox.core.data.di

import android.content.Context
import android.location.LocationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.theek.memox.core.data.service.LocationService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    @Singleton
    fun provideLocationService(@ApplicationContext context: Context): LocationService {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return LocationService(locationManager, context)
    }
}