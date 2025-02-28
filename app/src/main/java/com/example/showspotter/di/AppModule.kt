package com.example.showspotter.di

import android.util.Log
import com.example.showspotter.BuildConfig
import com.example.showspotter.data.api.TitleApi
import com.example.showspotter.data.repository.TitleRepository
import com.example.showspotter.data.repository.TitleRepositoryImpl
import com.example.showspotter.domain.usecase.GetCombinedTitlesUseCase
import com.example.showspotter.domain.usecase.GetTitleDetailsUseCase
import com.example.showspotter.presentation.details.DetailsViewModel
import com.example.showspotter.presentation.home.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { createRetrofit() }
    single<TitleApi> { get<Retrofit>().create(TitleApi::class.java) }

    single<TitleRepository> { TitleRepositoryImpl(get()) }
    factory { GetCombinedTitlesUseCase(get()) }
    factory { GetTitleDetailsUseCase(get()) }

    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}

private fun createRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl("https://api.watchmode.com/v1/")
    .client(
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val url = original.url.newBuilder()
                    .addQueryParameter("apiKey", BuildConfig.API_KEY)
                    .build()
                Log.d("API_DEBUG", "Final URL: $url")
                chain.proceed(original.newBuilder().url(url).build())
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    )
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .build()
