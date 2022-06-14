package com.dw.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dw.board.service.StudentsService;
import com.dw.board.vo.StudentsVO;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api/v1") 
/* @RequestMapping: 
Mapping 앞의 글자를 붙혀주는 어노테이션 (중복되는 주소를 줄여줌)
ex)학생저장 @PostMapping("/api/v1/students")->@PostMapping("/students")*/
public class StudentsRestController {
		@Autowired
		private StudentsService StudentsService;
		
		//학생 저장
		//post는 body에 숨겨서 데이터를보냄(보안)
		@CrossOrigin
		@PostMapping("/students")
		public int callSaveStudents(@RequestBody StudentsVO vo) {
			return StudentsService.setStudents(vo);
		}
		
		//학생 조회 or 페이징구현
		@CrossOrigin
		@GetMapping("/students")
		public PageInfo<StudentsVO> callStudentList(@RequestParam("pageNum")int pageNum, @RequestParam("pageSize")int pageSize){
			List<StudentsVO> list = StudentsService.getStudentsList(pageNum,pageSize);
			return new PageInfo<StudentsVO>(list);
		}
		
		//학생 조회(Map)
		@CrossOrigin
		@GetMapping("/students/Map")
		public List<Map<String, Object>> callStudentMapList(HttpSession httpSession){
			//세션 데이터 가져오기 (추후 로직 구현 예정)
//			String name = (String)httpSession.getAttribute("name");
//			System.out.println("세션에서 가져온 이름은 ====> " + name);
//			if(name == null) {//로그인을 하지 않으면 막음
//				return null;
//			}
			return StudentsService.getStudentMapList();
		}
		@CrossOrigin
		@GetMapping("/students/search")
		public List<StudentsVO> callBoardSearch(@RequestParam("writer")String writer){
			return StudentsService.getSearchStudent(writer);
		}
		
		@CrossOrigin
		@GetMapping("/students/search/searchPage")
		public PageInfo<StudentsVO> callBoardSearch(@RequestParam("writer")String writer,@RequestParam("pageNum")int pageNum, @RequestParam("pageSize")int pageSize){
			List<StudentsVO> list = StudentsService.getSearchStudentPage(writer,pageNum,pageSize);
			return new PageInfo<StudentsVO>(list);
		}
		
		//특정 학생 조회(PK로 조회예정)
		@CrossOrigin
		@GetMapping("/students/id/{id}")
		public StudentsVO callStudent(@PathVariable("id") int studentsId) {
			return StudentsService.getselectStudents(studentsId);
		}
		//특정 학생 제거
		@CrossOrigin
		@DeleteMapping("/students/id/{id}")
		public int callRemovestudents(@PathVariable("id") int studentsId) {
			return StudentsService.getDeleteStudents(studentsId);
		}
		//특정 학생 수정
		@CrossOrigin
		@PatchMapping("/students/id/{id}")
		public int callUpdatestudents(@PathVariable("id") int studentsId ,@RequestBody StudentsVO vo) {
			return StudentsService.getupdateStudents(studentsId, vo);
		}
		//중요한 정보를 보낼때 Post를 사용
		@CrossOrigin
		@PostMapping("/login")
		public boolean callIsLogin(@RequestBody StudentsVO vo , HttpSession httpSession) {
			boolean isLogin = StudentsService.isStudents(vo,httpSession);
			
			return isLogin;
		}
		
		
		
}
