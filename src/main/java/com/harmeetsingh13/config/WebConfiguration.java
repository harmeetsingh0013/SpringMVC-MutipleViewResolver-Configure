/**
 * 
 */
package com.harmeetsingh13.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 *
 */
@Configuration
@EnableWebMvc
@Import(value=MultiViewResolver.class)
@ComponentScan(basePackages="com.harmeetsingh13")
public class WebConfiguration extends WebMvcConfigurerAdapter{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
	}
}
