package com.Commu_back.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Commu_back.mapper.UserMapper;
import com.Commu_back.vo.AuthVO;
import com.Commu_back.vo.UserVO;
import com.github.pagehelper.PageHelper;

@Service
public class UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserMapper userMapper;
		
	//1.전체조회
	public List<UserVO> getSelectUsersAll(int pageNum,int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return userMapper.selectUsersAll();
	}
	
	//2.사용자번호조회
	public UserVO getSelectUserNo(int userNo){
		return userMapper.selectUserNo(userNo);
	}
		
	//4.특정 권환 조회
	public List<AuthVO> getUserAuth(String userId){
		return userMapper.selectUserAuth(userId);
	}
		
	//7.유저수정
	@Transactional(rollbackFor = Exception.class)
	public int getUpdateUser(UserVO userVO) {
		String pw = userVO.getUserPw();
		pw = passwordEncoder.encode(pw);
		userVO.setUserPw(pw);
		int row = userMapper.updateUser(userVO);
		return row;
	}
	
	//8.유저삭제
	@Transactional(rollbackFor = Exception.class)
	public int getDeleteUser(int userNo) {
		return userMapper.deleteUser(userNo);
		
	}
	
	//9.유저검색
	public List<UserVO> getSelectSearchUser(String search,int pageNum,int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return userMapper.selectSearchUser(search);
	}

	
}
