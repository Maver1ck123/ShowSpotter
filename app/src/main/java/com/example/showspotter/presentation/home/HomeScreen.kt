package com.example.showspotter.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.showspotter.data.model.Title
import com.example.showspotter.presentation.home.component.ShimmerEffect
import com.example.showspotter.presentation.home.component.TitleItem
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onItemClick: (String) -> Unit
) {
    val uiState by viewModel.uiState
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ShowSpotter", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            when (uiState) {
                is HomeUiState.Loading -> ShimmerEffect()
                is HomeUiState.Success -> TitleListContent(
                    movies = (uiState as HomeUiState.Success).movies,
                    shows = (uiState as HomeUiState.Success).tvShows,
                    onItemClick = onItemClick
                )

                is HomeUiState.Error -> ErrorPlaceholder((uiState as HomeUiState.Error).message)
            }
        }
    }
}

@Composable
private fun ErrorPlaceholder(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Failed to load content", color = MaterialTheme.colorScheme.error)
    }
}

@Composable
private fun TitleListContent(
    movies: List<Title>,
    shows: List<Title>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(initialPage = 0) { 2 }
    val scope = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    height = 4.dp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        ) {
            Tab(
                selected = pagerState.currentPage == 0,
                onClick = { scope.launch { pagerState.animateScrollToPage(0) } }
            ) {
                Text("Movies", modifier = Modifier.padding(16.dp))
            }

            Tab(
                selected = pagerState.currentPage == 1,
                onClick = { scope.launch { pagerState.animateScrollToPage(1) } }
            ) {
                Text("TV Shows", modifier = Modifier.padding(16.dp))
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> LazyColumn {
                    items(movies, key = { it.id }) { title ->
                        TitleItem(title = title, onItemClick = onItemClick)
                    }
                }
                1 -> LazyColumn {
                    items(shows, key = { it.id }) { title ->
                        TitleItem(title = title, onItemClick = onItemClick)
                    }
                }
            }
        }
    }
}
