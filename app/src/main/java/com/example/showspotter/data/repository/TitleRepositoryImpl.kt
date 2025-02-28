// data/repository/TitleRepositoryImpl.kt
package com.example.showspotter.data.repository

import com.example.showspotter.data.api.TitleApi
import com.example.showspotter.data.model.Title
import io.reactivex.rxjava3.core.Single
import com.example.showspotter.data.model.TitleDetails

class TitleRepositoryImpl(
    private val api: TitleApi
) : TitleRepository {

    override fun getCombinedTitles(): Single<Pair<List<Title>, List<Title>>> {
        return Single.zip(
            api.getMovies().map { response ->
                response.titles.mapNotNull { apiTitle ->
                    Title(
                        id = apiTitle.id ?: "",
                        title = apiTitle.title ?: "Untitled",
                        type = "movie",
                        year = apiTitle.year ?: 0,
                        imdbRating = apiTitle.imdbRating ?: "N/A"
                    )
                }
            },
            api.getTVShows().map { response ->
                response.titles.mapNotNull { apiTitle ->
                    Title(
                        id = apiTitle.id ?: "",
                        title = apiTitle.title ?: "Untitled",
                        type = "show",
                        year = apiTitle.year ?: 0,
                        imdbRating = apiTitle.imdbRating ?: "N/A"
                    )
                }
            },
            { movies, shows -> movies to shows }
        )
    }

    override fun getTitleDetails(id: String): Single<TitleDetails> {
        return api.getTitleDetails(id).map { response ->
            TitleDetails(
                id = response.id ?: "",
                title = response.title ?: "Unknown Title",
                description = response.plot_overview ?: "No description available",
                releaseDate = response.release_date ?: "Date not available",
                posterUrl = response.poster ?: ""
            )
        }
    }
}
