package com.dao;

import java.util.List;


import com.model.Age;


public interface AgeDao {
	public List<Age> queryAllAges() throws Exception;	
}
