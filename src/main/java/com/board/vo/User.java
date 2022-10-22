package com.board.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
}
