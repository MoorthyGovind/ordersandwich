package com.mrsandwich.service;

import java.util.Set;

import com.mrsandwich.dto.ItemDto;
import com.mrsandwich.dto.LoginRequestDto;
import com.mrsandwich.dto.LoginResponseDto;
import com.mrsandwich.exception.UserNotFoundException;

public interface UserService {

	public Set<ItemDto> getAllUserPreferenceItems(Integer userId) throws UserNotFoundException;

	public LoginResponseDto authenticateUser(LoginRequestDto loginRequestDto) throws UserNotFoundException;

}
