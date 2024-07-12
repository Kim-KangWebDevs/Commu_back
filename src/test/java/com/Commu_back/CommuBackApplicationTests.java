package com.Commu_back;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Commu_back.service.BoardService;
import com.Commu_back.service.ReplyService;
import com.Commu_back.service.UserService;
import com.Commu_back.vo.BoardVO;

@SpringBootTest
class CommuBackApplicationTests {

	@Autowired
	private BoardService boardservice;

	@Autowired
	private ReplyService replyservice;

	@Autowired
	private UserService userservice;

	private BoardVO boardVO;

	@BeforeEach
	private void beforeEach() throws Exception {
		
		// 난수 생성 시 1 ~ 입력값+1 만큼의 유저가 생성됨
		int USER_MAX = 241;
		
		// 생성할 게시물 개수 - 1
		int BOARD_MAX = 2999;

		
		int random_category;
		int random_user;
		
		// 테스트를 시작 할 때 마다 실행
		// 게시판 카테고리 생성
		String[][] categories = { { "notice", "공지" }, { "free", "자유" }, { "game", "게임" }, { "traval", "여행" },
				{ "sprots", "스포츠" } };

		for (int i = 0; i < categories.length; i++) {

			boardservice.addCategory(categories[i][0], categories[i][1]);

		}

		// 게시글 생성

		// board_no(0), board_id, user_no, board_title, board_content

		for (int b = 1; b < BOARD_MAX; b++) {
			
			// 랜덤 카테고리 선택, 0 과 카테고리 배열의 길이-1 사이의 난수 생성 
			random_category = (int) (Math.random() * (categories.length));
			
			// 랜덤 유저 선택, 1과 [USER_MAX] + 1 사이의 난수 생성 
			random_user = (int) (Math.random() * USER_MAX) + 1;
			
			boardVO = new BoardVO(0, categories[random_category][0], random_user, b + "번째 게시글 제목", b + "번째 게시글의 내용입니다");

			boardservice.addBoard(boardVO, "user" + ((int) (Math.random() * USER_MAX)) + 1);
			
		}

	}

	@AfterEach
	private void afterEach() {
		// 테스트가 끝날 때 마다 삭제

	}

	@Test
	@DisplayName("1. 첫번째 테스트")
	private void firstTest() {

	}

}
