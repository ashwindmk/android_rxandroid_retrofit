package samples.android.ashwin.com.rxandroidretrofit

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Ashwin on 12/24/2018.
 */
object RetrofitHelper {
    fun createNetworkClient(baseUrl: String, debug: Boolean = false) =
        retrofitClient(baseUrl, httpClient(debug))

    private fun httpClient(debug: Boolean): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()
        if (debug) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return clientBuilder.build()
    }

    private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}
