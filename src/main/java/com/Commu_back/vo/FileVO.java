package com.Commu_back.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class FileVO {

	private int file_no;
	private int board_no;
	private String board_type;
	private String file_name;
	private String file_regdate;
	
}
