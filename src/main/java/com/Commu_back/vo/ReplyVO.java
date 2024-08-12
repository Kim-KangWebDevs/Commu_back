package com.Commu_back.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ReplyVO {

	private Integer boardNo; 
    private Integer replyNo;
    private Integer userNo;
    private String userId;
    private Integer replyGroup;
    private Integer replyDept;
    private String replyContent; 
    private String replyRegdate;
    private String replyUpdateate;

}
