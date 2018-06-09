package nl.dss.atikkiel.authenticaiton

import com.google.gson.annotations.SerializedName

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 19-5-2018
 * Description...
 */
data class AccesToken(@SerializedName("access_token") val token : String,

                      @SerializedName("expires_in")
                                val expireDate : Long,

                      @SerializedName("scope")
                                val scope : String,

                      @SerializedName("token_type")
                                val type : TokenType) {
}

enum class TokenType {
    @SerializedName("Bearer")
    Bearer
}