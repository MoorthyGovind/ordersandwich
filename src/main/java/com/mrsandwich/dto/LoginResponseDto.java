package com.mrsandwich.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDto {
	
	private Integer statusCode;
	private String message;
	private Integer userId;
	private String role;
	private String name;

}
