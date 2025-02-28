data class TitleListResponse(
    val titles: List<Title>
)

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
        posterUrl = poster ?: ""
    )
}

data class Title(
    val id: String,
    val title: String,
)

data class TitleDetails(
    val id: String,
    val title: String,
    val description: String,
    val releaseDate: String,
    val posterUrl: String?,
)
