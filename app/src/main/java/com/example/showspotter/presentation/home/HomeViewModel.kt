package com.example.showspotter.presentation.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.showspotter.BuildConfig
import com.example.showspotter.data.model.Title
import com.example.showspotter.domain.usecase.GetCombinedTitlesUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.net.SocketTimeoutException

class HomeViewModel(
    private val getCombinedTitles: GetCombinedTitlesUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()
    private val _uiState = mutableStateOf<HomeUiState>(HomeUiState.Loading)
    val uiState: State<HomeUiState> = _uiState

    init {
        Log.d("API_KEY_DEBUG", "Loaded API Key: ${BuildConfig.API_KEY}")
        loadData()
    }

    fun loadData() {
        Log.d("API", "Starting data load with key: ${BuildConfig.API_KEY}")
        getCombinedTitles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _uiState.value = HomeUiState.Loading }
            .subscribe({ (movies, shows) ->
                Log.d("API", "Received ${movies.size} movies, ${shows.size} shows")
                _uiState.value = HomeUiState.Success(movies, shows)
            }, { error ->
                Log.e("API", "API Error: ${error.message}", error)
                _uiState.value = HomeUiState.Error(
                    message = when (error) {
                        is HttpException -> when (error.code()) {
                            401 -> "Invalid API Key (Received 401)"
                            404 -> "Resource not found"
                            else -> "Server error: ${error.code()}"
                        }
                        is SocketTimeoutException -> "Connection timeout"
                        else -> "Network error: ${error.localizedMessage ?: "Unknown error"}"
                    }
                )
            })
            .addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("ViewModel", "Clearing HomeViewModel")
        disposables.clear()
    }
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(
        val movies: List<Title>,
        val tvShows: List<Title>
    ) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}
