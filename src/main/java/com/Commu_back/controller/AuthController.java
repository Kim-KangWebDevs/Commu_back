package com.Commu_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.extern.slf4j.Slf4j;

import com.Commu_back.service.AuthService;
import com.Commu_back.vo.UserVO;

@Slf4j
@Controller
public class AuthController {
	@Autowired
	private AuthService authService ;
		
	@GetMapping("/login")
	public String callLoginPage() {
		log.info("[ Call /login - GET ]");
		return "login";
	}
	
	@PostMapping("/join")
	public @ResponseBody int callJoin(@RequestBody UserVO usersVO) {
		return authService.setUser(usersVO);
	}

}
