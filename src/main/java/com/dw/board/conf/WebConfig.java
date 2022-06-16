package com.dw.board.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dw.board.interceptor.Interceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	private Interceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//logs에 기록되는 부분 제외
		registry.addInterceptor(interceptor).excludePathPatterns(
				"/api/v1/logs","/login","/join","/logs",
				"/api/v1/login","/resources/static/css/*",
				"/resources/static/js/*","/resources/static/images/*",
				"/error");
		
	}
	
}
