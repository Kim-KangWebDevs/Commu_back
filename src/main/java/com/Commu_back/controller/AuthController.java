package com.Commu_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Commu_back.service.AuthService;
import com.Commu_back.service.UserService;
import com.Commu_back.vo.UserVO;

@Controller
public class AuthController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/join")
	public @ResponseBody int callJoin(@RequestBody UserVO usersVO) {
		return userService.setUser(usersVO);
	}

}
