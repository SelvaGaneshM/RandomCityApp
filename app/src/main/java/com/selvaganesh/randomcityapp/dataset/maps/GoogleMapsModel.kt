package com.selvaganesh.randomcityapp.dataset.maps

import com.selvaganesh.randomcityapp.common.module.NetworkModule
import com.selvaganesh.randomcityapp.dataset.mapDomain.GoogleMapsRepository
import com.selvaganesh.randomcityapp.dataset.maps.remote.api.GoogleMapsAPI
import com.selvaganesh.randomcityapp.dataset.maps.repository.GoogleMapsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class GoogleMapsModel {
    @Singleton
    @Provides
    fun provideGoogleMaps(retrofit: Retrofit): GoogleMapsAPI {
        return retrofit.create(GoogleMapsAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideGoogleMapsRepository(addDriverAPI: GoogleMapsAPI): GoogleMapsRepository {
        return GoogleMapsRepositoryImpl(addDriverAPI)
    }
}