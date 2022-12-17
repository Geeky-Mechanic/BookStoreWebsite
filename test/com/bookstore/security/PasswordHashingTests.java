package com.bookstore.security;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PasswordHashingTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() throws Exception {
		String toHash = "mysecret";
		System.out.println(PasswordHashing.generateHashString(toHash));
		assertEquals( PasswordHashing.generateHashString(toHash), PasswordHashing.generateHashString(toHash));
	}

}
