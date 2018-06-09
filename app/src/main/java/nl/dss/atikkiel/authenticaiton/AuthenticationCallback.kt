package nl.dss.atikkiel.authenticaiton

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 29-5-2018
 * Description...
 */
interface AuthenticationCallback {

    fun onSuccess(token : AccesToken)

    fun onFailure(errors: List<ApiError>)

}