package com.example.flickrphoto.di

import com.example.flickrphoto.feature.photodetails.PhotoDetailViewModel
import com.example.flickrphoto.feature.photosearch.PhotoSearchViewModel
import com.example.flickrphoto.util.SearchPrefsManager
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule: Module
    get() = module {
        includes(viewModelModules, dataStoreModule)
    }

val dataStoreModule = module {
    singleOf(::SearchPrefsManager)
}

val viewModelModules = module {
    viewModelOf(::PhotoSearchViewModel)
    viewModelOf(::PhotoDetailViewModel)
}