package com.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.model.Food_consumption_data;
import com.model.Import_table_format;
import com.model.Limiting_concentration;
import com.model.Radios;
import com.model.Table_six;
import com.model.Table_wto;


public interface ExcelDao {
	public int insert_Food_consumption_data(@Param(value = "list")Collection<Food_consumption_data> fcd) throws Exception;
	public int insert_user_Food_consumption_data(@Param(value = "list")Collection<Food_consumption_data> fcd) throws Exception;
	public int insert_Import_table_format(@Param(value = "list")Collection<Import_table_format> itf) throws Exception;
	public int insert_user_Import_table_format(@Param(value = "list")Collection<Import_table_format> itf) throws Exception;
	public int insert_Table_six(@Param(value = "list")Collection<Table_six> ts) throws Exception;
	public int insert_user_Table_six(@Param(value = "list")Collection<Table_six> ts) throws Exception;
	public int insert_Limiting_Concentration(@Param(value = "list")Collection<Limiting_concentration> lc) throws Exception;
	public int insert_Radios(@Param(value = "list")Collection<Radios> radios) throws Exception;
	public int insert_Table_wto(@Param(value = "list")Collection<Table_wto> tw) throws Exception;
	public boolean clearRAE() throws Exception;
	public boolean clearSVT() throws Exception;
	public boolean clearSVTRA() throws Exception;
	public boolean clearFOUR() throws Exception;
	public boolean clearLKJTABLE() throws Exception;
	public boolean clearONE() throws Exception;
	public boolean clearWTO() throws Exception;
	public boolean clearTWO() throws Exception;
	public boolean clearSIX() throws Exception;
	public boolean clearONENORUSER() throws Exception;
	public boolean clearFOURNORUSER() throws Exception;
	public boolean clearSIXNORUSER() throws Exception;

}
