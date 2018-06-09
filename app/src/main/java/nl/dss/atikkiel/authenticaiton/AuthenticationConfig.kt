package nl.dss.atikkiel.authenticaiton

import nl.dss.atikkiel.BASE_URL_TIKKIE
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 29-5-2018
 * Description...
 */
data class AuthenticationConfig(val apiKey : String, val key : String, val refresh : Boolean = true) {

    var client : OkHttpClient.Builder = OkHttpClient.Builder()

    var retrofit : Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL_AUTH)
            .addConverterFactory(GsonConverterFactory.create())
}