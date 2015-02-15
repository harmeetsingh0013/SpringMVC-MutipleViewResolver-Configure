/**
 * 
 */
package com.harmeetsingh13.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class MultiViewResolverSecondWay {


	@Bean
	public TilesConfigurer tilesConfigure() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[]{"classpath:tiles/admin-def.xml"});
		return configurer;
	}
	@Bean
	public TilesViewResolver viewResolver() {
		TilesViewResolver resolver = new TilesViewResolver();
		resolver.setOrder(0);
		return resolver;
	}
	
	/* Thymeleaf configuration */
	@Bean
	public ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setOrder(1);
		return templateResolver;
	}
	@Bean
	@Autowired
	public SpringTemplateEngine templateEngine(ServletContextTemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine;
	}
	@Bean
	@Autowired
	public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setViewClass(ThymeleafView.class);
		thymeleafViewResolver.setViewNames(new String[]{"thymeleaf/*"});
		thymeleafViewResolver.setTemplateEngine(templateEngine);
		return thymeleafViewResolver;
	}
	
	/* JSP view resolver */
	@Bean
	public InternalResourceViewResolver jspViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		//resolver.setOrder(2);
	return resolver;
	}
}
