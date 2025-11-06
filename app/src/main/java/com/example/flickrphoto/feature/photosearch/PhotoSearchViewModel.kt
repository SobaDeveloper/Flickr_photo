package com.example.flickrphoto.feature.photosearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Photo
import com.example.domain.usecase.GetRecentPhotos
import com.example.domain.usecase.SearchPhotosByTag
import com.example.flickrphoto.feature.common.ViewState
import com.example.flickrphoto.util.SearchPrefsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PhotoSearchViewModel(
    private val getRecentPhotos: GetRecentPhotos,
    private val searchPhotosByTag: SearchPhotosByTag,
    private val searchPrefsManager: SearchPrefsManager
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState<List<Photo>>>(ViewState.Idle)
    val viewState: StateFlow<ViewState<List<Photo>>> = _viewState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        viewModelScope.launch {
            searchPrefsManager.searchQueryFlow.collect {
                _searchQuery.value = it
                if (it.isNotBlank()) {
                    searchPhotos(it)
                } else {
                    loadRecentPhotos()
                }
            }
        }
    }

    fun updateSearchQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun searchPhotos(tags: String) {
        viewModelScope.launch {
            searchPrefsManager.saveSearchQuery(tags)
            _viewState.value = ViewState.Loading

            val result: Result<List<Photo>> = if (tags.isBlank()) {
                getRecentPhotos()
            } else {
                searchPhotosByTag(tags)
            }

            handleResult(result)
        }
    }

    private fun loadRecentPhotos() {
        viewModelScope.launch {
            _viewState.value = ViewState.Loading
            val result = getRecentPhotos()
            handleResult(result)

        }
    }

    private fun handleResult(result: Result<List<Photo>>) {
        result
            .onSuccess { photos ->
                _viewState.value = ViewState.Success(photos)
            }
            .onFailure { exception ->
                _viewState.value = ViewState.Error(exception.message ?: "Unknown error")
            }
    }
}