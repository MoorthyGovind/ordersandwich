package com.mrsandwich.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class LoginRequestDto {
	
	@NotNull(message = "phone should be not null")
	private Long phoneNumber;
	@NotNull(message = "password should be not null")
	private String password;
}
