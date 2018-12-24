package samples.android.ashwin.com.rxandroidretrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        compositeDisposable = CompositeDisposable()
    }

    fun loadProfile(v: View) {
        text_view.text = getString(R.string.loading)
        val retrofit = RetrofitHelper.createNetworkClient(Constants.URL, BuildConfig.DEBUG)
        val service = retrofit.create(RetrofitService::class.java)
        compositeDisposable?.add(service.getProfile()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError)
        )
    }

    private fun handleResponse(profile: Profile) {
        text_view.text = profile.toString()
    }

    private fun handleError(t: Throwable) {
        if (BuildConfig.DEBUG) {
            Log.e(Constants.TAG, "Exception while fetching profile", t)
        }
        text_view.text = getString(R.string.error)
    }

    override fun onDestroy() {
        compositeDisposable?.clear()
        super.onDestroy()
    }
}
