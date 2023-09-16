package in.nareshit.raghu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		// authorization
		http.authorizeRequests()
		.antMatchers("/rest/api/**","/user/login","/user/showForgot","/user/reGenNewPwd").permitAll()
		.antMatchers("/user/showUserActiveOtp","/user/doUserActiveOtp").permitAll()
		.antMatchers("/uom/**","/st/**","/om/**","/part/**","/wh/**").hasAnyAuthority("ADMIN","APPUSER")
		.antMatchers("/po/**","/grn/**","/sale/**","/shiping/**").hasAuthority("APPUSER")
		.antMatchers("/user/register","/user/create").hasAuthority("ADMIN")
		.anyRequest().authenticated()
		
		// login details
		.and()
		.formLogin()
		.loginPage("/user/login")//GET
		.loginProcessingUrl("/login")//POST
		.defaultSuccessUrl("/user/setup", true)
		.failureUrl("/user/login?error")
		
		// logout details
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/user/login?logout")
		
		// exception handling
		.and()
		.exceptionHandling()
		.accessDeniedPage("/user/denied");
	}
	
}
