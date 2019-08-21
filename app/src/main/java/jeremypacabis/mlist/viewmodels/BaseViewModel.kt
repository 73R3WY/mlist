package jeremypacabis.mlist.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import jeremypacabis.mlist.MList

/**
 * Created by jeremypacabis on Aug 16, 2019 @ 16:24.
 * @author Jeremy Patrick Pacabis <jeremypacabis@gmail.com>
 * jeremypacabis.mlist.viewmodels <mlist>
 */
open class BaseViewModel : ViewModel() {

    protected val apiClient = MList.apiClient
    protected var disposable: CompositeDisposable = CompositeDisposable()
}