package nl.dss.testapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import nl.dss.atikkiel.Callback
import nl.dss.atikkiel.Tikkie
import nl.dss.atikkiel.TikkieConfig
import nl.dss.atikkiel.authenticaiton.ApiError
import nl.dss.atikkiel.platform.Platform
import nl.dss.atikkiel.platform.PlatformSetupConfig
import nl.dss.atikkiel.platform.PlatformUsage
import nl.dss.atikkiel.user.User
import nl.dss.atikkiel.user.UserSetupConfig


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val config = TikkieConfig(getString(R.string.abn_ambro_api_key), getString(R.string.abn_ambro_private_key))
        Tikkie.setup(config)

        val userConfig = UserSetupConfig("Bram Sinke", "0600000000", "NL09 INGB 0000 0000 00 ", "ING")
        Platform.get(object : Callback<List<Platform>> {
            override fun onSuccess(value: List<Platform>) {
                val platform = value[0]
                platform.user(userConfig, object : Callback<User> {
                        override fun onSuccess(value: User) {
                            Log.i("MainActivity", "User created with token id: " + value.token)
                        }

                        override fun onApiError(errors: List<ApiError>) {
                            for(error : ApiError in errors) {
                                Log.e("MainActivity", error.message)
                            }
                        }

                        override fun onException(throwable: Throwable) {
                            throw throwable
                        }
                    })
            }

            override fun onApiError(errors: List<ApiError>) {
                for(error : ApiError in errors) {
                    Log.e("MainActivity", error.message)
                }
            }

            override fun onException(throwable: Throwable) {
                throw throwable
            }
        })

        val platformConfig = PlatformSetupConfig("SmartScan", "0600000000", "abc@gmail.com", PlatformUsage.PAYMENT_REQUEST_FOR_MYSELF)
        Platform.create(platformConfig, object : Callback<Platform> {
            override fun onSuccess(value: Platform) {
                Log.i("MainActivity", value.name)
            }

            override fun onApiError(errors: List<ApiError>) {
                for(error : ApiError in errors) {
                    Log.e("MainActivity", error.message)
                }
            }

            override fun onException(throwable: Throwable) {
                throw throwable
            }

        })

    }
}
