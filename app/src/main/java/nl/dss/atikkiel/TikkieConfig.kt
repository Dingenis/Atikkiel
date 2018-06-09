package nl.dss.atikkiel

import nl.dss.atikkiel.authenticaiton.AuthenticationConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 29-5-2018
 * Description...
 */
data class TikkieConfig(val apiKey : String, val key : String) {

    var client : OkHttpClient.Builder = OkHttpClient.Builder()

    var retrofit : Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL_TIKKIE)
            .addConverterFactory(GsonConverterFactory.create())

}