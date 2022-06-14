package com.dw.board;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dw.board.mapper.BoardMapper;
import com.dw.board.utils.PageHandler;


@SpringBootTest
class BoardApplicationTests {
	
	@Autowired
	private PageHandler pagehandler;
	
	@Autowired
	private BoardMapper boardmapper;

	@Test
	void contextLoads() {
		int total = boardmapper.selectboardTotal();//현재 게시물 수
		System.out.println("total ===>" + total);
		// 학생전체조회
		int pageNum = 6;// 현재 페이지 번호
		int pageSize = 10; // 현 페이지에 게시물 10개
		int navigatePages = 5; // 현 블록에 페이지 5개
		
		pagehandler.setTotal(total);
		pagehandler.setPageNum(pageNum);
		pagehandler.setPageSize(pageSize);
		pagehandler.setNavigatePages(navigatePages);
		
		pagehandler.setNowBlock(pageNum);
		int nowBlock = pagehandler.getNowBlock();//현재블록
		System.out.println("현재블록 ===> " + nowBlock);
		
		pagehandler.setLastBlock(total);
		int LastBlock = pagehandler.getLastBlock();
		System.out.println("마지막 블록 ===> "+nowBlock);
		
		pagehandler.setStartpage(nowBlock);
		int startPage = pagehandler.getStartpage();
		System.out.println("현재 페이지 ===> "+startPage);
		
		
		int pages = pagehandler.calcPage(total ,pageSize);
		pagehandler.setEndPage(nowBlock, pages);
		int lastPage = pagehandler.getEndPage();
		System.out.println("마지막 페이지 ===> "+ lastPage);
		
		pagehandler.setPreNext(pageNum);
		boolean HasPreviousPage = pagehandler.isHasPreviousPage();
		boolean hasNaxtPage = pagehandler.isHasNextPage();
		System.out.println("이전 버튼 유무 ===> "+ HasPreviousPage);
		System.out.println("다음 버튼 유무 ===> "+ hasNaxtPage);
		
		int limitStart = ((pageNum -1)* pageSize);
		List<Map<String, Object>> list = boardmapper.boardAllListTest(limitStart,pageSize);
		
		for(Map<String, Object> i:list) {
			System.out.println(i);
		}
	}

}
