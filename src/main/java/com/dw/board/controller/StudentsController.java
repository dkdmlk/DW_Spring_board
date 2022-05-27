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
import org.springframework.web.bind.annotation.RestController;

import com.dw.board.service.StudentsService;
import com.dw.board.vo.StudentsVO;

@RestController
@RequestMapping("/api/v1") 
/* @RequestMapping: 
Mapping 앞의 글자를 붙혀주는 어노테이션 (중복되는 주소를 줄여줌)
ex)학생저장 @PostMapping("/api/v1/students")->@PostMapping("/students")*/
public class StudentsController {
		@Autowired
		private StudentsService StudentsService;
		//학생 저장
		//post는 body에 숨겨서 데이터를보냄(보안)
		@CrossOrigin
		@PostMapping("/students")
		public int callSaveStudents(@RequestBody StudentsVO vo) {
			return StudentsService.setStudents(vo);
		}
		
		//학생 조회
		@GetMapping("/students")
		public List<StudentsVO> callStudentList(){
			return StudentsService.getStudentsList();
		}
		//학생 조회(Map)
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
		
		//특정 학생 조회(PK로 조회예정)
		@GetMapping("/students/id/{id}")
		public StudentsVO callStudent(@PathVariable("id") int studentsId) {
			return StudentsService.getselectStudents(studentsId);
		}
		//특정 학생 제거
		@DeleteMapping("/students/id/{id}")
		public int callRemovestudents(@PathVariable("id") int studentsId) {
			return StudentsService.getDeleteStudents(studentsId);
		}
		//특정 학생 수정
		@PatchMapping("/students/id/{id}")
		public int callUpdatestudents(@PathVariable("id") int studentsId ,@RequestBody StudentsVO vo) {
			return StudentsService.getupdateStudents(studentsId, vo);
		}
		//중요한 정보를 보낼때 Post를 사용
		@CrossOrigin
		@PostMapping("/login")
		public boolean callIsLogin(@RequestBody StudentsVO vo , HttpSession httpSession) {
			boolean isLogin = StudentsService.isStudents(vo);
			if(isLogin) {
				httpSession.setAttribute("name", "kanginseok");
			}
			return isLogin;
		}
		
}
