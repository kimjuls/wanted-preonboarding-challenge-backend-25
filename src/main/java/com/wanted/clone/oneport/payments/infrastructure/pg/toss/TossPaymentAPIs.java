package com.wanted.clone.oneport.payments.infrastructure.pg.toss;

import com.wanted.clone.oneport.payments.infrastructure.pg.toss.request.TossApproveMessage;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.request.TossCancelMessage;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.TossApproveResponseMessage;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.TossCancelResponseMessage;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.TossSettlementsResponseMessage;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface TossPaymentAPIs {
    @POST("payments/confirm")
    Call<TossApproveResponseMessage> paymentFullfill(@Body TossApproveMessage requestMessage);

    @POST("payments/{paymentKey}/cancel")
    Call<TossCancelResponseMessage> paymentCancel(@Path("paymentKey") String paymentKey, @Body TossCancelMessage requestMessage);

    @GET("settlements")
    Call<List<TossSettlementsResponseMessage>> paymentSettlements(@Path("startDate") String startDate,
                                                                  @Path("endDate") String endDate,
                                                                  @Path("page") int page,
                                                                  @Path("size") int size);
}