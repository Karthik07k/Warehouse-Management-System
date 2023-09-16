package com.logistics.kk.runner;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.logistics.kk.consts.UserMode;
import com.logistics.kk.model.UserInfo;
import com.logistics.kk.repo.RoleRepository;
import com.logistics.kk.repo.UserInfoRepository;
import com.logistics.kk.service.IUserInfoService;

@Component
@Order(20)
public class UserMasterDataRunner implements CommandLineRunner {

	@Autowired
	private IUserInfoService service;
	
	@Autowired
	private UserInfoRepository repo;
	
	@Autowired
	private RoleRepository roleRepo;

	public void run(String... args) throws Exception {
		if(!repo.existsByEmail("karthikkumarks50@gmail.com")) 
		{
			UserInfo user = new UserInfo();

			user.setName("KK-MASTER");
			user.setEmail("karthikkumarks50@gmail.com");

			user.setPassword("karthik123");
			user.setMode(UserMode.ENABLED);

			user.setRoles(
					roleRepo.findAll()
					.stream()
					.collect(Collectors.toSet())
					);

			service.saveUserInfo(user);
		}
	}

}
