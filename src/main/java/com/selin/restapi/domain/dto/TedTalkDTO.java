package com.selin.restapi.domain.dto;

import lombok.Data;

@Data
public class TedTalkDTO {
    private Long id;
    private String title;
    private String author;
    private String date;
    private Long views;
    private Long likes;
    private String link;
}
