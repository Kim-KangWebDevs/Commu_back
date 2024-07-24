package com.Commu_back.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.Commu_back.vo.AuthVO;
import com.Commu_back.vo.UserVO;

@Mapper
public interface UserMapper {
	
	//1.전체 사용자 조회
	public List<UserVO> selectUsersAll();
	
	//2.사용자 번호 조회 
	public UserVO selectUserNo(@Param("userId") int userNo);
	
	//3.특정 사용자 조회
	public UserVO selectUserInfo(@Param("userId") String userId);
	
	//4.특정 사용자 권환 조회
	public List<AuthVO> selectUserAuth(@Param("userId") String userId);
	
	//5.사용자 권환 변경
	public int updateAuth(UserVO userVO);
	
	//6.사용자 추가
	public int insertUser(UserVO userVo);
	
	//7.사용자 수정
	public int updateUser(UserVO userVO);
	
	//8.사용자 제거
	public int deleteUser(@Param("userNo")int userNo);
	
	//9.사용자 검색
	public List<UserVO> selectSearchUser(String Search);
	
}
