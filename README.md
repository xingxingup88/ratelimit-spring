# ratelimit-spring
Easier to use guava RateLimit in Spring with annotation.

How to use:
1.Clone the code or download rateLimit-spring.jar.

2.Config <ratelimit:annotation-config/> in your spring cofiguration file or use @EnableRateLimit to annotate your spring configuration class.

3.Annotate @RateLimit on the method you want to limit calling rate,parameter rate determines how many times the method should be called in one second,block determines if the call will block where exceed the rate, if false, you can define the timeOut and failover method.
