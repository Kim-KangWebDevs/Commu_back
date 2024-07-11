package com.Commu_back.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.Commu_back.vo.BoardVO;

@Mapper
public interface BoardMapper {
	
	// 카테고리 총 개수 조회
	// public int selectCategoryCount(@Param("board_id") String board_id) throws
	// Exception;

	// 카테고리 목록 조회
	// public ArrayList<Map<String, String>> selectCategoryList(@Param("board_id")
	// String board_id) throws Exception;

	// 카테고리 추가
//	public int insertCategory(@Param("board_id") String board_id, @Param("board_name") String board_name)
//			throws Exception;

	// 카테고리 삭제
//	public int deleteCategory(@Param("board_id") String board_id) throws Exception;

	// 카테고리 이름 조회
	public String selectCategoryName(@Param("board_id") String board_id) throws Exception;

	// 게시글 총 개수 조회
	public int selectBoardCount(@Param("board_id") String board_id, @Param("target") String target,
			@Param("keyword") String keyword) throws Exception;

	// 조회수 증가
	public int updateBoardViews(@Param("board_no") int board_no) throws Exception;
	
	// 게시글 목록 조회
	public ArrayList<Map<String, Object>> selectBoardList(Map<String, Object> board_map) throws Exception;

	// 게시글 조회
	public BoardVO selectBoard(@Param("board_no") int board_no) throws Exception;

	// 게시글 추가
	public int insertBoard(Map<String, Object> board_map) throws Exception;

	// 게시글 삭제
	public int deleteBoard(@Param("board_no") int board_no, @Param("user_id") String user_id) throws Exception;

}
