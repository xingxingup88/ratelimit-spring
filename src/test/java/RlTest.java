import com.cyf.ratelimit.RateLimit;


public class RlTest {
	@RateLimit(block=true, rate=0.5)
	public void test()
	{
		System.out.println("test");
	}
}
