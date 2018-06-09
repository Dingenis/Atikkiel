package nl.dss.atikkiel.payment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 19-5-2018
 * Description...
 */
public interface TikkiePaymentService {

    @POST("platforms/{platform}/users/{user}/bankaccounts/{bank}/paymentrequests")
    Call<PaymentInfo> createPayment(@Path("platform") String platform,
                                    @Path("user") String user,
                                    @Path("bank") String bankAccount);

    Call<List<PaymentRequest>> getPayments(@Path("platform") String platform,
                                           @Path("user") String user);

    Call<List<PaymentRequest>> getPayments(@Path("platform") String platform,
                                           @Path("user") String user,
                                           @Query("offset") int offset);

    Call<List<PaymentRequest>> getPayments(@Path("platform") String platform,
                                           @Path("user") String user,
                                           @Query("offset") int offset,
                                           @Query("limit") int limit);

    Call<List<PaymentRequest>> getPayments(@Path("platform") String platform,
                                           @Path("user") String user,
                                           @Query("offset") int offset,
                                           @Query("limit") int limit,
                                           @Query("fromDate") String fromDate);

    Call<List<PaymentRequest>> getPayments(@Path("platform") String platform,
                                           @Path("user") String user,
                                           @Query("offset") int offset,
                                           @Query("limit") int limit,
                                           @Query("fromDate") String fromDate,
                                           @Query("toDate") String toDate);
}
