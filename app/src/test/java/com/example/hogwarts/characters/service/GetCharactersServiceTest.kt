package com.example.hogwarts.characters.service

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection


class GetCharactersServiceTest {

    private var mockWebServer = MockWebServer()
    private lateinit var apiService: GetCharactersService

    @Before
    fun setup() {
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetCharactersService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `test getCharacters returns expected data`() {
        runBlocking {
            val response = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
            mockWebServer.enqueue(response)

            val product = apiService.getCharacters("101")
            assertNotNull(product)
        }
    }
}