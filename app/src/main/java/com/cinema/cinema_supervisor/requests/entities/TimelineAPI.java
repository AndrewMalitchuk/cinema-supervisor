package com.cinema.cinema_supervisor.requests.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class TimelineAPI {

    @Getter
    @Setter
    @SerializedName("id")
    @Expose
    private Integer id;

//    @Getter
//    @Setter
//    @SerializedName("time")
//    @Expose
//    private String time;
//
//    @Getter
//    @Setter
//    @SerializedName("date")
//    @Expose
//    private String date;


    @Getter
    @Setter
    @SerializedName("datetime")
    @Expose
    private String datetime;

    @Getter
    @Setter
    @SerializedName("cinema_id")
    @Expose
    private Integer cinemaId;

    @Getter
    @Setter
    @SerializedName("film_id")
    @Expose
    private Integer filmId;

    @Getter
    @Setter
    @SerializedName("price")
    @Expose
    private Double price;
}

