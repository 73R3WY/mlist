package jeremypacabis.mlist.views

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import jeremypacabis.mlist.R


/**
 * Created by jeremypacabis on Aug 16, 2019 @ 18:06.
 * @author Jeremy Patrick Pacabis <jeremypacabis@gmail.com>
 * jeremypacabis.mlist.views.activities <mlist>
 */
object VewBindings {

    @JvmStatic
    @BindingAdapter("setAdapter")
    fun bindRecyclerViewAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImageToImageView(imageView: ImageView, imageUrl: String?) {
        if (imageUrl != null) {
            if (
                imageView.getTag(R.id.image_url) == null ||
                imageView.getTag(R.id.image_url) != imageUrl
            ) {
                imageView.setImageBitmap(null)
                imageView.setTag(R.id.image_url, imageUrl)

                val requestOptions = RequestOptions()
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_menu_gallery)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .priority(Priority.HIGH)
                    .centerCrop()
                Glide
                    .with(imageView)
                    .load(imageUrl)
                    .apply(requestOptions)
                    .into(imageView)
            }
        } else {
            imageView.setTag(R.id.image_url, null)
            imageView.setImageResource(android.R.drawable.ic_menu_gallery)
        }
    }
}