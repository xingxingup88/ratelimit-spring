package com.cyf.advisor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.standard.Media;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

import com.cyf.ratelimit.RateLimit;
import com.google.common.util.concurrent.RateLimiter;

public class RateLimitAdvisor extends AbstractPointcutAdvisor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RateLimiter rateLimiter;
	private Method failOver;
	private int timeOut = 0;
	private boolean block;
	@Override
	public Pointcut getPointcut() {
		return AnnotationMatchingPointcut.forMethodAnnotation(RateLimit.class);
	}

	@Override
	public Advice getAdvice() {
		return new MethodInterceptor() {
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				RateLimit rLimit = invocation.getMethod().getDeclaredAnnotation(RateLimit.class);
				if (rateLimiter == null)
				{
					createLimiter(rLimit, invocation.getMethod());
				}
				if (block)
				{
					rateLimiter.acquire();
					return invocation.proceed();
				}
				else
				{
					boolean b = rateLimiter.tryAcquire(timeOut, TimeUnit.MICROSECONDS);
					if (b)
					{
						return invocation.proceed();
					}
					else
					{
						if (failOver != null)
						{
							failOver.invoke(invocation.getThis(), invocation.getArguments());
						}
						else
						{
							System.out.println("no failover found!");
						}
					}
				}
				return null;
			}
		};
	}

	protected void createLimiter(RateLimit rLimit, Method m) {
		double rate = rLimit.rate();
		timeOut = rLimit.timeOut();
		block = rLimit.block();
		rateLimiter = RateLimiter.create(rate);
		String fo = rLimit.failOver();
		if (!fo.equals(""))
		{
			failOver = findFailoverMethod(m, fo);
		}
	}

	private Method findFailoverMethod(Method m, String fo) {
		Method[] methods = m.getDeclaringClass().getDeclaredMethods();
		for (Method method : methods)
		{
			if (method.getName().equals(fo) && Arrays.equals(method.getParameterTypes(), m.getParameterTypes()))
				return method;
		}
		return null;
	}

}
