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
	
	private Integer boardNo; 
    private String boardCategory;
    private Integer userNo;
    private String userId;
    private String boardTitle; 
    private String boardContent; 
    private Integer boardViews;
    private Integer boardGood;
    private Integer boardBad;
    private String boardRegdate; 
    private String boardUpdateate;

}
