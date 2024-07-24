package com.Commu_back.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class FileVO {

	private int fileNo;
	private int boardNo;
	private String fileSrc;
	private String fileDesc;
	private String fileRegdate;
	
}
