package com.dao;

import java.util.List;

import com.model.GetE;

public interface GetEDao {
	public List<GetE> queryGetEByRadioAge(String radio,String age) throws Exception;
	public double queryWtByOrgan(String organ) throws Exception;
}
