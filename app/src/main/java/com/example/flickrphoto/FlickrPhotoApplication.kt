package com.example.flickrphoto

import android.app.Application
import com.example.data.di.dataModule
import com.example.data.di.networkModule
import com.example.domain.di.domainModule
import com.example.photos.di.photosModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class FlickrPhotoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FlickrPhotoApplication)
            modules(
                listOf(
                    networkModule,
                    dataModule,
                    domainModule,
                    photosModule
                )
            )
        }
    }
}
