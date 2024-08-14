package com.Commu_back.controller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import com.Commu_back.service.AuthService;
import com.Commu_back.service.UserService;
import com.Commu_back.vo.AuthVO;
import com.Commu_back.vo.UserVO;
import com.github.pagehelper.PageInfo;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	//1.전체사용자
	public String CollUser(ModelMap map,
			@RequestParam("pageNum") int pageNum,
			@RequestParam("pageSize") int pageSize) {
		log.info("[ Call /user - GET ]");
		//출저https://velog.io/@gmtmoney2357/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0-Authentication-SecurityContext
		// security에서 로그인한 사람에 정보를 체크 후 불러옴
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserVO user = (UserVO) auth.getPrincipal();// UsersVO 사용자 권한정보가 저장된 vo
		List<AuthVO> authVoList = userService.getUserAuth(user.getUserId());// 사용자 권한 조회
		user.setAuthList(authVoList);
		

		List<UserVO> list = userService.getSelectUsersAll(pageNum,pageSize);
		PageInfo<UserVO> pageInfo = new PageInfo<UserVO>(list);
				
		map.addAttribute("pageHelper", pageInfo);
		map.addAttribute("userId", user.getUserId());
		map.addAttribute("userAuth", user.getAuthList());
		

		return "user";
	}
	
	//2.특정 유저 정보 가져오기
	@ResponseBody
	@GetMapping("/user/{userNo}")
	public UserVO callUserNo(@PathVariable("userNo")int userNo) {
		log.info("[ Call /user/" + userNo + " - GET ]");
		return userService.getSelectUserNo(userNo);
	}
	
	//7.유저업데이트
	@CrossOrigin
	@ResponseBody
	@PatchMapping("/user/update")
	public int updateUser(@RequestBody UserVO userVo) {		
		return userService.getUpdateUser(userVo);
	}
	
	//9.특정 유저 검색
	@GetMapping("/user/search")
	public String CollUserSearch(ModelMap map, @RequestParam("search") String search,
			@RequestParam("pageNum") int pageNum,
			@RequestParam("pageSize") int pageSize) {
		log.info("[ Call /user/" + search + " - GET ]");
		List<UserVO> list = userService.getSelectSearchUser(search, pageNum, pageSize);
		String addSearcg = search;
		map.addAttribute("search", addSearcg);
		
		PageInfo<UserVO> pageInfo = new PageInfo<UserVO>(list);
		map.addAttribute("pageHelper", pageInfo);
		
		return "user";
	}
	
	
		
	
	
	
}
