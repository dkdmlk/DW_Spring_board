package com.dw.board.vo;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogVO {
	private int logId;
	private String ip;
	private String latitude;
	private String longitude;
	private String url;
	private String httpMethod;
	private String createAt;
	
}
