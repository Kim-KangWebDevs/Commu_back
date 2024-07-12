package com.Commu_back.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class BoardVO {
	
	private int board_no; 
    private String board_id;
    private int user_no;
    private String board_title; 
    private String board_content; 
    private int board_views;
    private int board_good;
    private int board_bad;
    private String board_regdate; 
    private String board_updatedate; 
    
    public BoardVO() {
    	
    }
    
	public BoardVO(int board_no, String board_id, int user_no, String board_title, String board_content){
		
		super();
		
		this.board_no = board_no;
		this.board_id = board_id;
		this.user_no = user_no;
		this.board_title = board_title;
		this.board_content = board_content;
		
	}
}
