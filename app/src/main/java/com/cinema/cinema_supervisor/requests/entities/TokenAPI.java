package com.cinema.client.requests.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class TokenAPI {

    @Getter
    @Setter
    @SerializedName("refresh")
    @Expose
    private String refresh;

    @Getter
    @Setter
    @SerializedName("access")
    @Expose
    private String access;
}