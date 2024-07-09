package com.Commu_back.vo;

import lombok.Getter;

@Getter
public class PagingVO{

	// 페이징에 필요한 기본 파라미터 선언 
	private int firstPage, lastPage, curPage, startRow, endRow, endPage, total, amount;
	private boolean prev, next;

	// 선택 1 : 모든 게시물의 양만 입력되었을 경우 
	public PagingVO(int total) {
		
		this(total, 10, 1);
		
	}
	
	// 선택 2 : 모든 게시물의 양과 페이징 단위가 입력되었을 경우
	public PagingVO(int total, int amount) {
		
		this(total, amount, 1);
		
	}
	
	// 선택 3(기본) : 모든 게시물의 양과 페이징 단위, 그리고 현재 페이지가 입력되었을 경우 
	public PagingVO(int total, int amount, int curPage) {
		
		this.total = total;
		this.amount = amount;
		this.curPage = curPage;
		
        this.startRow = (this.curPage * this.amount) - this.amount + 1;
        
        this.endRow = this.startRow + this.amount - 1;
		
		this.lastPage = (int) Math.ceil(curPage / 10.0) * 10;
        
		this.firstPage = this.lastPage - 9;
		
		this.endPage = (int) Math.ceil((double)this.total / this.amount);	
		
        if(this.endRow > this.total) {
        	this.endRow = this.total;
        }
		
		if(this.endPage < this.lastPage) {
			this.lastPage = this.endPage;
		}
		
		this.prev = this.firstPage > 1;
		this.next = this.lastPage < endPage;
	}
	
}