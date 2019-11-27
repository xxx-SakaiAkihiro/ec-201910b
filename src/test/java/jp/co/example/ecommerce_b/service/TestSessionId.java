package jp.co.example.ecommerce_b.service;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSessionId {
	
	@Autowired
	public HttpSession session;
	@Test
	public void test() {
		
		String source = session.getId();
		int radix = 36; // 数値＋アルファベット小文字の36進数

		Long result = Long.parseLong(source, radix);
		Integer userId = new Integer(result.toString());
		
		System.out.println(userId);
		
	}

}
