package jeremypacabis.mlist.models.api

import io.reactivex.Observable
import jeremypacabis.mlist.models.responses.MediaSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by jeremypacabis on Aug 16, 2019 @ 12:19.
 * @author Jeremy Patrick Pacabis <jeremypacabis@gmail.com>
 * jeremypacabis.mlist.models.api <mlist>
 */
interface Service {

    @GET("search")
    fun getMediaSearchResults(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String,
        @Query("all") all: String
    ): Observable<MediaSearchResponse>

    @GET
    fun getMediaSearchResultsFromUrl(@Url url: String): Observable<MediaSearchResponse>
}
