package jeremypacabis.mlist.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import jeremypacabis.mlist.BR
import jeremypacabis.mlist.models.responses.MediaItem
import jeremypacabis.mlist.viewmodels.MediaListViewModel

/**
 * Created by jeremypacabis on Aug 16, 2019 @ 13:11.
 * @author Jeremy Patrick Pacabis <jeremypacabis@gmail.com>
 * jeremypacabis.mlist.views.adapters <mlist>
 */
class MediaItemAdapter(private val layoutId: Int, private val viewModel: MediaListViewModel) :
    RecyclerView.Adapter<MediaItemAdapter.MediaViewHolder>() {

    var mediaList: List<MediaItem?> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return MediaViewHolder(binding)
    }

    override fun getItemCount(): Int = mediaList.size

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.bind(viewModel, position)
    }

    override fun getItemViewType(position: Int): Int = layoutId

    inner class MediaViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: MediaListViewModel, position: Int) {
            binding.setVariable(BR.mediaViewModel, viewModel)
            binding.setVariable(BR.position, position)
            binding.executePendingBindings()
        }
    }
}