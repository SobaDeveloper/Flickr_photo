package com.example.photos.di

import com.example.photos.details.PhotoDetailViewModel
import com.example.photos.search.PhotoSearchViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val photosModule: Module
    get() = module {
        includes(viewModelModules)
    }

val viewModelModules = module {
    viewModelOf(::PhotoDetailViewModel)
    viewModelOf(::PhotoSearchViewModel)
}