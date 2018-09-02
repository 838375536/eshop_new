package com.item.eshop;

import com.item.eshop.config.Constant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableConfigurationProperties(Constant.class)
@EnableWebSocket
@MapperScan("com.item.eshop.mapper")
public class EshopApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(EshopApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EshopApplication.class);
	}

	/**
	 * 文件上传临时路径
	 */
//	@Bean
//	MultipartConfigElement multipartConfigElement() {
//		MultipartConfigFactory factory = new MultipartConfigFactory();
//		factory.setLocation("D:/test/");
//		return factory.createMultipartConfig();
//	}
}
