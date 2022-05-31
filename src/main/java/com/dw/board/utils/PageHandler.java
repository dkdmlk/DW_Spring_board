package com.dw.board.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageHandler {
	private int total; //전체 게시물 수
	private int pageNum; //현재페이지
	private int pageSize; //1페이지에 몇개 개시물 표시할지
	private int startpage; // 현재 블록 첫 페이지
	private int endPage; // 현재 블록 마지막 페이지
	private boolean hasPreviousPage;//이전 버튼 유무
	private boolean hasNextPage;//다음 버튼 유무
	private int nowBlock;//현재 블록
	private int lastBlock;//마지막 블록
	private int navigatePages; //블록에 페이지 몇개 표시할지( ex) 1블록:1,2,3,5)
	
	/**
	 * @return
	 * @author : In Seok
	 * @Date : 2022. 5. 31.
	 * comment : 총페이지 수
	 */
	public int calcPage(int total,int pageSize) {
		int pages = total/pageSize;
		if(total % pageSize > 0) {
			++pages;
		}
		return pages;
	}
	
	/**
	 * 
	 * @author : In Seok
	 * @Date : 2022. 5. 31.
	 * comment : 현재 내 블록 알아내기
	 */
	public void setNowBlock(int pageNum, int navigatePages) {
		this.nowBlock = pageNum / navigatePages;
		if(pageNum % navigatePages > 0)this.nowBlock++;
	}
	
	/**
	 * 
	 * @author : In Seok
	 * @Date : 2022. 5. 31.
	 * comment : 마지막 블록 알아내기
	 */
	public void setLastBlock(int total) {
		this.lastBlock = total / (this.navigatePages * this.pageSize);
		if(total % this.navigatePages % this.pageSize > 0)this.lastBlock++;
	}
	//현재블록의 처음 페이지
	public void setStartpage(int nowBlock) {
		this.startpage = (nowBlock*this.navigatePages) - (this.navigatePages-1);
	}
	//현재 블록의 마지막 페이지
	public void setEndPage(int nowBlock, int total) {
		this.endPage = nowBlock*this.navigatePages;
		if(nowBlock == lastBlock) {
			this.endPage = total;
		}
	}
	//이전버튼,다음버튼 유무 판단
	public void setPreNext(int pageNum) {
		if(this.lastBlock == 1) {//블록이 하나이므로 이전,다음 버튼 존재x
			setHasPreviousPage(false);
			setHasNextPage(false);
		}
		if(this.lastBlock > 1 && this.lastBlock == this.nowBlock) {//마지막 블록이라면 이전 버튼만 존재
			setHasPreviousPage(true);
			setHasNextPage(false);
		}
		if(this.lastBlock > 1 && pageNum <= this.navigatePages) {// 첫번째블록인데 블록이 1이상일 경우 다음버튼 존재
			setHasPreviousPage(false);
			setHasNextPage(true);
		}
	}
	
	
	
	
	
	
	
	
}
