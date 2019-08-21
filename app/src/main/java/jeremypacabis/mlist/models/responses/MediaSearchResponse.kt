package jeremypacabis.mlist.models.responses

import com.google.gson.annotations.SerializedName

data class MediaSearchResponse(

    @field:SerializedName("resultCount")
    val resultCount: Int? = null,

    @field:SerializedName("results")
    val results: List<MediaItem?>? = null
)