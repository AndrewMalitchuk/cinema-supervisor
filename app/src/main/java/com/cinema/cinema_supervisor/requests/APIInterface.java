package com.cinema.cinema_supervisor.requests;


import com.cinema.cinema_supervisor.requests.entities.CinemaAPI;
import com.cinema.cinema_supervisor.requests.entities.TicketAPI;
import com.cinema.cinema_supervisor.requests.entities.TimelineAPI;
import com.cinema.cinema_supervisor.requests.entities.TokenAPI;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIInterface {


    public static final String api_film = "/api/v1/film";
    public static final String api_ticket = "/api/v1/ticket/";
    public static final String api_cinema = "/api/v1/cinema";
    public static final String api_timeline = "/api/v1/timeline";
    public static final String api_registration = "/api/v1/create/";
    public static final String api_user = "/api/v1/user/";
    public static final String api_token = "/api/token/";
    public static final String api_hall="/api/v1/hall/";
    public static final String api_staff="/api/v1/staff/";




    @GET(api_ticket)
    Call<TicketAPI> getTicketByCode(@Query("code") String code, @Header("Authorization") String authHeader);

    @GET(api_ticket)
    Observable<TicketAPI> getTicketByCodeRx(@Query("code") String code, @Header("Authorization") String authHeader);

    @GET(api_ticket)
    Call<List<TicketAPI>> getTicketByUserId(@Query("user_id") int user_id, @Header("Authorization") String authHeader);

    @GET(api_ticket)
    Observable<List<TicketAPI>> getTicketByUserIdRx(@Query("user_id") int user_id, @Header("Authorization") String authHeader);

    @POST(api_ticket)
    Call<TicketAPI> updateTicketById(@Query("id") int id, @Body TicketAPI ticket);


    @Multipart
    @POST(api_ticket)
    Call<TicketAPI> createTicket(@Part("place") RequestBody place,
                                                                     @Part("code") RequestBody code,
                                                                     @Part("status") RequestBody status,
                                                                     @Part("user") RequestBody user,
                                                                     @Part("timeline_id") RequestBody timeline_id,
                                                                     @Header("Authorization") String authHeader);


    @Multipart
    @PUT(api_ticket)
    Call<TicketAPI> updateTicket(@Query("id") int id,
                                                                     @Part("place") RequestBody place,
                                                                     @Part("code") RequestBody code,
                                                                     @Part("status") RequestBody status,
                                                                     @Part("cinema_id") RequestBody cinema_id,
                                                                     @Part("film_id") RequestBody film_id,
                                                                     @Part("user") RequestBody user,
                                                                     @Part("date") RequestBody date,
                                                                     @Header("Authorization") String authHeader);

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
    Call<List<TimelineAPI>> getTimeline();

    @GET(api_timeline)
    Call<List<TimelineAPI>> getTimelineByCinemaId(@Query("cinema_id") int cinema_id);

    @GET(api_timeline)
    Observable<TimelineAPI> getTimelineByIdRx(@Query("id") int id);

    @GET(api_timeline)
    Call<TimelineAPI> getTimelineById(@Query("id") int id);



    @GET(api_timeline)
    Call<List<TimelineAPI>> getTimelineByCinemaIdAndFilmId(@Query("cinema_id") int cinema_id, @Query("film_id") int film_id);

    @GET(api_timeline)
    Call<TimelineAPI> getTimelineByCinemaIdAndFilmIdAndDate(@Query("cinema_id") int cinema_id, @Query("film_id") int film_id, @Query("date") String date);

    @GET(api_timeline)
    Call<List<TimelineAPI>> getTimelineByDateAndCinemaId(@Query("date") String date, @Query("cinema_id") int cinema_id);

    @GET(api_timeline)
    Call<List<TimelineAPI>> getTimelineByDateAndCinemaIdAndFilmId(@Query("date") String date, @Query("cinema_id") int cinema_id, @Query("film_id") int film_id);




    @GET(api_cinema)
    Call<List<CinemaAPI>> getCinemas();

    @GET(api_cinema)
    Call<CinemaAPI> getCinemaById(@Query("id") int id);

    @GET(api_cinema)
    Call<CinemaAPI> getCinemaByName(@Query("name") String name);

    @GET(api_cinema)
    Call<List<CinemaAPI>> getCinemaByCity(@Query("city") int city);



    @Multipart
    @POST(api_registration)
    Call<com.cinema.client.requests.entities.RegistrationAPI> createNewUser(
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("username") RequestBody username);


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
    Call<com.cinema.client.requests.entities.UserAPI> getCurrentUser(@Header("Authorization") String authHeader);



    @GET(api_staff)
    Call<CinemaAPI> getJobByUserId(@Query("user_id") int user_id, @Header("Authorization") String authHeader);


}
