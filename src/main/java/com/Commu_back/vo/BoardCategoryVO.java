package com.Commu_back.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class BoardCategoryVO {
	
    private String board_id; 
    private String board_name; 
    private String board_category_regdate; 
    
}
