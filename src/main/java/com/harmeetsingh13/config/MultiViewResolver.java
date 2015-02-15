/**
 * 
 */
package com.harmeetsingh13.config;

import java.util.ArrayList;
import java.util.List;

import nz.net.ultraq.thymeleaf.LayoutDialect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafView;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 *
 */
@Configuration
public class MultiViewResolver {

	@Bean
	public TilesConfigurer tilesConfigure() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[]{"classpath:tiles/admin-def.xml"});
		return configurer;
	}
	
	@Bean
	public TilesViewResolver tilesViewResolver() {
		TilesViewResolver resolver = new TilesViewResolver();
		resolver.setCache(false);
		return resolver;
	}
	
	
	/* Thymeleaf configuration */
	private ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCacheable(false);
		return templateResolver;
	}
	
	private SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addDialect(new LayoutDialect());
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}
	
	private ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setViewClass(ThymeleafView.class);
		thymeleafViewResolver.setViewNames(new String[]{"thymeleaf/*"});
		thymeleafViewResolver.setTemplateEngine(templateEngine());
		return thymeleafViewResolver;
	}
	
	/* JSP view resolver */
	
	private InternalResourceViewResolver jspViewResolver() {
		InternalResourceViewResolver resolver  = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		resolver.setCache(false);
		return resolver;
	}
	
	@Bean
	public ViewResolver setupViewResolver(ContentNegotiationManager manager) {
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		
		resolvers.add(tilesViewResolver());
		resolvers.add(thymeleafViewResolver());
		resolvers.add(jspViewResolver());
		
		ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
		viewResolver.setViewResolvers(resolvers);
		viewResolver.setContentNegotiationManager(manager);
		return viewResolver;
	}
}
