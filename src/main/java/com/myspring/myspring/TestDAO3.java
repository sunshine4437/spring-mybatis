package com.myspring.myspring;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestDAO3 {
	public List<TestVO> testSelect3();
}
