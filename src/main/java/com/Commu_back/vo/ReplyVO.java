package com.Commu_back.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ReplyVO {

	private Integer board_no; 
    private Integer reply_no;
    private Integer user_no;
    private String user_id;
    private Integer reply_group;
    private Integer reply_dept;
    private String reply_content; 
    private String reply_regdate;

}
