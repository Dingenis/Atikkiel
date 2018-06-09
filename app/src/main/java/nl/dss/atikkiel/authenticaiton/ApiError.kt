package nl.dss.atikkiel.authenticaiton

import com.google.gson.annotations.SerializedName

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 29-5-2018
 * Description...
 */
data class ApiError(@SerializedName("code") val code : ErrorCode,
                    @SerializedName("message") val message : String,
                    @SerializedName("category") val category : String,
                    @SerializedName("reference") val reference : String,
                    @SerializedName("status") val status : Int,
                    @SerializedName("traceId") val traceId : String) {

}

enum class ErrorCode {

    /**
     * Defines ERR_1001_001
     *
     * Malformed or incorrect Authorization header
     *
     */
    INCORRECT_HEADER
}