package com.example.hogwarts.di

import androidx.room.Room
import com.example.hogwarts.constants.BASE_URL
import com.example.hogwarts.data.AppDatabase
import com.example.hogwarts.houses.repository.HouseRepo
import com.example.hogwarts.api.ApiClient
import com.example.hogwarts.houses.service.GetHousesService
import com.example.hogwarts.houses.viewmodel.MainActivityViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel {
        MainActivityViewModel(get())
    }
}

val repoModule = module {
    single {
        HouseRepo(get(), get())
    }
}

val apiModule = module {
    fun provideApi(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }

    single { provideApi(get()) }
}

val persistenceModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "house.db").build()
    }
    single { get<AppDatabase>().houseDao() }
}

val retrofitModule = module {
    fun retrofit(baseUrl: String) = Retrofit.Builder()
        .callFactory(OkHttpClient.Builder().build())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())  // 6
        .build()

    fun okHttp() = OkHttpClient.Builder()
        .build()

    single {   // 2
        okHttp()  // 3
    }
    single {
        retrofit(BASE_URL)  // 4
    }
    single {
        get<Retrofit>().create(GetHousesService::class.java)   // 5
    }

}