package com.Commu_back.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenVO {
	private String grantType;
    private String accessToken;
    private Long tokenExpiresIn;
}
