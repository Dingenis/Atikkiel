package nl.dss.atikkiel.user

import com.google.gson.annotations.SerializedName

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 19-5-2018
 * Description...
 */
data class BankAccount(@SerializedName("bankAccountToken")
                       val token : String,

                       @SerializedName("iban")
                       val IBAN : String,

                       @SerializedName("bankAccountLabel")
                       val label : String)