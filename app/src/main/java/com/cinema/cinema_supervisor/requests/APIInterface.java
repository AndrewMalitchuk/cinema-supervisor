package com.cinema.cinema_supervisor.requests;

import com.cinema.cinema_supervisor.requests.entities.CinemaAPI;
import com.cinema.cinema_supervisor.requests.entities.TicketAPI;
import com.cinema.cinema_supervisor.requests.entities.TimelineAPI;
import com.cinema.cinema_supervisor.requests.entities.TokenAPI;
import com.cinema.cinema_supervisor.requests.entities.UserAPI;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIInterface {

    public static final String api_ticket = "/api/v1/ticket/";
    public static final String api_timeline = "/api/v1/timeline";
    public static final String api_user = "/api/v1/user/";
    public static final String api_token = "/api/token/";
    public static final String api_staff = "/api/v1/staff/";


    @GET(api_ticket)
    Observable<TicketAPI> getTicketByCodeRx(@Query("code") String code, @Header("Authorization") String authHeader);

    @Multipart
    @PUT(api_ticket)
    Call<TicketAPI> updateTicket(@Query("id") int id,
                                 @Part("place") RequestBody place,
                                 @Part("code") RequestBody code,
                                 @Part("status") RequestBody status,
                                 @Part("timeline_id") RequestBody timeline_id,
                                 @Part("user") RequestBody user,
                                 @Header("Authorization") String authHeader);

    @GET(api_timeline)
    Observable<TimelineAPI> getTimelineByIdRx(@Query("id") int id);

    @Multipart
    @POST(api_token)
    Call<TokenAPI> refreshToken(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password);

    @Multipart
    @POST(api_token)
    Observable<TokenAPI> refreshTokenRx(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password);

    @GET(api_user)
    Call<UserAPI> getCurrentUser(@Header("Authorization") String authHeader);

    @GET(api_staff)
    Call<CinemaAPI> getJobByUserId(@Query("user_id") int user_id, @Header("Authorization") String authHeader);

}
