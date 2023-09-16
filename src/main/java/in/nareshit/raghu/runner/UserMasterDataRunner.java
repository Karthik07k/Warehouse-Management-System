package in.nareshit.raghu.runner;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import in.nareshit.raghu.consts.UserMode;
import in.nareshit.raghu.model.UserInfo;
import in.nareshit.raghu.repo.RoleRepository;
import in.nareshit.raghu.repo.UserInfoRepository;
import in.nareshit.raghu.service.IUserInfoService;

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
		if(!repo.existsByEmail("nit@gmail.com")) 
		{
			UserInfo user = new UserInfo();

			user.setName("NIT-MASTER");
			user.setEmail("nit@gmail.com");

			user.setPassword("nit");
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
