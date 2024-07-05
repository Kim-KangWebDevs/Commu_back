package com.Commu_back.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class BoardTypeVO {
	
    private String board_type; 
    private String board_name; 
    private String board_type_regdate; 
    
}
