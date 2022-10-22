package com.board.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class Post {
    private int id;
    private String title;
    private String content;
    private String author;
    private Date created_date;
    private Date modified_date;
}
