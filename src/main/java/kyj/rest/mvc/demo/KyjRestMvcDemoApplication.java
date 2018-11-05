package kyj.rest.mvc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kyj.rest.mvc.demo.interceptor.LoggerInterceptor;
import kyj.rest.mvc.demo.security.AppProperties;

/*
 * @SpringBootApplication 어노테이션이 아래 3 항목을 포함하고있다.
 * @SpringBootConfiguration
 * @EnableAutoConfiguration
 * @ComponentScan
 */

@SpringBootApplication
public class KyjRestMvcDemoApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(KyjRestMvcDemoApplication.class, args);
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean(name="AppProperties")
	public AppProperties getAppProperties()
	{
		return new AppProperties();
	}
	
	@Bean(name="LoggerInterceptor")
	public LoggerInterceptor loggerInterceptor()
	{
		return new LoggerInterceptor();
	}
}
