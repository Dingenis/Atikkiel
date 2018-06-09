package nl.dss.atikkiel.payment

import com.google.gson.annotations.SerializedName

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 19-5-2018
 * Description...
 */
data class PaymentInfo(@SerializedName("paymentRequestUrl") val url : String,

                       @SerializedName("paymentRequestToken")
                       val token : String,

                       @SerializedName("externalId")
                       val id : String)