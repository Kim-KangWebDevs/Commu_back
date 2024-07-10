package com.Commu_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	
	//3.특정유저조회
	public UserVO getSelectUserInfo(String userId) {
		return userMapper.selectUserInfo(userId);
	}
	
	//4.특정 권환 조회
	public List<AuthVO> getSelectUserAuth(String userId){
		return userMapper.selectUserAuth(userId);
	}
	
	//5.사용자 권환 추가
	public int getInsertAuth(int userNo, int roleNo) {
		return userMapper.insertAuth(userNo, roleNo);
	} 
	
	//6.유저 추가
	public int setUser(UserVO userVo) {
		String pw = userVo.getUserPw();
		pw = passwordEncoder.encode(pw);
		userVo.setUserPw(pw);
		return userMapper.insertUser(userVo);
	}
		
	//7.유저수정
	public int getUpdateUser(UserVO userVO) {
		String pw = userVO.getUserPw();
		pw = passwordEncoder.encode(pw);
		userVO.setUserPw(pw);
		return userMapper.updateUser(userVO);
	}
	
	//8.유저삭제
	public int getDeleteUser(int userNo) {
		return userMapper.deleteUser(userNo);
		
	}
	
	//9.유저검색
	public List<UserVO> getSelectSearchUser(String search,int pageNum,int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return userMapper.selectSearchUser(search);
	}
	

}
