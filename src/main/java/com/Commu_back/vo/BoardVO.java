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
	
	private Integer board_no; 
    private String board_category;
    private Integer user_no;
    private String user_id;
    private String board_title; 
    private String board_content; 
    private Integer board_views;
    private Integer board_good;
    private Integer board_bad;
    private String board_regdate; 
    private String board_updatedate;

}
