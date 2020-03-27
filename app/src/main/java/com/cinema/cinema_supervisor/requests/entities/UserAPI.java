package com.cinema.client.requests.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class UserAPI {

    @Getter
    @Setter
    @SerializedName("id")
    @Expose
    private Integer id;

    @Getter
    @Setter
    @SerializedName("username")
    @Expose
    private String username;

    @Getter
    @Setter
    @SerializedName("email")
    @Expose
    private String email;

    @Getter
    @Setter
    @SerializedName("first_name")
    @Expose
    private String firstName;

    @Getter
    @Setter
    @SerializedName("last_name")
    @Expose
    private String lastName;

    @Getter
    @Setter
    @SerializedName("is_staff")
    @Expose
    private Boolean isStaff;

    @Getter
    @Setter
    @SerializedName("is_active")
    @Expose
    private Boolean isActive;

}
