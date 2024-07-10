package com.Commu_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Commu_back.service.AuthService;
import com.Commu_back.service.UserService;
import com.Commu_back.vo.UserVO;
import com.github.pagehelper.PageInfo;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	//1.전체사용자
	@GetMapping("/user")
	public String CollUser(ModelMap map,
			@RequestParam("pageNum") int pageNum,
			@RequestParam("pageSize") int pageSize) {
		
		List<UserVO> list = userService.getSelectUsersAll(pageNum,pageSize);
		PageInfo<UserVO> pageInfo = new PageInfo<UserVO>(list);
		
		map.addAttribute("pageHelper", pageInfo);

		return "user";
	}
	
	//2.특정 유저 정보 가져오기
	@GetMapping("/user/{userNo}")
	@ResponseBody
	public UserVO callUserNo(@PathVariable("userNo")int userNo) {
		return userService.getSelectUserNo(userNo);
	}  
	
	//9.특정 유저 검색
	@GetMapping("/user/search")
	public String CollUserSearch(ModelMap map, @RequestParam("search") String search,
			@RequestParam("pageNum") int pageNum,
			@RequestParam("pageSize") int pageSize) {
		List<UserVO> list = userService.getSelectSearchUser(search, pageNum, pageSize);
		PageInfo<UserVO> pageInfo = new PageInfo<UserVO>(list);
		map.addAttribute("pageHelper", pageInfo);
		map.addAttribute("search", search);
		return "user";
	}
	
	//특정 권환 검색
	
	
	//유저추가
	
	
	//7.유저업데이트
	@CrossOrigin
	@ResponseBody
	@PatchMapping("/user/{userNo}")
	public int updateUser(@RequestBody UserVO userVo) {
		return userService.getUpdateUser(userVo);
	}
	
	//유저 삭제
	
	
}
