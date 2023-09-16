package com.logistics.kk.controller;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.kk.consts.UserMode;
import com.logistics.kk.model.UserInfo;
import com.logistics.kk.service.IRoleService;
import com.logistics.kk.service.IUserInfoService;
import com.logistics.kk.util.MailUtil;
import com.logistics.kk.util.MyAppUtil;
import com.logistics.kk.util.UserInfoUtil;

@Controller
@RequestMapping("/user")
public class UserInfoController {

	@Autowired
	private IUserInfoService service;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private MailUtil mailUtil;

	@Autowired
	private BCryptPasswordEncoder encoder;

	private void commonUi(Model model) {
		model.addAttribute("rolesMap", roleService.getRolesMap());
	}

	// 1. show Register page
	@GetMapping("/register")
	public String showUserReg(Model model) {
		commonUi(model);
		return "UserInfoRegister";
	}

	// 2. on submit
	@PostMapping("/create")
	public String saveUser(@ModelAttribute UserInfo userInfo, Model model) {
		String pwdGen = MyAppUtil.genPwd();
		String otp = MyAppUtil.genOtp();

		userInfo.setPassword(pwdGen);
		userInfo.setOtp(otp);

		Integer id = service.saveUserInfo(userInfo);
		if (id != 0) {
			String text = "UserName " + userInfo.getEmail() + ", password " + pwdGen + ", OTP " + otp + ", Roles are "
					+ UserInfoUtil.getRolesAsString(userInfo.getRoles());
			System.out.println(text);
			mailUtil.sendEmail(userInfo.getEmail(), "User Created!", text);
		}
		model.addAttribute("message", "User created => " + id);
		commonUi(model);
		return "UserInfoRegister";
	}

	// 3. show Login page
	@GetMapping("/login")
	public String showUserLogin() {
		return "UserInfoLogin";
	}

	// 4. display all users
	@GetMapping("/all")
	public String showAllUsers(Model model) {
		model.addAttribute("list", service.getAllUserInfos());
		return "UserInfoData";
	}

	// 5. session setup method
	@GetMapping("/setup")
	public String doSetup(HttpSession ses, Principal p) {
		String emailId = p.getName();
		UserInfo info = service.getOneUserInfoByEmail(emailId).get();
		ses.setAttribute("currentUser", info);
		ses.setAttribute("isAdmin", UserInfoUtil.getRolesAsString(info.getRoles()).contains("ADMIN"));
		return "redirect:/uom/register";
	}

	// 6. enable user
	@GetMapping("/enable")
	public String enableUser(@RequestParam Integer id) {
		service.updateUserStatus(id, UserMode.ENABLED);
		return "redirect:all";
	}

	// 7. enable user
	@GetMapping("/disable")
	public String disableUser(@RequestParam Integer id) {
		service.updateUserStatus(id, UserMode.DISABLED);
		return "redirect:all";
	}

	// 8. show user profile
	@GetMapping("/profile")
	public String showProfile(HttpSession ses, Model model) {
		// read current user from session
		UserInfo info = (UserInfo) ses.getAttribute("currentUser");

		// convert roles into String format for display
		Set<String> roles = UserInfoUtil.getRolesAsString(info.getRoles());

		// send details to UI
		model.addAttribute("userInfo", info);
		model.addAttribute("roles", roles);

		return "UserInfoProfile";
	}

	// 9. show user profile
	@GetMapping("/showForgot")
	public String showForgotPwdPage() {
		return "UserInfoForgotPwd";
	}

	// 10. re-gen user pwd
	@PostMapping("/reGenNewPwd")
	public String reGenNewPwd(@RequestParam String username, Model model) {
		Optional<UserInfo> opt = service.getOneUserInfoByEmail(username);
		if (opt.isPresent()) { // if username exist

			// generate new password
			String pwdGen = MyAppUtil.genPwd();

			// encode and update to DB
			String encPwd = encoder.encode(pwdGen);
			service.updateUserPassword(username, encPwd);
			// -----------send email-----------
			String text = "Hello: " + username + ", NEW PASSWORD " + pwdGen;
			System.out.println(text);
			mailUtil.sendEmail(username, "NEW PASSWORD GENERATED!", text);
			// ------------------------
			// show message at UI
			model.addAttribute("message", "PASSWORD UPDATED PLEASE CHECK YOUR INBOX!");
		} else { // username not exist
			model.addAttribute("message", "USER NOT EXIST!");
		}
		return "UserInfoForgotPwd";
	}

	// 11. show Password Update after login
	@GetMapping("/showUpdatePwd")
	public String showUpdatePwd() {
		return "UserNewPwdUpdate";
	}

	// 12. show Password Update after login
	@PostMapping("/doUpdateNewPwd")
	public String doUpdateNewPwd(
			// reading Form Input Passsword
			@RequestParam String password1, HttpSession session) {
		// read current user data from HttpSession
		UserInfo info = (UserInfo) session.getAttribute("currentUser");
		// Encode password entered in Form
		String encPwd = encoder.encode(password1);
		// update latest password in DB
		service.updateUserPassword(info.getEmail(), encPwd);
		// back to profile page
		return "redirect:profile";
	}

	// 13. show User Activation By OTP page
	@GetMapping("/showUserActiveOtp")
	public String showUserActiveOtp() {
		return "UserInfoActiveOtp";
	}

	// 14. do validate un/otp to activate
	@PostMapping("/doUserActiveOtp")
	public String doUserActiveOtp(@RequestParam String username, @RequestParam String otp, Model model) {
		// check given emailId/un exist in DB or not?
		Optional<UserInfo> opt = service.getOneUserInfoByEmail(username);

		if (opt.isPresent()) { // if username exist
			UserInfo user = opt.get();

			// compare OTP with DB value
			if (!otp.equals(user.getOtp())) { // if not matching
				model.addAttribute("message", "INVALID OTP!");
			} else { // matched
				// update user status to ACTIVE/ENABLE
				service.updateUserStatus(user.getId(), UserMode.ENABLED);
				model.addAttribute("message", "USER IS ACTIVATED, NOW YOU CAN LOGIN!!");
			}

		} else { // username not exist
			model.addAttribute("message", "USER NOT EXIST!");
		}
		return "UserInfoActiveOtp";
	}

//  7.Ajax validations 
	@GetMapping("/validateemail")
	@ResponseBody
	public String validateWhUserTypeEmail(@RequestParam String email, @RequestParam Integer id) {

		String message = "";
		if (id == 0 && service.isUserEmail(email))
			message = email + " already exit";

		return message;

	}

}
