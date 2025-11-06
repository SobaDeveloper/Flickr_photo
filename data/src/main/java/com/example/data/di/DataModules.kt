package com.example.data.di

import com.example.data.local.SearchPrefsManager
import com.example.data.service.flickr.FlickrService
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModule: Module
    get() = module {
        includes(serviceModule, localModule)
    }

val serviceModule = module {
    single { createService(get(), FlickrService::class.java) }
}

val localModule = module {
    single { SearchPrefsManager(androidContext()) }
}