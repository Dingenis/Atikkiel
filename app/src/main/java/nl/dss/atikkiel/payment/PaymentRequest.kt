package nl.dss.atikkiel.payment

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 19-5-2018
 * Description...
 */
data class PaymentRequest(@SerializedName("paymentRequestToken")
                          val token : String,

                          @SerializedName("amountInCents")
                          val cents : String,

                          @SerializedName("currency")
                          val currency : Currency,

                          @SerializedName("description")
                          val description : String,

                          @SerializedName("created")
                          val created : Date,

                          @SerializedName("expired")
                          val expired : Date,

                          @SerializedName("status")
                          val status : PaymentStatus,

                          @SerializedName("bankAccountYieldedTooFast")
                          val tooFast : Boolean,

                          @SerializedName("externalId")
                          val id : String,

                          @SerializedName("payments")
                          val payments : List<PaymentMadeInfo>)