package com.dao;

import java.util.List;
import com.model.Radio;

public interface FoodDao {
	public Radio selectRadioByID(long id) throws Exception;
	public List<Radio> queryAllRadioes() throws Exception;	

}
