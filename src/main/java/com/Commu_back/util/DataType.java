package com.Commu_back.util;

public class DataType {

	private int value;

	// 문자열을 숫자열인지 확인하는 메소드
	public int isDigit(String value) {
		
		this.value = value != null && value != ""
				? value.chars().allMatch(Character::isDigit) == true ? Integer.parseInt(value) : 1
				: 1;
		
		return this.value;
		
	}
}
