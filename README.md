# abnamro-tikkie
Android Tikkie Library

## Getting Started
1. Add the library to your project

```gradle
implementation 'nl.dss.abnamro:tikkie:0.0.1'
```

2. Initialize the library with your API key and your private key
```kotlin
        // Setup the authentication library
        val config = TikkieConfig(getString(R.string.abn_ambro_api_key), getString(R.string.abn_ambro_private_key))
        Tikkie.setup(config)
```

3. All done! You can now use the library to authenticate for the ABN AMRO APIs

## Platforms

### Add platform
```kotlin
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

```
### Get platforms
```kotlin
        Platform.get(object : Callback<List<Platform>> {
            override fun onSuccess(value: List<Platform>) {
                for(platform in value) {
                    Log.i("MainActivity", platform.name)
                }
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
```
## Users
### Add user
```kotlin
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
```
### Get users
```kotlin
platform.users(object : Callback<List<User>> {
                    override fun onSuccess(value: List<User>) {
                        for(user in value) {
                            Log.i("MainActivity", user.name)
                        }
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
```
