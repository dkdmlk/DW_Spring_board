package com.dw.board.interceptor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dw.board.service.LogsService;
import com.dw.board.vo.LogVO;

//Component : 내가 직접 작성한 class를 Spring에게 bean(Spring이 관리하는 클래스)으로 등록하라는 뜻 
@Component
public class Interceptor implements HandlerInterceptor{
	
	private static final Logger logger = LoggerFactory.getLogger(Interceptor.class);
	
	@Autowired
	private LogsService logsService;
	
	//preHandle : controller 에 도착하기전에 요청을 가로채는 함수
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
		String ip = request.getHeader("X-forwarded-For");
		String httpMethod = request.getMethod();
		if(ip ==null)ip = request.getRemoteAddr();
		
		logger.info("client IP : "+ ip);
		logger.info("client URL : "+ url);
		logger.info("client HTTPMethod : "+ httpMethod);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
		String time = formatter.format(Calendar.getInstance().getTime());
				
		LogVO vo = new LogVO();
		vo.setIp(ip);
		vo.setUrl(url);
		vo.setHttpMethod(httpMethod);
		vo.setLatitude("36.3286904");
		vo.setLongitude("127.4229992");
		vo.setCreateAt(time);
		logsService.setLogs(vo);
		
		//세션 체크
		HttpSession session = request.getSession();
		if(session.getAttribute("studentsId") == null) {
			response.sendRedirect("/login");//세션에 값이 없으면 /login 경로로 redirect
			return false; 
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
	
	
}
