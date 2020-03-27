package com.cinema.client.requests.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class RegistrationAPI {

    @Getter
    @Setter
    @SerializedName("id")
    @Expose
    private Integer id;

    @Getter
    @Setter
    @SerializedName("email")
    @Expose
    private String email;

    @Getter
    @Setter
    @SerializedName("username")
    @Expose
    private String username;

    @Getter
    @Setter
    @SerializedName("is_staff")
    @Expose
    private Boolean isStaff;
}