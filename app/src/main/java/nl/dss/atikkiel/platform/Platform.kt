package nl.dss.atikkiel.platform

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import nl.dss.atikkiel.Tikkie
import nl.dss.atikkiel.authenticaiton.ErrorResponse
import nl.dss.atikkiel.user.User
import nl.dss.atikkiel.user.UserSetupConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 27-5-2018
 * Description...
 */
class Platform protected constructor() {

    @SerializedName("name")
    val name : String = String()

    @SerializedName("platformToken")
    val token : String = String()

    @SerializedName("phoneNumber")
    val phone : String = String()

    @SerializedName("email")
    val email : String = String()

    @SerializedName("notificationUrl")
    val notificationUrl : String = String()

    @SerializedName("status")
    val status : PlatformStatus = PlatformStatus.ACTIVE

    @SerializedName("platformUsage")
    val usage : PlatformUsage = PlatformUsage.PAYMENT_REQUEST_FOR_MYSELF

    fun user(config : UserSetupConfig, callback : nl.dss.atikkiel.Callback<User>) {
        User.create(this, config, callback)
    }

    companion object {
        fun create(info : PlatformSetupConfig, callback: nl.dss.atikkiel.Callback<Platform>) {
            val service = Tikkie.getPlatformService()
            val call = service.createPlatform(info)
            val internalCallback = object : Callback<Platform> {
                override fun onResponse(call: Call<Platform>, response: Response<Platform>) {
                    if(response.isSuccessful) {
                        val result = response.body()
                        if(result != null) {
                            callback.onSuccess(result)
                        }
                    } else {
                        val errorBody = response.errorBody()
                        if(errorBody != null) {
                            val json = errorBody.string()
                            val deferred = async {
                                Gson().fromJson<ErrorResponse>(json, ErrorResponse::class.java)
                            }
                            launch {
                                val result = deferred.await()
                                callback.onApiError(result.errors)
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<Platform>, t: Throwable) {
                    callback.onException(t)
                }
            }
            Tikkie.Scheduler.schedule(call, internalCallback)
        }

        fun get(callback : nl.dss.atikkiel.Callback<List<Platform>>) {
            val service = Tikkie.getPlatformService()
            val call = service.platforms
            val internalCallback = object : Callback<List<Platform>> {
                override fun onResponse(call: Call<List<Platform>>, response: Response<List<Platform>>) {
                    if(response.isSuccessful) {
                        val platforms = response.body()
                        if(platforms != null) {
                            callback.onSuccess(platforms)
                        }
                    } else {
                        val errorBody = response.errorBody()
                        if(errorBody != null) {
                            val json = errorBody.string()
                            val deferred = async {
                                Gson().fromJson<ErrorResponse>(json, ErrorResponse::class.java)
                            }
                            launch {
                                val result = deferred.await()
                                callback.onApiError(result.errors)
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<List<Platform>>, t: Throwable) {
                    callback.onException(t)
                }
            }
            Tikkie.Scheduler.schedule(call, internalCallback)
        }
    }
}

enum class PlatformStatus {
    ACTIVE
}

enum class PlatformUsage {

    @SerializedName("PAYMENT_REQUEST_FOR_MYSELF")
    PAYMENT_REQUEST_FOR_MYSELF,

    @SerializedName("PAYMENT_REQUEST_FOR_OTHERS")
    PAYMENT_REQUEST_FOR_OTHERS
}