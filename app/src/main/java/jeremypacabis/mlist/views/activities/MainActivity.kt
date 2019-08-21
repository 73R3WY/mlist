package jeremypacabis.mlist.views.activities

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import jeremypacabis.mlist.MList
import jeremypacabis.mlist.R
import jeremypacabis.mlist.databinding.ActivityMainBinding
import jeremypacabis.mlist.models.responses.MediaItem
import jeremypacabis.mlist.utils.Logger
import jeremypacabis.mlist.utils.getLastViewedTimestamp
import jeremypacabis.mlist.utils.isNetworkConnected
import jeremypacabis.mlist.viewmodels.MediaListViewModel

/**
 * Created by jeremypacabis on Aug 16, 2019 @ 11:29.
 * @author Jeremy Patrick Pacabis <jeremypacabis@gmail.com>
 * jeremypacabis.mlist.views.activities <mlist>
 */
class MainActivity : BaseActivity() {

    private lateinit var mediaViewModel: MediaListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBindings()
        initializeData()
        initializeListeners()
    }

    override fun onResume() {
        super.onResume()
        mediaViewModel.lastVisitText.value = MList.lastVisitTimestampText
        val lastVisitedTimestamp = context.getLastViewedTimestamp()

        MList.lastVisitTimestampText = String.format(getString(R.string.view_last_visited_on), lastVisitedTimestamp)
        Logger.d { "MainActivity onResume..." }
    }

    private fun initializeBindings() {
        val activityBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        mediaViewModel = ViewModelProviders.of(this).get(MediaListViewModel::class.java)
        activityBinding.mediaViewModel = mediaViewModel
    }

    private fun initializeData() {
        mediaViewModel.isLoading.set(View.VISIBLE)
        mediaViewModel.fetchMediaList(context.isNetworkConnected())
        mediaViewModel.mediaLiveData.observe(
            this,
            Observer<List<MediaItem?>> { mediaList ->
                mediaViewModel.isLoading.set(View.GONE)
                if (mediaList.isEmpty()) {
                    mediaViewModel.showEmpty.set(View.VISIBLE)
                } else {
                    mediaViewModel.showEmpty.set(View.GONE)
                    mediaViewModel.mediaList = mediaList
                }
            }
        )
    }

    private fun initializeListeners() {
        mediaViewModel.mediaItemClicked.observe(
            this,
            Observer<MediaItem> { mediaItem ->
                Logger.e { "Clicked Item... Track Name: ${mediaItem.trackName}" }
            }
        )
    }
}