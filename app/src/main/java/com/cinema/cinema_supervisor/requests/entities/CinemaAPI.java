package com.cinema.cinema_supervisor.requests.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class CinemaAPI {

    @Getter
    @Setter
    @SerializedName("id")
    @Expose
    private Integer id;

    @Getter
    @Setter
    @SerializedName("name")
    @Expose
    private String name;

    @Getter
    @Setter
    @SerializedName("address")
    @Expose
    private String address;

    @Getter
    @Setter
    @SerializedName("city")
    @Expose
    private int city;

    @Getter
    @Setter
    @SerializedName("telephone")
    @Expose
    private String telephone;

    @Getter
    @Setter
    @SerializedName("geo_lat")
    @Expose
    private Double geoLat;

    @Getter
    @Setter
    @SerializedName("geo_lon")
    @Expose
    private Double geoLon;

    @Getter
    @Setter
    @SerializedName("pic_url")
    @Expose
    private String picUrl;

}
