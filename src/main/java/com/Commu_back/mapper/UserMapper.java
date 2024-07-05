package com.Commu_back.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Commu_back.vo.UserVO;

public interface UserMapper {
	
	//특정 사용자 조회
	public UserVO selectUserInfo(@Param("userId") String userId);
	
	//전체 사용자 조회
	public List<UserVO> selectUsersAll();
	

}
