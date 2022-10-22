package com.board.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostRequest {
    String author;
    String title;
    String content;
}
