package com.cinema.cinema_supervisor.requests.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class TicketAPI {

    @Getter
    @Setter
    @SerializedName("id")
    @Expose
    private Integer id;

    @Getter
    @Setter
    @SerializedName("place")
    @Expose
    private String place;

    @Getter
    @Setter
    @SerializedName("code")
    @Expose
    private String code;

    @Getter
    @Setter
    @SerializedName("status")
    @Expose
    private Integer status;

    @Getter
    @Setter
    @SerializedName("user")
    @Expose
    private Integer user;

    @Getter
    @Setter
    @SerializedName("timeline_id")
    @Expose
    private Integer timeline_id;

}