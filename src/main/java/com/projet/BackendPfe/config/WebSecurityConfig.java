package com.projet.BackendPfe.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.projet.BackendPfe.services.UserDetailsServiceImpl;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtAuthEntryPointJwt unauthorizedHandler;

	@Bean
	public JwtRequestFilter authenticationJwtTokenFilter() {
		return new JwtRequestFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/api/login").permitAll()
			.antMatchers("/api/sendEmailAdmin").permitAll()
			.antMatchers("/api/sendEmailUser").permitAll()
			.antMatchers("/api/test/**").permitAll()
			
			/*******************  Admin Digital Manager ***************************/
			
			.antMatchers("/adminDigital/signup").permitAll()
			.antMatchers("/adminDigital/login").permitAll()
			.antMatchers("/adminDigital/update/{id}").permitAll()
			.antMatchers("/adminDigital/updateImageProfileAdminDigial/{id}").permitAll()
			.antMatchers("/adminDigital/getAdminDigital/{id}").permitAll()
			.antMatchers("/adminDigital/getImageProfileAdminDigial/{id}").permitAll()
			.antMatchers("/adminDigital/deletAdminDigital/{id}").permitAll()

			/*******************  Admin Medical Manager ***************************/

			.antMatchers("/adminMedical/login").permitAll()
			.antMatchers("/adminMedical/update/{id}").permitAll()
			.antMatchers("/adminMedical/getAdminMedical/{id}").permitAll()
			.antMatchers("/adminMedical/updateImageProfileAdminMedical/{id}").permitAll()
			.antMatchers("/adminMedical/getImageProfileAdminMedical/{id}").permitAll()
			.antMatchers("/adminMedical/deletAdminMedical/{id}").permitAll()
			.antMatchers("/adminMedical/addAdminMedicalParAdminDigitalSansImage/{id}").permitAll()
			.antMatchers("/adminMedical/all").permitAll()
	
			
			/*******************  PCDA ***************************/
			.antMatchers("/PCDA/addPcda").permitAll()
			.antMatchers("/PCDA/all").permitAll()
			.antMatchers("/PCDA/update/{id}").permitAll()
			.antMatchers("/PCDA/{id}").permitAll()
			.antMatchers("/PCDA/get/{id}").permitAll()
			
			
			/************* UserBMW ***********************/
			.antMatchers("/userBMw/login").permitAll()
			.antMatchers("/userBMw/addUserBMWParAdminSansImage/{id}").permitAll()
			.antMatchers("/userBMw/updateUserBMW/{id}").permitAll()
			.antMatchers("/userBMw/getuserBMW/{id}").permitAll()
			.antMatchers("/userBMw/deletUserBMW/{id}").permitAll()
			.antMatchers("/userBMw/all").permitAll()
			.antMatchers("/userBMw/homme").permitAll()
			.antMatchers("/userBMw/femme").permitAll()
			.antMatchers("/userBMw/nbrAll").permitAll()
			
			/******** UserAudi **********************/
			.antMatchers("/userAudi/login").permitAll()
			.antMatchers("/userAudi/addUserAudiParAdminSansImage/{id}").permitAll()
			.antMatchers("/userAudi/updateUserAudi/{id}").permitAll()
			.antMatchers("/userAudi/getuserAudi/{id}").permitAll()
			.antMatchers("/userAudi/deletUserAudi/{id}").permitAll()
			.antMatchers("/userAudi/all").permitAll()
			.antMatchers("/userAudi/homme").permitAll()
			.antMatchers("/userAudi/femme").permitAll()
			.antMatchers("/userAudi/nbrAll").permitAll()

			/*******UserMB ****************/
			.antMatchers("/userMB/login").permitAll()
			.antMatchers("/userMB/addUserMBParAdminSansImage/{id}").permitAll()
			.antMatchers("/userMB/updateUserMb/{id}").permitAll()
			.antMatchers("/userMB/getuserMb/{id}").permitAll()
			.antMatchers("/userMB/deletUserMB/{id}").permitAll()
			.antMatchers("/userMB/all").permitAll()
			.antMatchers("/userMB/homme").permitAll()
			.antMatchers("/userMB/femme").permitAll()
			.antMatchers("/userMB/nbrAll").permitAll()
			
			
			/*******MEB_autak ****************/
			.antMatchers("/userMEB_autak/login").permitAll()
			.antMatchers("/userMEB_autak/addUserMEBParAdminSansImage/{id}").permitAll()
			.antMatchers("/userMEB_autak/updateUserMeb/{id}").permitAll()
			.antMatchers("/userMEB_autak/getuserMeb/{id}").permitAll()
			.antMatchers("/userMEB_autak/deletUserMEB/{id}").permitAll()
			.antMatchers("/userMEB_autak/all").permitAll()
			.antMatchers("/userMEB_autak/homme").permitAll()
			.antMatchers("/userMEB_autak/femme").permitAll()
			.antMatchers("/userMEB_autak/nbrAll").permitAll()
			
			/**********UserMH1**********/
			.antMatchers("/userMH1/login").permitAll()	
			.antMatchers("/userMH1/addUserMH1ParAdminSansImage/{id}").permitAll()
			.antMatchers("/userMH1/updateUserMh1/{id}").permitAll()
			.antMatchers("/userMH1/getuserMh/{id}").permitAll()
			.antMatchers("/userMH1/deletUserMH1/{id}").permitAll()
			.antMatchers("/userMH1/all").permitAll()
			.antMatchers("/userMH2/homme").permitAll()
			.antMatchers("/userMH1/femme").permitAll()
			.antMatchers("/userMH1/nbrAll").permitAll()
			
			/**********UserMH2**********/
			.antMatchers("/userMH2/login").permitAll()	
			.antMatchers("/userMH2/addUserMH2ParAdminSansImage/{id}").permitAll()
			.antMatchers("/userMH2/updateUserMh/{id}").permitAll()
			.antMatchers("/userMH2/getuserMh/{id}").permitAll()
			.antMatchers("/userMH2/deletUserMH2/{id}").permitAll()
			.antMatchers("/userMH2/all").permitAll()
			.antMatchers("/userMH2/homme").permitAll()
			.antMatchers("/userMH2/femme").permitAll()
			
			.antMatchers("/userMH2/nbrAll").permitAll()
			
			/**********UserMN**********/
			.antMatchers("/userMN/login").permitAll()	
			.antMatchers("/userMN/addUserMNParAdminSansImage/{id}").permitAll()
			.antMatchers("/userMN/updateUserMN/{id}").permitAll()
			.antMatchers("/userMN/getuserMN/{id}").permitAll()
			.antMatchers("/userMN/deletUserMN/{id}").permitAll()
			.antMatchers("/userMN/all").permitAll()
			.antMatchers("/userOEMS/homme").permitAll()
			.antMatchers("/userMN/femme").permitAll()
		
			.antMatchers("/userMN/nbrAll").permitAll()
			
			/**********UserMS**********/
			.antMatchers("/userMS/login").permitAll()	
			.antMatchers("/userMS/addUserMSParAdminSansImage/{id}").permitAll()
			.antMatchers("/userMS/updateUserMS/{id}").permitAll()
			.antMatchers("/userMS/getuserMS/{id}").permitAll()
			.antMatchers("/userMS/deletUserMS/{id}").permitAll()
			.antMatchers("/userMS/all").permitAll()
			.antMatchers("/userOEMS/homme").permitAll()
			.antMatchers("/userMS/femme").permitAll()
		
			.antMatchers("/userMS/nbrAll").permitAll()
			
			/**********UserOEMS**********/
			.antMatchers("/userOEMS/login").permitAll()	
			.antMatchers("/userOEMS/addUserOEMSParAdminSansImage/{id}").permitAll()
			.antMatchers("/userOEMS/updateUserOEMS/{id}").permitAll()
			.antMatchers("/userOEMS/getuserOEMS/{id}").permitAll()
			.antMatchers("/userOEMS/deletUserOEMS/{id}").permitAll()
			.antMatchers("/userOEMS/all").permitAll()
			.antMatchers("/userOEMS/homme").permitAll()
			.antMatchers("/userOEMS/femme").permitAll()
			
			.antMatchers("/userOEMS/nbrAll").permitAll()
			
			/**********UserVW**********/
			.antMatchers("/userVW/login").permitAll()	
			.antMatchers("/userVW/addUserVWParAdminSansImage/{id}").permitAll()
			.antMatchers("/userVW/updateUserVW/{id}").permitAll()
			.antMatchers("/userVW/getuserVW/{id}").permitAll()
			.antMatchers("/userVW/deletUserVW/{id}").permitAll()
			.antMatchers("/userVW/all").permitAll()
			.antMatchers("/userVW/homme").permitAll()
			.antMatchers("/userVW/femme").permitAll()
			.antMatchers("/userVWnbrAll").permitAll()
			
		.anyRequest().authenticated();
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
