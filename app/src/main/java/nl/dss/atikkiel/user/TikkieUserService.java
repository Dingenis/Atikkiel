package nl.dss.atikkiel.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 18-5-2018
 * Description...
 */
public interface TikkieUserService {

    @POST("platforms/{platform}/users")
    Call<User> createUser(@Path("platform") String platform,
                          @Body UserSetupConfig config);

    @GET("platforms/{platform}/users")
    Call<List<User>> getUsers(@Path("platform") String platform);

}
