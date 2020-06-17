package com.example.hogwarts.di

import androidx.room.Room
import com.example.hogwarts.constants.BASE_URL
import com.example.hogwarts.data.AppDatabase
import com.example.hogwarts.houses.repository.HouseRepo
import com.example.hogwarts.api.ApiClient
import com.example.hogwarts.characters.repo.CharacterRepo
import com.example.hogwarts.characters.service.GetCharactersService
import com.example.hogwarts.characters.viewmodel.HouseCharactersViewModel
import com.example.hogwarts.houses.service.GetHousesService
import com.example.hogwarts.houses.viewmodel.HousesActivityViewModel
import com.example.hogwarts.spells.repo.SpellsRepo
import com.example.hogwarts.spells.service.GetSpellsService
import com.example.hogwarts.spells.viewmodel.SpellsActivityViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel {
        HousesActivityViewModel(get())
    }
}

val charactersViewModelModule = module {
    viewModel {
        HouseCharactersViewModel(get())
    }
}

val spellsViewModelModule = module {
    viewModel {
        SpellsActivityViewModel(get())
    }
}

val repoModule = module {
    single {
        HouseRepo(get(), get())
    }
}

val characterRepoModule = module {
    single {
        CharacterRepo(get(), get())
    }
}

val spellsRepoModule = module {
    single {
        SpellsRepo(get(), get())
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
    single { get<AppDatabase>().characterDao() }
    single { get<AppDatabase>().spellsDao() }
}

val retrofitModule = module {
    fun retrofit(baseUrl: String) = Retrofit.Builder()
        .callFactory(OkHttpClient.Builder().build())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun okHttp() = OkHttpClient.Builder()
        .build()

    single {
        okHttp()
    }
    single {
        retrofit(BASE_URL)
    }
    single {
        get<Retrofit>().create(GetHousesService::class.java)
    }
    single {
        get<Retrofit>().create(GetCharactersService::class.java)
    }
    single {
        get<Retrofit>().create(GetSpellsService::class.java)
    }
}