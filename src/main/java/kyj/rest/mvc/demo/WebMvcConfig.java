package kyj.rest.mvc.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kyj.rest.mvc.demo.interceptor.LoggerInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	private LoggerInterceptor loggerInterceptor;
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggerInterceptor)
			.excludePathPatterns("/webjars/**");
			//제외패턴은 아래와같이 기술
			//.excludePathPatterns("/test/**/")
            //.excludePathPatterns("/users/login");
	}
}
