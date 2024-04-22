package com.hokhanh.sinhVien.security;

import javax.naming.AuthenticationException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SinhVienConfiguration {

	@Autowired
	private SinhVienDetailsService giangVienDetailsService;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.authorizeHttpRequests(request -> request	
			.requestMatchers("/resources/**", "/css/**", "/img/**", "/vendor/**", "/js/**", "/scss/**").permitAll()
			.requestMatchers("/login").permitAll()
			.requestMatchers("/home").permitAll()
			.requestMatchers("giangVienVaCanBo").permitAll()
			.requestMatchers("/thongBao").permitAll()
			.requestMatchers("/thoiKhoaBieu").permitAll()
			.requestMatchers("/deTaiNghienCuu").permitAll()
			.requestMatchers("/loTrinhHoc").permitAll()
			.anyRequest().authenticated()
		)
		.formLogin(form -> form
			.loginPage("/login") 
			.loginProcessingUrl("/do-login")
			.defaultSuccessUrl("/home", true)
			.permitAll()
		)
		.rememberMe(remember -> remember.tokenRepository(persistenTokenRepository()))
		.logout(logout -> logout
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout")
			.permitAll()
		);

		return http.build();
	}
	
	@Bean
	public PersistentTokenRepository persistenTokenRepository() {
		JdbcTokenRepositoryImpl impl =new JdbcTokenRepositoryImpl();
		impl.setDataSource(this.dataSource);
		return impl;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
		
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(this.giangVienDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	
}
