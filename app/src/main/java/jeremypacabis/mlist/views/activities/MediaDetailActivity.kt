package jeremypacabis.mlist.views.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import jeremypacabis.mlist.R
import jeremypacabis.mlist.databinding.ActivityMediaDetailBinding
import jeremypacabis.mlist.utils.INTENT_KEY_MEDIA_ID
import jeremypacabis.mlist.utils.Logger
import jeremypacabis.mlist.utils.getLastViewedTimestamp
import jeremypacabis.mlist.viewmodels.MediaDetailViewModel

/**
 * Created by jeremypacabis on Aug 21, 2019 @ 20:42.
 * @author Jeremy Patrick Pacabis <jeremypacabis@gmail.com>
 * jeremypacabis.mlist.views.activities <mlist>
 */
class MediaDetailActivity : BaseActivity() {

    private lateinit var mediaDetailViewModel: MediaDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initializeBindings()
        initializeData()
    }

    override fun onResume() {
        super.onResume()
        Logger.d { "MediaDetailActivity onResume..." }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initializeBindings() {
        val activityBinding =
            DataBindingUtil.setContentView<ActivityMediaDetailBinding>(this, R.layout.activity_media_detail)
        mediaDetailViewModel = ViewModelProviders.of(this).get(MediaDetailViewModel::class.java)
        activityBinding.mediaDetailViewModel = mediaDetailViewModel
        activityBinding.lifecycleOwner = this
    }

    private fun initializeData() {
        mediaDetailViewModel.fetchMediaData(intent.getStringExtra(INTENT_KEY_MEDIA_ID))
        mediaDetailViewModel.mediaItemData.observe(
            this,
            Observer { mediaItem ->
                supportActionBar?.title = mediaItem!!.trackName
                mediaDetailViewModel.showLastVisitText.set(View.GONE.takeIf {
                    mediaItem.lastViewedTimestamp.isNullOrBlank()
                } ?: View.VISIBLE)
                mediaDetailViewModel.updateLastVisitTextForMedia(context.getLastViewedTimestamp())
            }
        )
    }
}