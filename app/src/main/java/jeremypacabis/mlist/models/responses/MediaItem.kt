package jeremypacabis.mlist.models.responses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "media_table")
data class MediaItem(

    @field:SerializedName("artworkUrl100")
    @ColumnInfo(name = "artworkUrl100")
    val artworkUrl100: String? = null,

//    @field:SerializedName("country")
//    val country: String? = null,

//    @field:SerializedName("copyright")
//    val copyright: String? = null,

//    @field:SerializedName("collectionViewUrl")
//    val collectionViewUrl: String? = null,

//    @field:SerializedName("previewUrl")
//    val previewUrl: String? = null,

//    @field:SerializedName("releaseDate")
//    val releaseDate: String? = null,

    @field:SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String? = null,

//    @field:SerializedName("artistId")
//    val artistId: Int? = null,

    @field:SerializedName("collectionPrice")
    @ColumnInfo(name = "collectionPrice")
    val collectionPrice: Double? = null,

    @field:SerializedName("primaryGenreName")
    @ColumnInfo(name = "primaryGenreName")
    val primaryGenreName: String? = null,

//    @field:SerializedName("collectionName")
//    val collectionName: String? = null,

//    @field:SerializedName("artistViewUrl")
//    val artistViewUrl: String? = null,

//    @field:SerializedName("collectionExplicitness")
//    val collectionExplicitness: String? = null,

//    @field:SerializedName("trackCount")
//    val trackCount: Int? = null,

    @field:SerializedName("artworkUrl60")
    @ColumnInfo(name = "artworkUrl60")
    val artworkUrl60: String? = null,

//    @field:SerializedName("wrapperType")
//    val wrapperType: String? = null,

//    @field:SerializedName("artistName")
//    val artistName: String? = null,

    @field:SerializedName("currency")
    @ColumnInfo(name = "currency")
    val currency: String? = null,

    @field:SerializedName("collectionId")
    @ColumnInfo(name = "collectionId")
    val collectionId: Int? = null,

//    @field:SerializedName("collectionCensoredName")
//    val collectionCensoredName: String? = null,

//    @field:SerializedName("trackTimeMillis")
//    val trackTimeMillis: Int? = null,

    @field:SerializedName("trackName")
    @ColumnInfo(name = "trackName")
    val trackName: String? = null,

//    @field:SerializedName("discNumber")
//    val discNumber: Int? = null,

//    @field:SerializedName("artworkUrl30")
//    val artworkUrl30: String? = null,

//    @field:SerializedName("isStreamable")
//    val isStreamable: Boolean? = null,

//    @field:SerializedName("trackExplicitness")
//    val trackExplicitness: String? = null,

//    @field:SerializedName("trackNumber")
//    val trackNumber: Int? = null,

//    @field:SerializedName("kind")
//    val kind: String? = null,

    @field:SerializedName("trackId")
    @ColumnInfo(name = "trackId")
    val trackId: Int? = null,

//    @field:SerializedName("discCount")
//    val discCount: Int? = null,

    @field:SerializedName("trackPrice")
    @ColumnInfo(name = "trackPrice")
    val trackPrice: Double? = null,

//    @field:SerializedName("trackViewUrl")
//    val trackViewUrl: String? = null,

//    @field:SerializedName("trackCensoredName")
//    val trackCensoredName: String? = null,

    @field:SerializedName("longDescription")
    @ColumnInfo(name = "longDescription")
    val longDescription: String? = null,

//    @field:SerializedName("collectionHdPrice")
//    val collectionHdPrice: Double? = null,

//    @field:SerializedName("trackHdPrice")
//    val trackHdPrice: Double? = null,

//    @field:SerializedName("contentAdvisoryRating")
//    val contentAdvisoryRating: String? = null,

    @field:SerializedName("shortDescription")
    @ColumnInfo(name = "shortDescription")
    val shortDescription: String? = null,

    @ColumnInfo(name = "lastViewedTimeStamp")
    var lastViewedTimestamp: String? = null,

    @PrimaryKey(autoGenerate = false)
    var id: String = ""

//    @field:SerializedName("collectionArtistName")
//    val collectionArtistName: String? = null,

//    @field:SerializedName("collectionArtistId")
//    val collectionArtistId: Int? = null,

//    @field:SerializedName("trackHdRentalPrice")
//    val trackHdRentalPrice: Double? = null,

//    @field:SerializedName("collectionArtistViewUrl")
//    val collectionArtistViewUrl: String? = null,

//    @field:SerializedName("trackRentalPrice")
//    val trackRentalPrice: Double? = null,

//    @field:SerializedName("amgArtistId")
//    val amgArtistId: Int? = null,

//    @field:SerializedName("hasITunesExtras")
//    val hasITunesExtras: Boolean? = null,

//    @field:SerializedName("artworkUrl600")
//    val artworkUrl600: String? = null,

//    @field:SerializedName("feedUrl")
//    val feedUrl: String? = null,
) {
    val mediaPrice: String
        get() = "$currency ${(trackPrice ?: collectionPrice).toString()}"

    val mediaId: String
        get() = UUID.nameUUIDFromBytes("$trackId-$collectionId-$trackName".toByteArray()).toString()

    fun generateId(): MediaItem {
        this@MediaItem.id = this@MediaItem.mediaId
        return this@MediaItem
    }
}