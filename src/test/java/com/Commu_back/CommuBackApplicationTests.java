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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.Commu_back.vo.BoardVO;
import com.Commu_back.vo.ReplyVO;
import com.Commu_back.vo.UserVO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@DisplayName("Commu_Back_Test")
@AutoConfigureMockMvc
@EnableWebMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CommuBackApplicationTests {

	private final static Logger log = LoggerFactory.getLogger(CommuBackApplication.class);

	@Autowired
	private MockMvc mvc;

	@Autowired
	private WebApplicationContext ctx;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public ObjectMapper objectMapper = new ObjectMapper();

	// 기본 세팅값 지정
	//
	private final int USER_MAX = 240;
	private final int BOARD_MAX = 1234;
	private final int REPLY_MAX = 5678;
	private final int REREPLY_MAX = 3456;

	// 2) 카테고리 2차원 배열 생성
	String[][] categories = { { "notice", "공지", "3" }, { "free", "자유", "3" }, { "game", "게임", "3" },
			{ "traval", "여행", "3" }, { "sprots", "스포츠", "3" }, { "cook", "요리", "3" }, { "car", "자동차", "3" },
			{ "movie", "영화", "3" }, { "war", "전쟁", "3" }, { "painting", "그림", "3" }, { "art", "예술", "3" },
			{ "animal", "동물", "3" }, { "music", "음악", "3" } , { "celebrity", "연애인", "3" }  };

	List<UserVO> users = new ArrayList<>();

	@DisplayName("테스트 전 실행")
	@BeforeEach
	public void beforeEach() throws Exception {
		this.mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	@DisplayName("유저 생성 메소드")
	public void generateUsers() throws Exception {

		log.info("회원 생성 시작");

		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		int USER_COUNT = 0;
		String requestBody;
		UserVO userVO = new UserVO();

		// 1. 관리자 계정 생성
		userVO.setUserId("admin");
		userVO.setUserPw("admin");
		userVO.setUserChr("관리자");
		userVO.setUserEmail("관리자");
		users.add(userVO);

		// 2. 회원 계정 생성
		for (int i = 2; i < USER_MAX + 1; i++) {
			userVO = new UserVO();
			userVO.setUserId("user" + i);
			userVO.setUserPw("user" + i);
			userVO.setUserChr(i + "번회원");
			userVO.setUserEmail(i + "번회원");
			users.add(userVO);
		}

		// 3. 회원 가입
		for (int i = 0; i < users.size(); i++) {

			// 3-1 builder로 생선된 객체를 JSON으로 변환
			requestBody = objectMapper.writeValueAsString(users.get(i));
			// 3-2 계정 추가
			mvc.perform(MockMvcRequestBuilders.post("/test/adduser.do").contentType(MediaType.APPLICATION_JSON)
					.content(requestBody));

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
		for (int i = 0; i < categories.length; i++) {

			categoryMap = new LinkedMultiValueMap<>();
			categoryMap.add("id", categories[i][0]);
			categoryMap.add("name", categories[i][1]);

			mvc.perform(MockMvcRequestBuilders.post("/test/addcategory.do").params(categoryMap));

			log.info("생성된 게시판 카테고리 정보 : " + categoryMap.toString());

			// 2. 가입된 회원 수 증가
			BOARD_CATEGORY_COUNT++;

		}

		log.info("생성된 게시판 카테고리 수 : " + BOARD_CATEGORY_COUNT);
	}

	@Test
	@DisplayName("게시글 생성 메소드")
	public void generateBoards() throws Exception {

		log.info("게시글 생성 시작");

		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		List<BoardVO> boards = new ArrayList<>();

		int BOARD_COUNT = 0;
		String requestBody;
		BoardVO boardVO;

		// 1. 게시글 리스트 생성
		for (int i = 1; i < BOARD_MAX + 1; i++) {
			boardVO = new BoardVO();
			boardVO.setUser_id(("user" + ((int) (Math.random() * (USER_MAX - 1)) + 2)));
			boardVO.setBoard_category(categories[(int) (Math.random() * categories.length)][1]);
			boardVO.setBoard_title(i + "번째 게시글");
			boardVO.setBoard_content(i + "번째로 생성된 게시글의 내용입니다.\n이 글은 테스트용으로 작성된 " + i + "번째 글입니다. ");
			boards.add(boardVO);
		}

		// 3. 게시글 리스트 삽입
		for (int i = 0; i < boards.size(); i++) {

			// 3-1 builder로 생선된 객체를 JSON으로 변환
			requestBody = objectMapper.writeValueAsString(boards.get(i));
			// 3-2 게시글 추가
			mvc.perform(MockMvcRequestBuilders.post("/test/addboard.do").contentType(MediaType.APPLICATION_JSON)
					.content(requestBody));

			log.info("생성된 회원 정보 : " + requestBody);

			// 3-2 가입된 회원 수 증가
			BOARD_COUNT++;

		}

		log.info("생성된 게시글 수 : " + BOARD_COUNT);

	}

	@Test
	@DisplayName("댓글 생성 메소드")
	public void generateReplys() throws Exception {

		log.info("댓글 생성 시작");

		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		List<ReplyVO> replys = new ArrayList<>();

		int REPLY_COUNT = 0;
		String requestBody;
		ReplyVO replyVO;

		// 1. 댓글 리스트 생성
		for (int i = 0; i < REPLY_MAX; i++) {
			replyVO = new ReplyVO();
			replyVO.setBoard_no((int) (Math.random() * (BOARD_MAX - 1) + 1));
			replyVO.setUser_id(("user" + ((int) (Math.random() * (USER_MAX - 1)) + 2)));
			replyVO.setReply_content(i + "번째로 생성된 댓글의 내용입니다. ");
			replyVO.setReply_dept(0);
			replyVO.setReply_group(0);
			replys.add(replyVO);
		}

		// 2. 대댓글 리스트 생성
		for (int i = 0; i < REREPLY_MAX; i++) {
			replyVO = new ReplyVO();
			replyVO.setBoard_no((int) (Math.random() * (BOARD_MAX - 1) + 1));
			replyVO.setUser_id(("user" + ((int) (Math.random() * (USER_MAX - 1)) + 2)));
			replyVO.setReply_content((i + REPLY_MAX) + "번째로 생성된 댓글의 내용입니다.\n이 이것은 대댓글 입니다. ");
			replyVO.setReply_dept(1);
			replyVO.setReply_group((int) (Math.random() * REPLY_MAX - 1) + 1);
			replys.add(replyVO);
		}

		// 3. 게시글 리스트 삽입
		for (int i = 0; i < replys.size(); i++) {

			// 3-1 builder로 생선된 객체를 JSON으로 변환
			requestBody = objectMapper.writeValueAsString(replys.get(i));
			// 3-2 게시글 추가
			mvc.perform(MockMvcRequestBuilders.post("/test/addreply.do").contentType(MediaType.APPLICATION_JSON)
					.content(requestBody));

			log.info("생성된 댓글 정보 : " + requestBody);

			// 3-2 가입된 회원 수 증가
			REPLY_COUNT++;

		}

		log.info("생성된 게시글 수 : " + REPLY_COUNT);

	}

	@DisplayName("테스트 후 실행")
	@AfterEach
	public void afterEach() {

		System.out.println("테스트 후 실행");
	}

}
