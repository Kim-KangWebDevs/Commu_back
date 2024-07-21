package com.Commu_back.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardMapper {

	// 카테고리 총 개수 조회
	public int selectCategoryCount(@Param("board_category_desc") String board_category_desc);

	// 카테고리 목록 조회
	public List<Map<String, Object>> selectCategoryList(@Param("board_category_desc") String board_category_desc,
			@Param("startRow") int startRow, @Param("endRow") int endRow);

	// 카테고리 추가
	public int insertCategory(@Param("board_category") String board_category,
			@Param("board_category_desc") String board_category_desc);

	// 카테고리 삭제
	public int deleteCategory(@Param("board_category") String board_category);

	// 카테고리 이름 조회
	public String selectCategoryDesc(@Param("board_category") String board_category);

	// 게시글 총 개수 조회
	public int selectBoardCount(Map<String, Object> board_map);

	// 게시글 목록 조회
	public List<Map<String, Object>> selectBoardList(Map<String, Object> board_map);

	// 게시글 조회
	public Map<String, Object> selectBoard(@Param("board_no") int board_no);

	// 게시글 추가 및 수정
	public int insertBoard(Map<String, Object> board_map);

	// 게시글 삭제
	public int deleteBoard(@Param("board_no") int board_no, @Param("user_id") String user_id);

	// 게시글 조회수 증가
	public int updateBoardViews(@Param("board_no") int board_no);

}
