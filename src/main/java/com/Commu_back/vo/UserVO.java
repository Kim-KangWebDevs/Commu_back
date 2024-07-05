package com.Commu_back.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {
	private int userNo;
	private String userId;
	private String userPw;
	private String userChr;	//유저닉네임
	private String userEmail;
	private String userRegdate; //유저 가입날짜
	private String fileName;
	
	
	
}
