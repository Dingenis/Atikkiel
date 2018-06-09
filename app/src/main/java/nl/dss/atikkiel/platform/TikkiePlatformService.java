package nl.dss.atikkiel.platform;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 18-5-2018
 * Description...
 */
public interface TikkiePlatformService {

    @POST("platforms")
    Call<Platform> createPlatform(@Body PlatformSetupConfig config);


    @GET("platforms")
    Call<List<Platform>> getPlatforms();
}
