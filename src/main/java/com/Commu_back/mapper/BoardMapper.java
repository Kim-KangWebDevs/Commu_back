package com.Commu_back.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardMapper {

	// 카테고리 총 개수 조회
	public int selectCategoryCount(@Param("board_category_desc") String board_category_desc);

	// 카테고리 목록 조회
	public ArrayList<Map<String, String>> selectCategoryList(@Param("board_category_desc") String board_category_desc);

	// 카테고리 추가
	public int insertCategory(@Param("board_category") String board_category,
			@Param("board_category_desc") String board_category_desc, @Param("board_role_no") int board_role_no);

	// 카테고리 삭제
	public int deleteCategory(@Param("board_category") String board_category);

	// 카테고리 접근 권한 수정 
	public int updateCategoryAuth(@Param("board_category") String board_category, @Param("board_role_no") int board_role_no);
	
	// 카테고리 이름 조회
	public String selectCategoryDesc(@Param("board_category") String board_category);

	// 게시글 총 개수 조회
	public int selectBoardCount(@Param("board_category") String board_category, @Param("target") String target,
			@Param("keyword") String keyword);

	// 조회수 증가
	public int updateBoardViews(@Param("board_no") int board_no);

	// 게시글 목록 조회
	public ArrayList<Map<String, Object>> selectBoardList(Map<String, Object> board_map);

	// 게시글 조회
	public Map<String, Object> selectBoard(@Param("board_no") int board_no);

	// 게시글 추가
	public int insertBoard(Map<String, Object> board_map);

	// 게시글 삭제
	public int deleteBoard(@Param("board_no") int board_no, @Param("user_id") String user_id);

}
