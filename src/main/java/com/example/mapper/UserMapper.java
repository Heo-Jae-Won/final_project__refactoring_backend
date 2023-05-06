package com.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import com.example.dto.UserDto;

@Mapper
public interface UserMapper {

	public UserDto read(String userId);

	public void update(UserDto updateVO);

	public void insert(UserDto insertVO);

	public void deactivate(String userId);

	public void restore(UserDto restoreVO);

	public int updatePw(UserDto vo);

	public String readUnickname(String userNickname);

	public String readUemail(@Param("userEmail") String userEmail, @Param("userName") String userName);

	public void updateUpoint(String receiver);

}
