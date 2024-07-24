package com.Commu_back.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCategoryVO {

	private int boardCategoryNo;
	private String boardCategory;
	private String boardCategoryDesc;
	private String boardCategoryRegdate;

}
