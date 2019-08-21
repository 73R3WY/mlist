package jeremypacabis.mlist.viewmodels

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import jeremypacabis.mlist.MList
import jeremypacabis.mlist.models.responses.MediaItem
import jeremypacabis.mlist.models.responses.MediaSearchResponse
import jeremypacabis.mlist.utils.Logger
import jeremypacabis.mlist.views.adapters.MediaItemAdapter


/**
 * Created by jeremypacabis on Aug 16, 2019 @ 15:42.
 * @author Jeremy Patrick Pacabis <jeremypacabis@gmail.com>
 * jeremypacabis.mlist.viewmodels <mlist>
 */
class MediaListViewModel : BaseViewModel() {

    var mediaItemAdapter: MediaItemAdapter = MediaItemAdapter(jeremypacabis.mlist.R.layout.list_item_media, this)
    val mediaLiveData: MutableLiveData<List<MediaItem?>> = MutableLiveData()
    val mediaItemClicked: MutableLiveData<MediaItem> = MutableLiveData()
    val lastVisitText: MutableLiveData<String> = MutableLiveData()
    var isLoading: ObservableInt = ObservableInt(View.GONE)
    var showEmpty: ObservableInt = ObservableInt(View.GONE)
    val showLastVisitText: ObservableInt = ObservableInt(View.GONE)
    var mediaList: List<MediaItem?> = ArrayList()
        set(value) {
            mediaItemAdapter.mediaList = value
            mediaItemAdapter.notifyDataSetChanged()
            field = value
        }

    fun getMediaAtIndex(position: Int) = mediaLiveData.value?.get(position)

    fun onItemClicked(position: Int) {
        mediaItemClicked.value = getMediaAtIndex(position)
    }

    fun fetchMediaList(online: Boolean) {
        when (online) {
            true -> fetchServerData()
            false -> fetchLocalData()
        }
    }

    private fun fetchServerData() {
        disposable.add(
            MList.apiClient.getMediaSearchResults()
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<MediaSearchResponse>() {
                    override fun onComplete() {
                    }

                    override fun onNext(t: MediaSearchResponse) {
                        mediaLiveData.value = t.results
                        t.results?.forEach { addMediaToLocalDb(it!!) }
                    }

                    override fun onError(e: Throwable) {
                        Logger.e { e.message.toString() }
                        fetchLocalData()
                    }
                })
        )
    }

    private fun fetchLocalData() {
        disposable.add(
            MList.mediaDatabase.mediaDao().getAllLocalMedia()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { mediaLiveData.value = it }
        )
    }

    fun addMediaToLocalDb(mediaItem: MediaItem) {
        Completable.fromAction {
            MList.mediaDatabase.mediaDao().addLocalMedia(mediaItem.generateId())
        }
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {}

                override fun onComplete() {
                    Logger.d { "Media with id ${mediaItem.id} has been added to the database." }
                }

                override fun onError(e: Throwable) {
                    Logger.e { "Error adding media ${mediaItem.id} to the database." }
                }
            })
    }
}