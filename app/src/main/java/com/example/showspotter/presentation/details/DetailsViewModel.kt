package com.example.showspotter.presentation.details

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.showspotter.data.model.TitleDetails
import com.example.showspotter.domain.usecase.GetTitleDetailsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException

class DetailsViewModel(
    private val getTitleDetails: GetTitleDetailsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState: StateFlow<DetailsUiState> = _uiState

    @SuppressLint("CheckResult")
    fun loadDetails(titleId: String) {
        getTitleDetails(titleId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ details ->
                _uiState.value = DetailsUiState.Success(details)
            }, { error ->
                Log.e("DetailsVM", "Error loading details", error)
                _uiState.value = DetailsUiState.Error(
                    message = when (error) {
                        is HttpException -> "Error ${error.code()}: ${error.message()}"
                        else -> "Failed to load details: ${error.localizedMessage}"
                    }
                )
            })
    }
}

sealed class DetailsUiState {
    object Loading : DetailsUiState()
    data class Success(val details: TitleDetails) : DetailsUiState()
    data class Error(val message: String) : DetailsUiState()
}
