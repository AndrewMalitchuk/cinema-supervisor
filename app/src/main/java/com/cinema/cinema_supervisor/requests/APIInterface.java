package com.cinema.cinema_supervisor.requests;


import com.cinema.cinema_supervisor.requests.entities.CinemaAPI;

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




    @GET(api_ticket)
    Call<com.cinema.client.requests.entities.TicketAPI> getTicketByCode(@Query("code") String code, @Header("Authorization") String authHeader);

    @GET(api_ticket)
    Call<List<com.cinema.client.requests.entities.TicketAPI>> getTicketByUserId(@Query("user_id") int user_id, @Header("Authorization") String authHeader);

    @GET(api_ticket)
    Observable<List<com.cinema.client.requests.entities.TicketAPI>> getTicketByUserIdRx(@Query("user_id") int user_id, @Header("Authorization") String authHeader);

    @POST(api_ticket)
    Call<com.cinema.client.requests.entities.TicketAPI> updateTicketById(@Query("id") int id, @Body com.cinema.client.requests.entities.TicketAPI ticket);


    @Multipart
    @POST(api_ticket)
    Call<com.cinema.client.requests.entities.TicketAPI> createTicket(@Part("place") RequestBody place,
                                                                     @Part("code") RequestBody code,
                                                                     @Part("status") RequestBody status,
                                                                     @Part("user") RequestBody user,
                                                                     @Part("timeline_id") RequestBody timeline_id,
                                                                     @Header("Authorization") String authHeader);


    @Multipart
    @PUT(api_ticket)
    Call<com.cinema.client.requests.entities.TicketAPI> updateTicket(@Query("id") int id,
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
    Call<com.cinema.client.requests.entities.TicketAPI> updateTicket(@Query("id") int id,
                                                                     @Part("place") RequestBody place,
                                                                     @Part("code") RequestBody code,
                                                                     @Part("status") RequestBody status,
                                                                     @Part("timeline_id") RequestBody timeline_id,
                                                                     @Part("user") RequestBody user,
                                                                     @Header("Authorization") String authHeader);


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
    Call<com.cinema.client.requests.entities.TokenAPI> refreshToken(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password);

    @Multipart
    @POST(api_token)
    Observable<com.cinema.client.requests.entities.TokenAPI> refreshTokenRx(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password);


    @GET(api_user)
    Call<com.cinema.client.requests.entities.UserAPI> getCurrentUser(@Header("Authorization") String authHeader);


}
