package com.Commu_back.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import com.Commu_back.vo.AuthVO;
import com.Commu_back.vo.UserVO;

@Mapper
public interface UserMapper {
	
	//전체 사용자 조회
	public List<UserVO> selectUsersAll();
	
	//특정 사용자 조회
	public UserVO selectUserInfo(@Param("userId") String userId);
	
	//특정 사용자 권환 조회
	public List<AuthVO> selectUserAuth(@Param("userId") String userId);
	
	//사용자 권환 추가
	public int insertAuth(@Param("userNo")int userNo,@Param("roleNo")int roleNo);
	
	//사용자 추가
	public int insertUser(UserVO userVo);
	
	//사용자 수정
	public int updateUser(@RequestBody UserVO userVO);
	
	//사용자 제거
	public int deleteUser(@Param("userNo")int userNo);
	
	
	

}
