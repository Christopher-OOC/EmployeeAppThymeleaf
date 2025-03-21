package com.niit.mms.project3.config;

import com.niit.mms.project3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private UserService userService;
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((authorize) ->
						authorize.requestMatchers("/registration/**").permitAll()
								.requestMatchers("/js/**").permitAll()
								.requestMatchers("/css/**").permitAll()
								.requestMatchers("/img/**").permitAll()
								.requestMatchers("/h2-console/**").permitAll()
								.anyRequest().authenticated()
				).formLogin(
						form -> form
								.loginPage("/login")
								.loginProcessingUrl("/login/page")
								.defaultSuccessUrl("/")
								.permitAll()
				).logout(
						logout -> logout
								.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
								.permitAll()
				).headers(headers -> headers.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));
		return http.build();
	}
}
