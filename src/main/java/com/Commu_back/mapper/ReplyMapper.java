package com.Commu_back.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReplyMapper {

	// 댓글 총 개수 조회
	public int selectReplyCount(@Param("board_no") int board_no);

	// 댓글 리스트 조회
	public List<Map<String, Object>> selectReplyList(Map<String, Object> reply_map);

	// 댓글 추가
	public int insertReply(Map<String, Object> reply_map);

	// 댓글 삭제
	public int deleteReply(@Param("user_id") String user_id, @Param("reply_no") int reply_no);
}
