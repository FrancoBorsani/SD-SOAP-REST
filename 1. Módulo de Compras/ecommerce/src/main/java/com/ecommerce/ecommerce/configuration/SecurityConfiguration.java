package com.ecommerce.ecommerce.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.cors.CorsConfiguration;

import com.ecommerce.ecommerce.implementation.UserService;
import com.ecommerce.ecommerce.security.JwtRequestFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private UserService userService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(HttpSecurity security) throws Exception {
    	security.authorizeRequests()
			.antMatchers("/css/**", "/js/**","/screenshot/**", "/fonts/**", "/vendor/**", "/vendor/jquery/*", "/vendor/bootstrap/js/*", "/scss/**" , "/js/**" , "/img/**" ,"/images/**", "/demo/**", "/assets/**").permitAll();
    	
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:3002"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));
        
        
    	security.cors().configurationSource(request -> corsConfiguration).
    	and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

		.authorizeRequests().antMatchers( "/api/v1/pedido/devolver", "/api/v1/productos/denuncia/*", "/api/v1/pedido/reclamos/*", "/api/v1/auth/signin", "/api/v1/registro/create", "/home", "/registro", "/iniciar", "/api/v1/productos", "/api/v1/productos/*", "/api/v1/categorias", "/api/v1/categorias/*", "/swagger-ui.html",  "swagger-ui.html", "/swagger-resources/**", "/v2/**", "/webjars/**").permitAll()
		.antMatchers(
			                HttpMethod.GET,
			                "/",
			                "/*.html",
			                "/favicon.ico",
			                "/**/*.html",
			                "/**/*.css",
			                "/**/*.js"
			        ).permitAll()
		
		.anyRequest().authenticated();
        
    	
    //    .antMatchers("/denuncia").permitAll()

    //    .antMatchers(
    //            HttpMethod.GET,
    //            "/",
    //            "/*.html",
     //           "/favicon.ico",
     //           "/**/*.html",
     //           "/**/*.css",
     //           "/**/*.js"
    //    ).permitAll()
    //    .antMatchers("/auth/**").permitAll()
    //    .antMatchers(HttpMethod.POST,"/reclamo").permitAll()
    //    .antMatchers(HttpMethod.POST,"/denuncia").permitAll()
    //    .anyRequest().authenticated();
        
    	
    	
    	
    	
        security.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        
        
        
        
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    

    
}
