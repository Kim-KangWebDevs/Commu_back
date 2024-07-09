package com.Commu_back.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.Commu_back.vo.ReplyVO;

public interface ReplyMapper {

	// 댓글 총 개수 조회
	public int selectReplyCount(@Param("board_type") String board_type, @Param("board_no") String board_no)
			throws Exception;

	// 댓글 리스트 조회
	public List<ReplyVO> selectReplyList(Map<String, Object> reply_map) throws Exception;

	// 댓글 추가
	public int insertReply(ReplyVO replyVO) throws Exception;

	// 댓글 삭제
	public int deleteReply(@Param("board_type") String board_type, @Param("board_no") String board_no,
			@Param("reply_no") int reply_no) throws Exception;
}
