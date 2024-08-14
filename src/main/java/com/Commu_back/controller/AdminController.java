package com.Commu_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Commu_back.service.BoardService;
import com.Commu_back.service.UserService;
import com.Commu_back.vo.AuthVO;
import com.Commu_back.vo.UserVO;

@Controller
public class AdminController {
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = {"/main","/"}, method = RequestMethod.GET)
	public String loadAdminHome() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserVO user = (UserVO) auth.getPrincipal();// UsersVO 사용자 권한정보가 저장된 vo
		List<AuthVO> authVoList = userService.getUserAuth(user.getUserId());// 사용자 권한 조회
		user.setAuthList(authVoList);
		
		return "main";
	}
	

}
