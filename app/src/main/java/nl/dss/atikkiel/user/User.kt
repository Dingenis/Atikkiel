package nl.dss.atikkiel.user

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import nl.dss.atikkiel.Tikkie
import nl.dss.atikkiel.authenticaiton.ErrorResponse
import nl.dss.atikkiel.platform.Platform
import org.jetbrains.anko.coroutines.experimental.bg
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 19-5-2018
 * Description...
 */
data class User(@SerializedName("userToken") val token : String,

                @SerializedName("name")
                    val name : String,

                @SerializedName("status")
                    val status : UserStatus,

                @SerializedName("bankAccounts")
                    val accounts : List<BankAccount>) {

    companion object {

        fun create(platform : Platform, config: UserSetupConfig, callback : nl.dss.atikkiel.Callback<User>) {
            return create(platform.token, config, callback)
        }

        fun create(platform : String, config : UserSetupConfig, callback : nl.dss.atikkiel.Callback<User>) {
            val service = Tikkie.getUserService()
            val call = service.createUser(platform, config)
            val internalCallback = object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if(response.isSuccessful) {
                        val result = response.body()
                        if(result != null) {
                            callback.onSuccess(result)
                        }
                    } else {
                        val errorBody = response.errorBody()
                        if(errorBody != null) {
                            val json = errorBody.string()
                            val deferred = bg {
                                Gson().fromJson<ErrorResponse>(json, ErrorResponse::class.java)
                            }
                            async(UI) {
                                val result = deferred.await()
                                callback.onApiError(result.errors)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    callback.onException(t)
                }
            }
            Tikkie.Scheduler.schedule(call, internalCallback)
        }

        fun get(platform : String, callback : nl.dss.atikkiel.Callback<List<User>>) {
            val service = Tikkie.getUserService()
            val call = service.getUsers(platform)
            val internalCallback = object : Callback<List<User>> {
              override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                  if(response.isSuccessful) {
                      val result = response.body()
                      if(result != null) {
                          callback.onSuccess(result)
                      }
                  } else {
                      val errorBody = response.errorBody()
                      if(errorBody != null) {
                          val json = errorBody.string()
                          val deferred = bg {
                              Gson().fromJson<ErrorResponse>(json, ErrorResponse::class.java)
                          }
                          async(UI) {
                              val result = deferred.await()
                              callback.onApiError(result.errors)
                          }
                      }
                  }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    callback.onException(t)
                }
            }
            Tikkie.Scheduler.schedule(call, internalCallback)
        }
    }
}