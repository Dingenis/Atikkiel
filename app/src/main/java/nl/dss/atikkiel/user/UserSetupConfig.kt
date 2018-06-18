package nl.dss.atikkiel.user

import com.google.gson.annotations.SerializedName

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 29-5-2018
 * Description...
 */
data class UserSetupConfig(@SerializedName("name")
                           var name : String,

                           @SerializedName("phoneNumber")
                           var phone : String,

                           @SerializedName("iban")
                           var iban : String,

                           @SerializedName("bankAccountLabel")
                           var label : String) {

    /*
    * Constructs empty instance of UserSetupConfig
    *
    * */
    protected constructor() : this("", "", "", "")

    companion object {
        inline fun new(block: UserSetupConfig.() -> Unit) : UserSetupConfig {
            val config = UserSetupConfig()
            block.invoke(config)
            return config
        }
    }

}
