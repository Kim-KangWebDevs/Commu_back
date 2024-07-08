package com.Commu_back.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Commu_back.mapper.UserMapper;
import com.Commu_back.vo.AuthVO;
import com.Commu_back.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserMapper userMapper;
	
	//유저 추가
	public int setUser(UserVO userVo) {
		String pw = userVo.getUserPw();
		pw = passwordEncoder.encode(pw);
		userVo.setUserPw(pw);
		return userMapper.insertUser(userVo);
	}
	//전체조회
	public List<UserVO> getselectUsersAll(){
		return userMapper.selectUsersAll();
	}
	//특정사용자조회
	public List<AuthVO> selectUserAuth(String userId){
		return userMapper.selectUserAuth(userId);
	}
	
	//특정유저조회
	public UserVO getselectUserInfo(String userId) {
		return userMapper.selectUserInfo(userId);
	}
	
	//유저수정
	public int getUpdateUser(UserVO userVO) {
		String pw = userVO.getUserPw();
		pw = passwordEncoder.encode(pw);
		userVO.setUserPw(pw);
		return userMapper.updateUser(userVO);
	}
	
	//유저삭제
	public int getdeleteUser(int userNo) {
		return userMapper.deleteUser(userNo);
		
	}
	

}
