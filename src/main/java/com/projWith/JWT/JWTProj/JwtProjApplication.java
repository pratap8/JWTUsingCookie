package com.projWith.JWT.JWTProj;

import org.springframework.boot.SpringApplication;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@SpringBootApplication
public class JwtProjApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(JwtProjApplication.class, args);
	}

	  @Override
	    public void configureViewResolvers(ViewResolverRegistry registry) {
	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	        resolver.setPrefix("/WEB-INF/jsp/");
	        resolver.setSuffix(".jsp");
	        registry.viewResolver(resolver);
	    }
}
