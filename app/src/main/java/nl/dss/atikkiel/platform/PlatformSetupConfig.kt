package nl.dss.atikkiel.platform

import com.google.gson.annotations.SerializedName

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 29-5-2018
 * Description...
 */
data class PlatformSetupConfig(@SerializedName("name")
                               var name : String,

                               @SerializedName("phoneNumber")
                               var phone : String,

                               @SerializedName("email")
                               var email : String,

                               @SerializedName("platformUsage")
                               var usage : PlatformUsage) {
    protected  constructor() : this("", "", "", PlatformUsage.PAYMENT_REQUEST_FOR_MYSELF)

    companion object {
        inline fun new(block: PlatformSetupConfig.() -> Unit) : PlatformSetupConfig {
            var setup = PlatformSetupConfig()
            block.invoke(setup)
            return setup
        }
    }
}