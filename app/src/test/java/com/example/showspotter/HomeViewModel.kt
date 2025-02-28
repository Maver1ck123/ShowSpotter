package com.example.showspotter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.showspotter.data.repository.TitleRepository
import com.example.showspotter.domain.usecase.GetCombinedTitlesUseCase
import com.example.showspotter.presentation.home.HomeUiState
import com.example.showspotter.presentation.home.HomeViewModel
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mockRepository = mockk<TitleRepository>()
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(
            GetCombinedTitlesUseCase(mockRepository)
        )
    }

    @Test
    fun `loadData should emit success state when data is fetched`() {
        // Stub repository response
        every { mockRepository.getCombinedTitles() } returns Single.just(
            Pair(emptyList(), emptyList())
        )

        // Verify initial state is loading
        assertTrue(viewModel.uiState.value is HomeUiState.Loading)

        // Trigger data loading (remove private modifier from loadData in ViewModel)
        viewModel.loadData()

        // Verify success state
        assertTrue(viewModel.uiState.value is HomeUiState.Success)
    }

    @Test
    fun `loadData should emit error state on failure`() {
        // Stub error response
        every { mockRepository.getCombinedTitles() } returns Single.error(Throwable("Error"))

        // Verify initial state is loading
        assertTrue(viewModel.uiState.value is HomeUiState.Loading)

        // Trigger data loading
        viewModel.loadData()

        // Verify error state
        assertTrue(viewModel.uiState.value is HomeUiState.Error)
    }
}
