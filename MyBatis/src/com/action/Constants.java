package com.action;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Constants {

	private static SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();  
	private static SqlSessionFactory sqlSessionFactory=null; 
	public static SqlSession getSqlSession() throws IOException {
		if(sqlSessionFactory==null) {
		    Reader reader = Resources.getResourceAsReader("SQLConfiguration.xml");  
		    sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
		}
		return sqlSessionFactory.openSession(false);
	}
}
