package com.cyf.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.cyf.parser.RateAnnotationBeanDefinitionParser;

public class RateLimitNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("annotation-config", new RateAnnotationBeanDefinitionParser());
	}

}
