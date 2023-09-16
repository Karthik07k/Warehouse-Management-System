package com.logistics.kk.service;

import java.util.List;
import java.util.Optional;

import com.logistics.kk.consts.UserMode;
import com.logistics.kk.model.UserInfo;

public interface IUserInfoService {

	Integer saveUserInfo(UserInfo ui);
	List<UserInfo> getAllUserInfos();
	Optional<UserInfo> getOneUserInfoByEmail(String email);
	void updateUserStatus(Integer id,UserMode mode);
	void updateUserPassword(String email,String password);
	
	boolean isUserEmail(String email);
}
