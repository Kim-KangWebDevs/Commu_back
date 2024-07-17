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

	private int board_category_no;
	private String board_category;
	private String board_category_desc;
	private String board_category_regdate;

}
