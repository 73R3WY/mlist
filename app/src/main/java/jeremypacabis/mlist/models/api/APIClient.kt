package jeremypacabis.mlist.models.api

import android.content.Context
import jeremypacabis.mlist.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by jeremypacabis on Aug 16, 2019 @ 12:18.
 * @author Jeremy Patrick Pacabis <jeremypacabis@gmail.com>
 * jeremypacabis.mlist.models.api <mlist>
 */
class APIClient(baseUrl: String, context: Context) {

    var searchFullParametersUrl: String = context.resources.getString(R.string.media_search_full_parameters)

    var service: Service = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(OkHttpClient().newBuilder().addInterceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.addHeader("Content-Type", "application/json")
            builder.addHeader("Accept", "application/json")
            chain.proceed(builder.build())
        }.build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(Service::class.java)

    fun getMediaSearchResultsFromUrl(searchUrl: String = searchFullParametersUrl) =
        service.getMediaSearchResultsFromUrl(searchUrl)

    fun getMediaSearchResults(
        country: String = "au",
        media: String = "movie",
        term: String = "star",
        all: String = ""
    ) = service.getMediaSearchResults(
        country = country,
        media = media,
        term = term,
        all = all
    )
}