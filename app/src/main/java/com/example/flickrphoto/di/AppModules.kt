package com.example.flickrphoto.di

import com.example.flickrphoto.feature.photosearch.PhotoSearchViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule: Module
    get() = module {
        includes(viewModelModules)
    }

val viewModelModules = module {
    viewModelOf(::PhotoSearchViewModel)
}