package com.example.showspotter.domain.usecase

import com.example.showspotter.data.model.Title
import com.example.showspotter.data.repository.TitleRepository
import io.reactivex.rxjava3.core.Single

class GetCombinedTitlesUseCase(
    private val repository: TitleRepository
) {
    operator fun invoke(): Single<Pair<List<Title>, List<Title>>> {
        return repository.getCombinedTitles()
    }
}
