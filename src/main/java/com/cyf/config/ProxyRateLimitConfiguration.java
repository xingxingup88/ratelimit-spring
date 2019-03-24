package com.cyf.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cyf.processor.RateLimitBeanPostProcessor;

@Configuration
public class ProxyRateLimitConfiguration {
	@Bean
	public BeanPostProcessor bpp()
	{
		return new RateLimitBeanPostProcessor();
	}
}
