package samples.android.ashwin.com.rxandroidretrofit

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Ashwin on 12/24/2018.
 */
interface RetrofitService {
    @GET("profile.json")
    fun getProfile(): Observable<Profile>
}
