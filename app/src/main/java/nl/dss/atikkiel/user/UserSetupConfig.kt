package nl.dss.atikkiel.user

import com.google.gson.annotations.SerializedName

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 29-5-2018
 * Description...
 */
data class UserSetupConfig(@SerializedName("name")
                           val name : String,

                           @SerializedName("phoneNumber")
                           val phone : String,

                           @SerializedName("iban")
                           val iban : String,

                           @SerializedName("bankAccountLabel")
                           val label : String)
