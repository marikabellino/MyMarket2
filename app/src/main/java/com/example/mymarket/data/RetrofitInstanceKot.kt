package com.example.mymarket.data
import com.example.mymarket.data.network.ClienteService
import com.example.mymarket.data.network.MarchioService
import com.example.mymarket.data.network.OrdineService
import com.example.mymarket.data.network.ProdottoService
import com.example.mymarket.data.network.PuntoVenditaService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceKot {
    private val marchioService: MarchioService =
        provideRetrofit().create(MarchioService::class.java)

    private val puntoVenditaService: PuntoVenditaService =
        provideRetrofit().create(PuntoVenditaService::class.java)

    private val ordineService: OrdineService =
        provideRetrofit().create(OrdineService::class.java)

    private val clienteService: ClienteService =
        provideRetrofit().create(ClienteService::class.java)

    private val prodottoService: ProdottoService =
        provideRetrofit().create(ProdottoService::class.java)

    private fun provideRetrofit(
    ): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://sacred-nominally-lizard.ngrok-free.app/")
            .client(client)
            .build()
    }
/*
   suspend fun getCliente(): List<Cliente> {
        return clienteService.getDetailsCliente()
    }*/

   /* suspend fun getItemsNews(newsId: Int): ItemsDto {
        return newsService.newsStoriesItems(newsId)
    }

    suspend fun getIdTops(): TopsDto {
        return topsService.topStories()
    }

    suspend fun getItemsTops(topsId: Int): ItemsDto {
        return topsService.topStoriesItems(topsId)
    }
    suspend fun getIdBestStories(): BestStoriesDto {
        return bestStoriesService.bestStories()
    }

    suspend fun getItemsBestStories(bestStoriesId: Int): ItemsDto {
        return bestStoriesService.bestStoriesItems(bestStoriesId)
    }*/
}