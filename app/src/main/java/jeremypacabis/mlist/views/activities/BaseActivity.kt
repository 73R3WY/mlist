package jeremypacabis.mlist.views.activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by jeremypacabis on Aug 16, 2019 @ 10:48.
 * @author Jeremy Patrick Pacabis <jeremypacabis@gmail.com>
 * jeremypacabis.mlist.views.activities <mlist>
 */
open class BaseActivity : AppCompatActivity() {

    protected var disposable: CompositeDisposable = CompositeDisposable()
    protected lateinit var context: Context
    protected lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}