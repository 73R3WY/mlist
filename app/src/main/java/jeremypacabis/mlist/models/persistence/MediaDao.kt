package jeremypacabis.mlist.models.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import jeremypacabis.mlist.models.responses.MediaItem

/**
 * Created by jeremypacabis on Aug 18, 2019 @ 21:03.
 * @author Jeremy Patrick Pacabis <jeremypacabis@gmail.com>
 * jeremypacabis.mlist.models.persistence <mlist>
 */
@Dao
interface MediaDao {

    @Query("SELECT * FROM media_table")
    fun getAllLocalMedia(): Maybe<List<MediaItem?>>

    @Query("SELECT * FROM media_table WHERE id = :mediaId")
    fun getLocalMediaWithId(mediaId: String): Maybe<MediaItem?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLocalMedia(mediaItem: MediaItem)
}