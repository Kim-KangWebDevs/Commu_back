package com.Commu_back.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

	private SecurityUtil() { }

    public static int getCurrentMemberId() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth == null || auth.getName() == null) {
            throw new RuntimeException("Security Context에 인증 정보가 없습니다.");
        }

        return Integer.parseInt(auth.getName());
    }
}
