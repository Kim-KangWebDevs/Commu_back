package com.Commu_back.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthVo {
	
	private int roleNo;
	private String roleName;
	private String roleDesc;
	
	public int getroleNo() {
		return this.roleNo;
	}

}
