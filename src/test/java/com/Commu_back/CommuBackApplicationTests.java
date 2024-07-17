package com.Commu_back;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.Commu_back.vo.BoardVO;
import com.Commu_back.vo.UserVO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@DisplayName("Commu_Back_Test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CommuBackApplicationTests {

	private static Logger log = LoggerFactory.getLogger(CommuBackApplication.class);
	
	@Autowired
	private MockMvc mvc;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public ObjectMapper objectMapper = new ObjectMapper();
	
	// 기본 세팅값 지정
	// 
	int USER_MAX = 240;
	int BOARD_MAX = 1234;
	
	// 2) 카테고리 2차원 배열 생성
	String[][] categories = { 
			{ "notice", "공지", "3" }, 
			{ "free", "자유", "3" }, 
			{ "game", "게임", "3" }, 
			{ "traval", "여행", "3" },
			{ "sprots", "스포츠", "3" },
			{ "cook", "요리", "3" },
			{ "car", "자동차", "3" },
			{ "movie", "영화", "3" },
			{ "war", "전쟁", "3" }, 
			{ "painting", "그림", "3" },
			{ "art", "예술", "3" },
			{ "animal", "동물", "3" }
	};
	
	List<UserVO> userlist = new ArrayList<>();
	
	
	
	@DisplayName("테스트 전 실행")
	@BeforeEach
	public void beforeEach() throws Exception {

	}

	@Test
	@DisplayName("유저 생성 메소드")
	public void generateUsers() throws Exception {
		
		log.info("회원 생성 시작");
		
		int USER_COUNT = 0;
		String requestBody;
		
		
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		// 1. 관리자 계정 생성
		userlist.add(UserVO.builder()
					.userId("admin")
					.userPw("admin")
					.userChr("관리자")
					.userEmail("admin@commuback.com")
					.build());
		
		// 2. 회원 계정 생성
		for (int i = 2; i < USER_MAX + 1; i++) {
			
			userlist.add(UserVO.builder()
					.userId("user" + i)
					.userPw("user" + i)
					.userChr(i + "번회원")
					.userEmail("user" + i + "@commuback.com")
					.build());
			
		}
		
		// 3. 회원 가입
		for (int i = 0; i < USER_MAX; i++) {
			
			// 3-1 builder로 생선된 객체를 JSON으로 변환
			requestBody = objectMapper.writeValueAsString(userlist.get(i));	
			
			// 3-2 계정 추가 
			mvc.perform(MockMvcRequestBuilders.post("/user/set")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(MockMvcResultMatchers.status().isOk());
			
			log.info("생성된 회원 정보 : " + requestBody);
			
			// 3-2 가입된 회원 수 증가
			USER_COUNT++;

		}
		
		log.info("생성된 회원 수 : " + USER_COUNT);

	}

	@Test
	@DisplayName("게시판 카테고리 생성 메소드")
	public void genetareCategories() throws Exception {
		
		log.info("게시판 카테고리 생성 시작");
		
		int BOARD_CATEGORY_COUNT = 0;
		MultiValueMap<String, String> categoryMap;
		
		
		// 1. 카테고리 생성
		for(int i = 0; i < categories.length; i++) {
			
			categoryMap = new LinkedMultiValueMap<>();
			categoryMap.add("id", categories[i][0]);
			categoryMap.add("name", categories[i][1]);
			categoryMap.add("auth", categories[i][2]);

			
			mvc.perform(MockMvcRequestBuilders.get("/board/addcategory.do")
				.params(categoryMap))
				.andExpect(MockMvcResultMatchers.status().isOk());
			
			log.info("생성된 게시판 카테고리 정보 : " + categoryMap.toString());
			
			// 2. 가입된 회원 수 증가
			BOARD_CATEGORY_COUNT++;
			
		}

		log.info("생성된 게시판 카테고리 수 : " + BOARD_CATEGORY_COUNT);
	}

//	@Test
//	@DisplayName("게시글 생성 메소드")
//	private void generateBoards(int value) throws Exception  {
//
//		log.info("게시글 생성 시작");
//		
//		int BOARD_COUNT = 0;
//		
//		// 게시글 생성
//		List<BoardVO> boardlist = new ArrayList<>();
//		String requestBody;
//		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//		
//		
//		int a = (int) (Math.random() * USER_MAX) + 1;
//		// 1. 게시글 리스트 생성
//		boardlist.add(BoardVO.builder()
//					.board_category(requestBody)
//					.user_no(BOARD_COUNT)
//					.board_title(requestBody)
//					.board_content(requestBody)
//					.build());
//		
//		// 3. 게시글 리스트 삽입
//		for (int i = 0; i < BOARD_MAX; i++) {
//			
//			// 3-1 builder로 생선된 객체를 JSON으로 변환
//			requestBody = objectMapper.writeValueAsString(boardlist.get(i));	
//			
//			// 3-2 게시글 추가
//			mvc.perform(MockMvcRequestBuilders.post("/user/set")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(requestBody))
//				.andExpect(MockMvcResultMatchers.status().isOk());
//			
//			log.info("생성된 회원 정보 : " + requestBody);
//			
//			// 3-2 가입된 회원 수 증가
//			BOARD_COUNT++;
//
//		}
		
//		for (int b = 1; b < BOARD_MAX; b++) {
//
//		// 랜덤 카테고리 선택, 0 과 카테고리 배열의 길이-1 사이의 난수 생성
//		random_category = (int) (Math.random() * (categories.length));
//
//		// 랜덤 유저 선택, 1과 [USER_MAX] + 1 사이의 난수 생성
//		random_user = (int) (Math.random() * USER_MAX) + 1;
//
//		// VO 생성 및
//		boardVO = new BoardVO(0, categories[random_category][0], random_user, b + "번째 게시글 제목", b + "번째 게시글의 내용입니다");
//
//		boardservice.addBoard(boardVO, "user" + random_user);
//
//		}	
//	}

	@DisplayName("테스트 후 실행")
	@AfterEach
	public void afterEach() {

		System.out.println("테스트 후 실행");
	}
}
