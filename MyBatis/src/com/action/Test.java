package com.action;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.dao.RadioDao;
import com.model.Radio;


public class Test {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader; 

    static{
        try{
            reader    = Resources.getResourceAsReader("SQLConfiguration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }
    public void a(){
    	System.out.println("testtest");
    }
    public static void main(String[] args) throws Exception {
    	SqlSession session = sqlSessionFactory.openSession();
        try {
        		RadioDao radioDao = session.getMapper(RadioDao.class);
        		Radio radio = radioDao.selectRadioByID(1);
        		System.out.println(radio.getRadio());
        		System.out.println(radio.getActivity());
        } finally {
        session.close();
        System.out.println("endddd");
        }

    }
}