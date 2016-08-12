package com.lin;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lin.controllers.ConsumerController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatalyticdsConsumerApplicationTests {
	
	private String factorResult = "[1, 2, 5, 10]";
	private String palindromeResult = "true";
	private String fibonacciResult = "13";
	
	@Autowired
	private ConsumerController consumerController;
	
	
	@Test
	public void testFactor(){
		String result = consumerController.hello("Factor", "10");
		
		Assert.assertNotNull(result);
		Assert.assertEquals(factorResult, result);
		Assert.assertNotEquals("[]", result);
	}
	
	@Test
	public void testPalindrome(){
		String result = consumerController.hello("Palindrome", "Hello, olleh");
		String falseResult = consumerController.hello("Palindrome", "Hello, 1he");
		
		Assert.assertNotNull(result);
		Assert.assertEquals(palindromeResult, result);
		Assert.assertNotEquals(palindromeResult, falseResult);
	}
	
	@Test
	public void testFibonacci(){
		String result = consumerController.hello("Fibonacci", "6");
		String falseResult = consumerController.hello("Fibonacci", "3");
		
		Assert.assertNotNull(result);
		Assert.assertEquals(fibonacciResult, result);
		Assert.assertNotEquals(fibonacciResult, falseResult);
	}

}
