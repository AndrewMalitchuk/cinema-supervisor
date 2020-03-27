package com.cinema.client.requests.entities;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class CreateUserResponse {

    @Getter
    @Setter
    private List<String> username = null;


}
