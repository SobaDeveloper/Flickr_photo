package com.example.photos.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Photo
import com.example.domain.usecase.GetRecentPhotos
import com.example.domain.usecase.GetSearchQuery
import com.example.domain.usecase.SaveSearchQuery
import com.example.domain.usecase.SearchPhotosByTag
import com.example.photos.common.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PhotoSearchViewModel(
    private val getRecentPhotos: GetRecentPhotos,
    private val searchPhotosByTag: SearchPhotosByTag,
    private val getSearchQuery: GetSearchQuery,
    private val saveSearchQuery: SaveSearchQuery
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState<List<Photo>>>(ViewState.Idle)
    val viewState: StateFlow<ViewState<List<Photo>>> = _viewState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore.asStateFlow()

    private var currentPage = INDEX_INITIAL_PAGE
    private var canLoadMore = true
    private var currentSearchTags = ""

    init {
        viewModelScope.launch {
            getSearchQuery().collect {
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
            saveSearchQuery(tags)
            currentSearchTags = tags
            currentPage = INDEX_INITIAL_PAGE
            canLoadMore = true
            _viewState.value = ViewState.Loading

            val result: Result<List<Photo>> = if (tags.isBlank()) {
                getRecentPhotos(page = currentPage)
            } else {
                searchPhotosByTag(tags, page = currentPage)
            }

            handleResult(result, isLoadingMore = false)
        }
    }

    fun loadMorePhotos() {
        if (_isLoadingMore.value || !canLoadMore) return

        viewModelScope.launch {
            _isLoadingMore.value = true
            currentPage++

            val result: Result<List<Photo>> = if (currentSearchTags.isBlank()) {
                getRecentPhotos(page = currentPage)
            } else {
                searchPhotosByTag(currentSearchTags, page = currentPage)
            }

            handleResult(result, isLoadingMore = true)
        }
    }

    private fun loadRecentPhotos() {
        viewModelScope.launch {
            currentSearchTags = ""
            currentPage = 1
            canLoadMore = true
            _viewState.value = ViewState.Loading
            val result = getRecentPhotos(page = currentPage)
            handleResult(result, isLoadingMore = false)
        }
    }

    private fun handleResult(result: Result<List<Photo>>, isLoadingMore: Boolean) {
        result
            .onSuccess { photos ->
                val currentPhotos = (_viewState.value as? ViewState.Success)?.data.orEmpty()
                val updatedPhotos = if (isLoadingMore) currentPhotos + photos else photos

                _viewState.value = ViewState.Success(updatedPhotos)
                canLoadMore = photos.isNotEmpty()
                _isLoadingMore.value = false
            }
            .onFailure { exception ->
                if (isLoadingMore) {
                    currentPage--
                    _isLoadingMore.value = false
                } else {
                    _viewState.value = ViewState.Error(exception.message ?: "Unknown error")
                }
            }
    }

    companion object {
        const val INDEX_INITIAL_PAGE = 1
    }
}