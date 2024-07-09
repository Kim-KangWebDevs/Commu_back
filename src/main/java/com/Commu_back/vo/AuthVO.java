package com.Commu_back.vo;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthVO implements GrantedAuthority{
	
	private int roleNo;
	private String roleName;
	private String roleDesc;
	
	@Override
	public String getAuthority() {
		return this.roleName;
	}
	
}
