package com.action;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.dao.AgeDao;
import com.dao.RadioDao;
import com.model.Age;
import com.model.Radio;


public class ConnectSQLAction {

    List<Age> ageList=new ArrayList<Age>();
    List<Radio> radioList=new ArrayList<Radio>();

    public String getAgeList() throws Exception{
    	System.out.println("getAgeList start");
    	SqlSession session = Constants.getSqlSession();
        try {
        		AgeDao ageDao = session.getMapper(AgeDao.class);
        		ageList = ageDao.queryAllAges();
                for(Age ages:ageList)
                {
                	System.out.println(ages.getAgeValue());
                }
        		System.out.println("agelistend");
        } finally {
        session.close();
        System.out.println("ageListEnd"+ageList.size());
        }  
        return "ages";
        }
    public String radio() throws Exception{
    	SqlSession session = Constants.getSqlSession();
        try {
        		RadioDao radioDao = session.getMapper(RadioDao.class);
        		Radio radio = radioDao.selectRadioByID(1);
        		System.out.println(radio.getRadio());
        		System.out.println(radio.getActivity());
        		System.out.println(radio.getUnit());
        		radioList = radioDao.queryAllRadioes();
        		for(Radio radioes:radioList)
                {
                	System.out.println(radioes.getId()+radioes.getRadio()+radioes.getActivity()+radioes.getUnit());
                }
        } finally {
        session.close();
        System.out.println("endddd");
        }       
        return "radioes";
        }
}