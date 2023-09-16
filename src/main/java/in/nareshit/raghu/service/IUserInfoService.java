package in.nareshit.raghu.service;

import java.util.List;
import java.util.Optional;

import in.nareshit.raghu.consts.UserMode;
import in.nareshit.raghu.model.UserInfo;

public interface IUserInfoService {

	Integer saveUserInfo(UserInfo ui);
	List<UserInfo> getAllUserInfos();
	Optional<UserInfo> getOneUserInfoByEmail(String email);
	void updateUserStatus(Integer id,UserMode mode);
	void updateUserPassword(String email,String password);
}
