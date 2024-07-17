package com.Commu_back.vo;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardVO {
	
	private int board_no; 
    private String board_category;
    private int user_no;
    private String board_title; 
    private String board_content; 
    private int board_views;
    private int board_good;
    private int board_bad;
    private String board_regdate; 
    private String board_updatedate; 

}
