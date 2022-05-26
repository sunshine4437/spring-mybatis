package com.myspring.myspring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	/*
	 * private TestDAO testDAO;
	 * 
	 * @Autowired public TestService (TestDAO testDAO) { this.testDAO = testDAO; };
	 */
	@Autowired
	private TestDAO1 testDAO1;
	@Autowired
	private TestDAO2 testDAO2;
	@Autowired
	private TestDAO3 testDAO3;
	
//	@Autowired
//	public TestService(TestDAO1 testDAO1, TestDAO2 testDAO2, TestDAO3 testDAO3)
//	
//	{
//		this.testDAO1 = testDAO1;
//		this.testDAO2 = testDAO2;
//		this.testDAO3 = testDAO3;
//	}
	
	public List<TestVO> testSelect(){
		return testDAO1.testSelect();
	}
	public List<TestVO> testSelect2(){
		return testDAO2.testSelect2();
	}
	public List<TestVO> testSelect3(){
		return testDAO3.testSelect3();
	}
}
