package com.example.showspotter.data.repository

import com.example.showspotter.data.model.Title
import com.example.showspotter.data.model.TitleDetails
import io.reactivex.rxjava3.core.Single

interface TitleRepository {
    fun getCombinedTitles(): Single<Pair<List<Title>, List<Title>>>
    fun getTitleDetails(id: String): Single<TitleDetails>
}
