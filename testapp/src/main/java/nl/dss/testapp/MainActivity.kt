package nl.dss.testapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import nl.dss.atikkiel.Callback
import nl.dss.atikkiel.Tikkie
import nl.dss.atikkiel.TikkieConfig
import nl.dss.atikkiel.authenticaiton.ApiError
import nl.dss.atikkiel.platform.Platform
import nl.dss.atikkiel.platform.PlatformSetupConfig
import nl.dss.atikkiel.platform.PlatformUsage
import nl.dss.atikkiel.user.User
import nl.dss.atikkiel.user.UserSetupConfig
import java.util.*


class MainActivity : AppCompatActivity() {

    /*
    * Logs a message to the TextView in the Activity
    *
    * @param string The string to log
    * */
    fun log(string: String) {
        var text = status_text.text as String
        text += string + "\n"
        status_text.text = text
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setup tikkie
        val config = TikkieConfig(getString(R.string.abn_ambro_api_key), getString(R.string.abn_ambro_private_key))
        Tikkie.setup(config)

        //Create user config
        val userConfig = UserSetupConfig.new {
            name = "Bram Sinke"
            phone = "0600000000"
            iban = "NL09 INGB 0000 0000 00"
            label = "ING"
        }

        //Get the platforms
        Platform.get(object : Callback<List<Platform>> {
            override fun onSuccess(value: List<Platform>) {
                for(platform in value) {

                    //Log the received platform
                    log("Platform received with token id: " + platform.token)

                    //Create user for that platform
                    platform.user(userConfig, object : Callback<User> {
                        override fun onSuccess(value: User) {
                            log("User created with token id: " + value.token)
                            Log.i("MainActivity", "User created with token id: " + value.token)
                        }

                        override fun onApiError(errors: List<ApiError>) {
                            //Log the errors
                            for(error : ApiError in errors) {
                                log(error.message)
                                Log.e("MainActivity", error.message)
                            }
                        }

                        override fun onException(throwable: Throwable) {
                            throw throwable
                        }
                    })
                }
            }

            override fun onApiError(errors: List<ApiError>) {
                //Log the errors
                for(error : ApiError in errors) {
                    log(error.message)
                    Log.e("MainActivity", error.message)
                }
            }

            override fun onException(throwable: Throwable) {
                throw throwable
            }
        })
/*
        //Setup config for platform
        val pConfig = PlatformSetupConfig.new {
            name = "MyPlatform"
            phone = "0000000000"
            email = "abc@gmail.com"
            usage = PlatformUsage.PAYMENT_REQUEST_FOR_MYSELF
        }

        //Create platform
        Platform.create(pConfig, object : Callback<Platform> {
            override fun onSuccess(value: Platform) {
                log("Platform created with id: " + value.token)
            }

            override fun onApiError(errors: List<ApiError>) {
                //Log errors
                for(error : ApiError in errors) {
                    log(error.message)
                    Log.e("MainActivity", error.message)
                }
            }

            override fun onException(throwable: Throwable) {
                throw throwable
            }
        })*/


    }
}
