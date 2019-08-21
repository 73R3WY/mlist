package jeremypacabis.mlist.models.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import jeremypacabis.mlist.models.responses.MediaItem
import jeremypacabis.mlist.utils.MEDIA_DB

/**
 * Created by jeremypacabis on Aug 18, 2019 @ 21:08.
 * @author Jeremy Patrick Pacabis <jeremypacabis@gmail.com>
 * jeremypacabis.mlist.models.persistence <mlist>
 */
@Database(entities = [MediaItem::class], version = 1)
abstract class MediaDb : RoomDatabase() {

    abstract fun mediaDao(): MediaDao

    companion object {
        private var instance: MediaDb? = null

        fun getInstance(context: Context): MediaDb? {
            if (instance == null) {
                synchronized(MediaDb::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MediaDb::class.java,
                        MEDIA_DB
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return instance
        }
    }
}