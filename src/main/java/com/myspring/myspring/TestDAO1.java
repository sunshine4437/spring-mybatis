package com.myspring.myspring;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAO1 {
	private SqlSessionTemplate sql;

	@Autowired(required = false)	
	public TestDAO1(SqlSessionTemplate sqlSessionTemplate) {
		this.sql = sqlSessionTemplate;
	}

	public List<TestVO> testSelect() {
		return sql.selectList("testSelect1");
	}
}
