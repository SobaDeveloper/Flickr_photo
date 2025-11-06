package com.example.flickrphoto.di

import com.example.photos.details.PhotoDetailViewModel
import com.example.photos.search.PhotoSearchViewModel
import com.example.data.local.SearchPrefsManager
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
    viewModelOf(::PhotoDetailViewModel)
    viewModelOf(::PhotoSearchViewModel)
}