package nl.dss.atikkiel.platform

import com.google.gson.annotations.SerializedName

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 29-5-2018
 * Description...
 */
data class PlatformSetupConfig(@SerializedName("name")
                               val name : String,

                               @SerializedName("phoneNumber")
                               val phone : String,

                               @SerializedName("email")
                               val email : String,

                               @SerializedName("platformUsage")
                               val usage : PlatformUsage)