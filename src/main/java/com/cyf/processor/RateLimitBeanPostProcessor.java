package com.cyf.processor;

import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.beans.factory.BeanFactory;

import com.cyf.advisor.RateLimitAdvisor;

public class RateLimitBeanPostProcessor extends
		AbstractBeanFactoryAwareAdvisingPostProcessor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		super.setBeanFactory(beanFactory);
		RateLimitAdvisor advisor = new RateLimitAdvisor();
		this.advisor = advisor;
	}
}
