package nl.dss.atikkiel

import nl.dss.atikkiel.authenticaiton.ApiError
import nl.dss.atikkiel.platform.Platform

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 29-5-2018
 * Description...
 */
interface Callback<T> {

    fun onSuccess(value : T)

    fun onApiError(errors : List<ApiError>)

    fun onException(throwable : Throwable)
}