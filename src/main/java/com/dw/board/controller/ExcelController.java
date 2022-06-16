package com.dw.board.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.dw.board.service.ExcelService;

/**
 * @author dw-006
 * @create-data : 22.06.14
 * comment : excel 다운로드 받는 컨트롤러
 */
@Controller
public class ExcelController {
	@Autowired
	private ExcelService excelservice;
	
	
	//엑셀, 사진, 한글, PDF, Zip, 영상 등등... return type이 void or RsponseEntity
	//페이지 이름으로 return (X)
	@GetMapping("/excel")
	public void downloadExcelFile(HttpServletResponse response) throws Exception{
		String today = new SimpleDateFormat( "yyMMdd").format(new Date());
		String title = "DW아카데미_게시판";
				
		//엑셀 파일을 보내겠다.
		response.setContentType("ms-vnd/excel");
		//엑셀 파일 이름 수정
		response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(today+"_"+title,"UTF-8")+".xls");
		Workbook workBook = excelservice.makeExcelForm();
		workBook.write(response.getOutputStream());
		workBook.close();
		        
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
		
		
		
	}
}
