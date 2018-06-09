package nl.dss.atikkiel.authenticaiton;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 19-5-2018
 * Description...
 */
public interface AuthenticationService {

    @Headers({
            "Content-Type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("oauth/token")
    Call<AccesToken> authenticate(@Header("API-Key") String key,
                                  @Field("client_assertion") String token,
                                  @Field("client_assertion_type") String type,
                                  @Field("grant_type") String grant_type,
                                  @Field("scope") String scope);

}
