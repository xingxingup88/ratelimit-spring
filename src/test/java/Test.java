import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cyf.config.EnableRateLimit;
import com.cyf.processor.RateLimitBeanPostProcessor;

@Configuration
@EnableRateLimit
public class Test {
	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ApplicationContext context = new AnnotationConfigApplicationContext(Test.class);
		RlTest rt = context.getBean(RlTest.class);
		for (int i = 0; i < 10; i++)
			rt.test();
	}

	@Bean
	public RlTest getRlTest()
	{
		return new RlTest();
	}
}
