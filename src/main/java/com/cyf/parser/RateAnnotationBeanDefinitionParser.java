package com.cyf.parser;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import com.cyf.processor.RateLimitBeanPostProcessor;

public class RateAnnotationBeanDefinitionParser implements BeanDefinitionParser {
	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		AbstractBeanDefinition processor = BeanDefinitionBuilder.genericBeanDefinition(RateLimitBeanPostProcessor.class).getBeanDefinition();
		BeanComponentDefinition component = new BeanComponentDefinition(processor, "rlProcessor");
		parserContext.registerBeanComponent(component);
		return null;
	}
}
