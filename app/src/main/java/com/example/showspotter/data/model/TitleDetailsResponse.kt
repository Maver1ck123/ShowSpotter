package com.example.showspotter.data.model

data class TitleDetailsResponse(
    val id: String,
    val title: String,
    val plot_overview: String,
    val release_date: String,
    val poster: String?
) {
    fun toTitleDetails() = TitleDetails(
        id = id,
        title = title,
        description = plot_overview,
        releaseDate = release_date,
        posterUrl = poster
    )
}
