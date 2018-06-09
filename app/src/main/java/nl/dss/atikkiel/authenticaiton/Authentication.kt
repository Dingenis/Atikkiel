package nl.dss.atikkiel.authenticaiton

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.ArrayList

const val BASE_URL_AUTH = "https://api-sandbox.abnamro.com/v1/"

const val AUTH_TYPE_JWT_BEARER = "urn:ietf:params:oauth:client-assertion-type:jwt-bearer"

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 29-5-2018
 * Description...
 */
object Authentication  {

    private var apiKey : String = String()

    private var key : String = String()

    private var client : OkHttpClient = OkHttpClient()

    private var retrofit : Retrofit? = null

    var refresh : Boolean = true

    var token : AccesToken? = null

    fun setup(config : AuthenticationConfig) {
        apiKey = config.apiKey
        key = config.key

        this.client = config.client
                .build()

        this.retrofit = config.retrofit
                .client(client)
                .build()

        refresh = config.refresh
    }

    fun authenticate(callback : AuthenticationCallback) {
        val service = getService()
        val key = KeyGenerator.generatePrivateKey(key)
        val sendToken = TokenGenerator.generate(apiKey, key)
        service.authenticate(apiKey, sendToken, AUTH_TYPE_JWT_BEARER, "client_credentials", Scope.Tikkie.name.toLowerCase())
                .enqueue(object : Callback<AccesToken> {
                    override fun onResponse(call: Call<AccesToken>, response: Response<AccesToken>) {
                        if(response.isSuccessful) {
                            val token = response.body()
                            if(token != null) {
                                callback.onSuccess(token)
                            }
                            this@Authentication.token = token
                        } else {
                            val errorBody = response.errorBody()
                            if(errorBody != null) {
                                val json = errorBody.string()
                                val deferred = async {
                                    Gson().fromJson<ErrorResponse>(json, ErrorResponse::class.java)
                                }
                                launch {
                                    val result = deferred.await()
                                    callback.onFailure(result.errors)
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<AccesToken>, t: Throwable) {

                    }
                })
    }

    fun getService() : AuthenticationService {
        return retrofit?.create(AuthenticationService::class.java) ?: throw NullPointerException()
    }

}