package jeremypacabis.mlist.viewmodels

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import jeremypacabis.mlist.MList
import jeremypacabis.mlist.models.responses.MediaItem
import jeremypacabis.mlist.utils.Logger

/**
 * Created by jeremypacabis on Aug 21, 2019 @ 20:49.
 * @author Jeremy Patrick Pacabis <jeremypacabis@gmail.com>
 * jeremypacabis.mlist.viewmodels <mlist>
 */
class MediaDetailViewModel : BaseViewModel() {

    var mediaItemData: MutableLiveData<MediaItem?> = MutableLiveData()
    val showLastVisitText: ObservableInt = ObservableInt(View.GONE)

    fun fetchMediaData(mediaId: String) {
        disposable.add(
            MList.mediaDatabase.mediaDao().getLocalMediaWithId(mediaId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mediaItemData.value = it
                }
        )
    }

    fun updateLastVisitTextForMedia(lastVisitText: String) {
        val mediaItem = mediaItemData.value
        mediaItem!!.lastViewedTimestamp = lastVisitText
        Completable.fromAction {
            MList.mediaDatabase.mediaDao().updateLastVisitText(mediaItem.mediaId, lastVisitText)
        }
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {}

                override fun onComplete() {
                    Logger.d { "Media with id ${mediaItem.id} has been updated with last viewed timestamp $lastVisitText." }
                }

                override fun onError(e: Throwable) {
                    Logger.e { "Error updating media ${mediaItem.id} with last viewed timestamp $lastVisitText." }
                }
            })
    }
}