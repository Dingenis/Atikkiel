package nl.dss.atikkiel

import android.util.Log
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import nl.dss.atikkiel.authenticaiton.*
import nl.dss.atikkiel.payment.TikkiePaymentService
import nl.dss.atikkiel.platform.TikkiePlatformService
import nl.dss.atikkiel.user.TikkieUserService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit

const val BASE_URL_TIKKIE = "https://api-sandbox.abnamro.com/v1/tikkie/"

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 19-5-2018
 * Description...
 */
object Tikkie {

    private var apiKey : String = String()

    private var key : String = String()

    private var token : AccesToken? = null

    private var client : OkHttpClient = OkHttpClient()

    private var retrofit : Retrofit? = null

    fun setup(config : TikkieConfig) {
        apiKey = config.apiKey
        key = config.key

        client = config.client
                .addInterceptor { chain -> intercept(chain) }
                .build()

        retrofit = config.retrofit
                .client(client)
                .build()

        Authentication.setup(AuthenticationConfig(apiKey, key))
        Authentication.authenticate(Callback)
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 29-5-2018
     * Description...
     */
    object Callback : AuthenticationCallback {
        override fun onSuccess(token: AccesToken) {
            Tikkie.token = token
        }

        override fun onFailure(errors: List<ApiError>) {
            for(error : ApiError in errors) {
                Log.e("Tikkie", error.message)
            }
        }
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 29-5-2018
     * Description...
     */
    internal object Scheduler {
        fun<T> schedule(call : Call<T>, callback : retrofit2.Callback<T>) {
            if(Tikkie.token != null) {
                call.enqueue(callback)
            } else {
                launch {
                    while(Tikkie.token == null) {
                        delay(1000)
                    }
                    call.enqueue(callback)
                }
            }
        }
    }

    fun getPlatformService() : TikkiePlatformService {
        return retrofit?.create(TikkiePlatformService::class.java) ?: throw NullPointerException()
    }

    fun getPaymentService() : TikkiePaymentService {
        return retrofit?.create(TikkiePaymentService::class.java) ?: throw NullPointerException()
    }

    fun getUserService() : TikkieUserService {
        return retrofit?.create(TikkieUserService::class.java) ?: throw NullPointerException()
    }

    private fun intercept(chain: Interceptor.Chain) : Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.header("Authorization", "Bearer " + token?.token)
        builder.header("API-Key", apiKey)
        val response = chain.proceed(builder.build())
        if(!response.isSuccessful) {
            when(response.code()) {
                401 -> {
                    Authentication.authenticate(Callback)
                }
            }
        }
        return response
    }


}