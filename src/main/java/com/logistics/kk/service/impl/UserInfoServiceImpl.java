package com.logistics.kk.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistics.kk.consts.UserMode;
import com.logistics.kk.model.UserInfo;
import com.logistics.kk.repo.UserInfoRepository;
import com.logistics.kk.service.IUserInfoService;

@Service
public class UserInfoServiceImpl implements IUserInfoService, UserDetailsService {

	@Autowired
	private UserInfoRepository repo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Integer saveUserInfo(UserInfo ui) {
		//read plain password from UserInfo
		String pwd = ui.getPassword();
		//encode it
		String encPwd = passwordEncoder.encode(pwd);
		//set back to same object
		ui.setPassword(encPwd);

		return repo.save(ui).getId();
	}

	public List<UserInfo> getAllUserInfos() {
		return repo.findAll();
	}

	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		Optional<UserInfo> opt = repo.findByEmail(username);
		if(!opt.isPresent() || opt.get().getMode().equals(UserMode.DISABLED)) {
				throw new UsernameNotFoundException("User not exist");
		}
		UserInfo info = opt.get();
		return new User(
				info.getEmail(),  // username 
				info.getPassword(),  // password
				info.getRoles()   //authorities
				.stream()
				.map(r -> new SimpleGrantedAuthority( r.getRole().name() ))
				.collect(Collectors.toSet())
				);	
	}

	public Optional<UserInfo> getOneUserInfoByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	@Transactional
	public void updateUserStatus(Integer id, UserMode mode) {
		repo.updateUserStatus(id, mode);
	}
	
	@Transactional
	public void updateUserPassword(String email, String password) {
		repo.updateUserPassword(email, password);
	}
	
	// userEmailExit or not...................................
	@Override
	public boolean isUserEmail(String email) {
		
		return repo.existsByEmail(email);
	}
}
