package com.Commu_back.util;

import lombok.Getter;

@Getter
public class DataType {

	private int value;
	private boolean status;
	
	// 문자열을 숫자열인지 확인하는 메소드
	public int isDigit(String value) {
		
		this.status = value != null && value.chars().allMatch(Character::isDigit) == true ? true : false;
		
		this.value = this.status == true ? Integer.parseInt(value): 1; 
		
		return this.value;
		
	}
}
