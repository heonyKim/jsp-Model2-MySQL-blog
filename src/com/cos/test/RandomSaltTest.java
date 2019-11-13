package com.cos.test;



import java.util.Random;

import org.junit.jupiter.api.Test;

import com.cos.util.SHA256;

public class RandomSaltTest {
	
	@Test
	public void RandomSaltTest() {
		String password = SHA256.getEncrypt("ssar", "cos");
		System.out.println(password);
		
		String password2 = SHA256.getEncrypt("ssar", "0.78");
		System.out.println(password2);

		
		
		
	}
}
