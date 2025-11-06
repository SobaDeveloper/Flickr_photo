package com.example.domain.di

import com.example.domain.repo.PhotosRepository
import com.example.domain.repo.SearchPrefsRepository
import com.example.domain.usecase.GetPhotoDetails
import com.example.domain.usecase.GetRecentPhotos
import com.example.domain.usecase.GetSearchQuery
import com.example.domain.usecase.SaveSearchQuery
import com.example.domain.usecase.SearchPhotosByTag
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val domainModule: Module
    get() = module {
        includes(repositoryModules, useCaseModules)
    }

val repositoryModules = module {
    singleOf(::PhotosRepository)
    singleOf(::SearchPrefsRepository)
}

val useCaseModules = module {
    singleOf(::GetRecentPhotos)
    singleOf(::SearchPhotosByTag)
    singleOf(::GetPhotoDetails)

    singleOf(::GetSearchQuery)
    singleOf(::SaveSearchQuery)
}