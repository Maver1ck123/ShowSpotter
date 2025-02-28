package com.example.showspotter.domain.usecase

import com.example.showspotter.data.model.TitleDetails
import com.example.showspotter.data.repository.TitleRepository
import io.reactivex.rxjava3.core.Single

class GetTitleDetailsUseCase(private val repository: TitleRepository) {
    operator fun invoke(id: String): Single<TitleDetails> {
        return repository.getTitleDetails(id)
    }
}
