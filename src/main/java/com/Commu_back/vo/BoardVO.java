package com.Commu_back.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class BoardVO {
	
    private String board_id; 
    private int board_no; 
    private String board_title; 
    private int user_no;
    private String board_content; 
    private int board_views;
    private int board_good;
    private int board_bad;
    private String board_regdate; 
    private String board_updatedate; 
    
}
