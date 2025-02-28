package com.example.showspotter.data.model

data class TitleListResponse(
    val titles: List<Title>
) {
    fun toTitleList(): List<Title> = titles
}
