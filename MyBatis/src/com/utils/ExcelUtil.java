package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.action.Constants;
import com.dao.ExcelDao;
import com.dao.SaveEDao;
import com.model.FoodConsume;
import com.model.FoodContent;
import com.model.FoodTypeModel;
import com.model.Food_consumption_data;
import com.model.GraphOneModel;
import com.model.Import_table_format;
import com.model.Limiting_concentration;
import com.model.OrganContent;
import com.model.OrganWt;
import com.model.Radio;
import com.model.Radios;
import com.model.SVTModel;
import com.model.SVTRAModel;
import com.model.SaveE;
import com.model.Table_six;
import com.model.Table_wto;

public class ExcelUtil {
	
    private static DecimalFormat df = new DecimalFormat("0");  
    private static SimpleDateFormat sdf = new SimpleDateFormat(  "yyyy-MM-dd HH:mm:ss");   
    private static DecimalFormat nf = new DecimalFormat("0.00");    
    public static ArrayList<ArrayList<Import_table_format>> readAdminExcel(File file) throws Exception{  
        if(file == null){  
            return null;  
        }  
        
        if(file.getName().endsWith("xlsx")){  
        	System.out.println("2007");
            return readExcel2007(file);
        }else{  
            //处理ecxel2003  
            //return readExcel2003(file);  
        	
        }  
        return null;
    }  
  
    public ArrayList<ArrayList<ArrayList<Object>>> readExcel2003(File file){  
        try{  
        	ArrayList<ArrayList<ArrayList<Object>>> sheetList = new ArrayList<ArrayList<ArrayList<Object>>>();
            ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();  
            ArrayList<Object> colList;  
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));  
            for(int k=0;k<wb.getNumberOfSheets();k++){
            	HSSFSheet sheet = wb.getSheetAt(k);  
            	HSSFRow row;  
            	HSSFCell cell;  
            	Object value; 
            	for(int i = sheet.getFirstRowNum() , rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows() ; i++ ){  
            		row = sheet.getRow(i);  
            		colList = new ArrayList<Object>();  
            		if(row == null){  
            			if(i != sheet.getPhysicalNumberOfRows()){//判断是否是最后一行  
            				rowList.add(colList);  
            			}  
            			continue;  
            		}else{  
            			rowCount++;  
            		}  
            		for( int j = row.getFirstCellNum() ; j <= row.getLastCellNum() ;j++){  
            			cell = row.getCell(j);  
            			if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){  
            				if(j != row.getLastCellNum()){
            					colList.add("");  
            				}  
            				continue;  
            			}  
            			switch(cell.getCellType()){  
            			case XSSFCell.CELL_TYPE_STRING:    
            				value = cell.getStringCellValue();    
            				break;    
            			case XSSFCell.CELL_TYPE_NUMERIC:
            				if ("@".equals(cell.getCellStyle().getDataFormatString())) {    
            					value = df.format(cell.getNumericCellValue());    
            				} else if ("General".equals(cell.getCellStyle()    
            						.getDataFormatString())) {    
            					value = nf.format(cell.getNumericCellValue());    
            				} else {    
            					value = sdf.format(HSSFDateUtil.getJavaDate(cell    
            							.getNumericCellValue()));    
            				}    
            				break;    
            			case XSSFCell.CELL_TYPE_BOOLEAN:    
            				value = Boolean.valueOf(cell.getBooleanCellValue());  
            				break;    
            			case XSSFCell.CELL_TYPE_BLANK:    
            				value = "";    
            				break;    
            			default:    
            				value = cell.toString();    
            			}
            			colList.add(value);  
            		}
            		rowList.add(colList);  
            	}  
            	sheetList.add(rowList);
            }
            return sheetList;
        }catch(Exception e){  
            return null;  
        }  
    }  
      
    public static  ArrayList<ArrayList<Import_table_format>> readExcel2007(File file) throws Exception {
    	clearAllTables();
        try{  
        	ArrayList<ArrayList<Import_table_format>> sheetList = new ArrayList<ArrayList<Import_table_format>>();
            System.out.println(file.getName()+"路径："+file.getPath()+file.length());
            InputStream inputStream=new FileInputStream(file);
            XSSFWorkbook wb =  new XSSFWorkbook(inputStream); 
            
            for(int k=0;k<wb.getNumberOfSheets();k++){
            	XSSFSheet sheet = wb.getSheetAt(k);
            	String name = wb.getSheetName(k);
            	switch(name){
            		case "食品消费量数据":
            			analysisFirstSheet(sheet);
            			break;
            		case "用户导入表格格式":
            			analysisSecondSheet(sheet);
            			break;
            		case "限制浓度":
            			analysis12Sheet(sheet);
            			break;
            		default:
            			analysisRadioSheets(sheet);
            			break;
            	}
            }
            return sheetList;  
        }catch(Exception e){
            return null;  
        } 
        finally {
        	newGetE();
			System.out.println("finally");
		}
    }  
    public static void analysisFirstSheet(XSSFSheet sheet) throws Exception{
    	ArrayList<Food_consumption_data> rowList = new ArrayList<Food_consumption_data>();
    	String foodType = new String();  
    	XSSFRow row;  
    	XSSFCell cell;
    	Object value;
    	List<String> colName = new ArrayList<String>();
    	row = sheet.getRow(sheet.getFirstRowNum());
    	for( int j = row.getFirstCellNum() ; j < row.getLastCellNum() ;j++){ 
    		colName.add(row.getCell(j).toString().replaceAll(" ", "").replaceAll("岁",""));
    	}
    	for(int i = sheet.getFirstRowNum()+1,rowCount=0; i < sheet.getPhysicalNumberOfRows() ; i++ ){  
    		Food_consumption_data colList; 
    		row = sheet.getRow(i);  
    		if(row == null){  
    			if(i != sheet.getPhysicalNumberOfRows()){ 
    			}  
    			continue;  
    		}else{  
    			rowCount++;  
    		}
    		for( int j = row.getFirstCellNum()+2 ; j < sheet.getRow(0).getLastCellNum() ;j++){  
    			colList = new Food_consumption_data();  
    			cell = row.getCell(j);  
    			if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){ 
    				if(j != row.getLastCellNum()){ 
    					colList.setFoodConsume(0);
    					if(row.getCell(0).toString() == "" || row.getCell(0).toString() == null){
    						colList.setFoodType(foodType);
    					}
    					else{
    						foodType = row.getCell(0).toString();
    						colList.setFoodType(foodType);
    					}
    					colList.setFoodName(row.getCell(1).toString());
    					colList.setAge(colName.get(j));
    					rowList.add(colList);
    				}  
    				continue;  
    			}
    			switch(cell.getCellType()){  
    			case XSSFCell.CELL_TYPE_STRING:    
    				value = cell.getStringCellValue();    
    				break;    
    			case XSSFCell.CELL_TYPE_NUMERIC:  
    				value = cell.getNumericCellValue();
    				break;    
    			case XSSFCell.CELL_TYPE_BOOLEAN:    
    				value = Boolean.valueOf(cell.getBooleanCellValue());  
    				break;    
    			case XSSFCell.CELL_TYPE_BLANK:    
    				value = "";    
    				break;    
    			default:    
    				value = cell.toString();    
    			}  
    			colList.setFoodConsume(Double.parseDouble(value.toString())); 
    			if(row.getCell(0).toString() == "" || row.getCell(0).toString() == null){
					colList.setFoodType(foodType);
				}
				else{
					foodType = row.getCell(0).toString();
					colList.setFoodType(foodType);
				}
				colList.setFoodName(row.getCell(1).toString());
				colList.setAge(colName.get(j));
				rowList.add(colList);
    		}
    	}
    	int result = insertFoodConsumptionData(rowList);
    }
       
    public static void analysisSecondSheet(XSSFSheet sheet) throws Exception{
    	ArrayList<Import_table_format> rowList = new ArrayList<Import_table_format>();
    	ArrayList<Table_six> rowList2 = new ArrayList<Table_six>();
    	String foodType = new String();   
    	XSSFRow row;  
    	XSSFCell cell;
    	Object value;
    	List<String> colName = new ArrayList<String>();
    	row = sheet.getRow(sheet.getFirstRowNum()+1);
    	for( int j = row.getFirstCellNum() ; j < row.getLastCellNum() ;j++){    
    		colName.add(row.getCell(j).toString().replaceAll(" ", ""));
    	}
    	
    	for(int i = sheet.getFirstRowNum()+2,rowCount=0; i < sheet.getPhysicalNumberOfRows() ; i++ ){  
    		Import_table_format colList; 
    		Table_six colList2;
    		row = sheet.getRow(i);  
    		if(row == null){  
    			if(i != sheet.getPhysicalNumberOfRows()){ 
    			}  
    			continue;  
    		}else{  
    			rowCount++;  
    		}
    		
    		for( int j = row.getFirstCellNum()+2 ; j < sheet.getRow(0).getLastCellNum()-2 ;j++){  
    			colList = new Import_table_format();  
    			cell = row.getCell(j);  
    			if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){ 
    				if(j != row.getLastCellNum()){ 
    					colList.setContent(0);
    					if(row.getCell(0).toString() == "" || row.getCell(0).toString() == null){
    						colList.setFoodType(foodType);
    					}
    					else{
    						foodType = row.getCell(0).toString();
    						colList.setFoodType(foodType);
    					}
    					colList.setFoodName(row.getCell(1).toString());
    					colList.setRadio(colName.get(j));
    					rowList.add(colList);
    				}  
    				continue;  
    			}
    			switch(cell.getCellType()){  
    			case XSSFCell.CELL_TYPE_STRING:    
    				value = cell.getStringCellValue();    
    				break;    
    			case XSSFCell.CELL_TYPE_NUMERIC:  
    				value = cell.getNumericCellValue();
    				break;    
    			case XSSFCell.CELL_TYPE_BOOLEAN:    
    				value = Boolean.valueOf(cell.getBooleanCellValue());  
    				break;    
    			case XSSFCell.CELL_TYPE_BLANK:    
    				value = "";    
    				break;    
    			default:    
    				value = cell.toString();    
    			} 
    			colList.setContent(Double.parseDouble(value.toString())); 
				if(row.getCell(0).toString() == "" || row.getCell(0).toString() == null){
					colList.setFoodType(foodType);
				}
				else{
					foodType = row.getCell(0).toString();
					colList.setFoodType(foodType);
				}
				colList.setFoodName(row.getCell(1).toString());
				colList.setRadio(colName.get(j));
				rowList.add(colList);
    		}
    		colList2 = new Table_six();
    		for(int j=sheet.getRow(0).getLastCellNum()-2;j<sheet.getRow(0).getLastCellNum();j++){
    			cell = row.getCell(j);
    			if(!(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK)){
    				value = cell.toString();
    				if(j == sheet.getRow(0).getLastCellNum()-2){
    					colList2.setFoodType(row.getCell(0).toString());
    					colList2.setNumber(Double.parseDouble((String) value));
    				}
    				else{
    					colList2.setSD(Double.parseDouble((String) value));
    					if(colList2.getFoodType() != "")
    						rowList2.add(colList2);
    				}
    			}else{
    				if(j == sheet.getRow(0).getLastCellNum()-2){
    					colList2.setFoodType(row.getCell(0).toString());
    					colList2.setNumber(0);
    				}
    				else{
    					colList2.setSD(0);
    					if(colList2.getFoodType() != "")
    						rowList2.add(colList2);
    				}
    			}
    			
    		}
    	}  
    	int result = insertImportTableFormat(rowList);
    	int result1 = insertTableSix(rowList2);
    }
    
    public static void analysis12Sheet(XSSFSheet sheet) throws Exception{
    	ArrayList<Limiting_concentration> rowList = new ArrayList<Limiting_concentration>();
    	XSSFRow row;  
    	XSSFCell cell;
    	Object value;
    	List<String> colName = new ArrayList<String>();
    	row = sheet.getRow(sheet.getFirstRowNum()+1);
    	for( int j = row.getFirstCellNum() ; j < row.getLastCellNum() ;j++){  
    		colName.add(row.getCell(j).toString().replaceAll(" ", ""));
    	}
    	
    	for(int i = sheet.getFirstRowNum()+2,rowCount=0; i < sheet.getPhysicalNumberOfRows() ; i++ ){  
    		Limiting_concentration colList; 
    		row = sheet.getRow(i);  
    		if(row == null){  
    			if(i != sheet.getPhysicalNumberOfRows()){ 
    			}  
    			continue;
    		}else{  
    			rowCount++;  
    		}
    		for( int j = row.getFirstCellNum()+1 ; j < sheet.getRow(1).getLastCellNum() ;j++){  
    			colList = new Limiting_concentration();  
    			cell = row.getCell(j);  
    			if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
    				if(j != row.getLastCellNum()){ 
    					colList.setLKJ(0);
    					colList.setFoodType(row.getCell(0).toString());
    					colList.setRadio(colName.get(j));
    					rowList.add(colList);
    				} 
    				continue;  
    			}
    			switch(cell.getCellType()){  
	    			case XSSFCell.CELL_TYPE_STRING:    
	    				value = cell.getStringCellValue();    
	    				break;    
	    			case XSSFCell.CELL_TYPE_NUMERIC:
	    				value = cell.getNumericCellValue();
	    				break;    
	    			case XSSFCell.CELL_TYPE_BOOLEAN:
	    				value = Boolean.valueOf(cell.getBooleanCellValue());  
	    				break;    
	    			case XSSFCell.CELL_TYPE_BLANK:
	    				value = "";    
	    				break;    
	    			default:
	    				value = cell.toString();    
    			} 
    			colList.setLKJ(Double.parseDouble(value.toString())); 
				colList.setFoodType(row.getCell(0).toString());
				colList.setRadio(colName.get(j));
				rowList.add(colList);
    		} 
    	} 
    	int result = insertLimitingConcentration(rowList);
    }
    
    public static void analysisRadioSheets(XSSFSheet sheet) throws Exception{
    	String name = sheet.getSheetName();
    	name = name.substring(0,name.length());
    	ArrayList<Radios> rowList = new ArrayList<Radios>();
    	ArrayList<Table_wto> rowList2 = new ArrayList<>();
    	XSSFRow row;  
    	XSSFCell cell;
    	Object value;
    	List<String> colName = new ArrayList<String>();
    	row = sheet.getRow(sheet.getFirstRowNum());
    	for( int j = row.getFirstCellNum() ; j < row.getLastCellNum() ;j++){  
    		colName.add(row.getCell(j).toString().replaceAll(" ", ""));
    	}
    	
    	for(int i = sheet.getFirstRowNum()+1,rowCount=0; i < sheet.getPhysicalNumberOfRows() ; i++ ){  
    		Radios colList; 
    		Table_wto colList2;
    		row = sheet.getRow(i);  
    		if(row == null){  
    			if(i != sheet.getPhysicalNumberOfRows()){ 
    			}  
    			continue;
    		}else{  
    			rowCount++;  
    		}
    		for( int j = row.getFirstCellNum()+1 ; j < sheet.getRow(0).getLastCellNum() ;j++){  
    			if(j == sheet.getRow(0).getLastCellNum()-1){  //取得sheet最后一列
    				colList2 = new Table_wto();
    				cell = row.getCell(j);
    				colList2.setOrgan(row.getCell(0).toString());
    				if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
    					colList2.setWt(0);
        			}
    				else{
    					value = cell.toString();
    					colList2.setWt(Double.parseDouble((String) value));
    				}
    				rowList2.add(colList2);
    				break;
    			}
    			colList = new Radios();  
    			cell = row.getCell(j);  
    			if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
    				if(j != row.getLastCellNum()){//判断是否是该行中最后一个单元格  
    					colList.setOrganContent(0);
    					colList.setOrgan(row.getCell(0).toString());
    					colList.setAge(colName.get(j));
    					colList.setRadio(name);
    					rowList.add(colList);
    				} 
    				continue;  
    			}
    			switch(cell.getCellType()){  
	    			case XSSFCell.CELL_TYPE_STRING:    
	    				value = cell.getStringCellValue();    
	    				break;    
	    			case XSSFCell.CELL_TYPE_NUMERIC:
	    				value = cell.getNumericCellValue();
	    				break;    
	    			case XSSFCell.CELL_TYPE_BOOLEAN:
	    				value = Boolean.valueOf(cell.getBooleanCellValue());  
	    				break;    
	    			case XSSFCell.CELL_TYPE_BLANK:
	    				value = "";    
	    				break;    
	    			default:
	    				value = cell.toString();    
    			} 
    			colList.setRadio(name);
    			colList.setAge(colName.get(j));
    			colList.setOrgan(row.getCell(0).toString());
    			colList.setOrganContent(Double.parseDouble(value.toString()));
				rowList.add(colList);
    		} 
    		
    	} 
    	int result = insertRadios(rowList);
    	int result2 = insertTableWto(rowList2);
    }
    
    public static int insertFoodConsumptionData(List<Food_consumption_data> list) throws Exception{
    	int result;
    	SqlSession session = Constants.getSqlSession();
    	ExcelDao excelDao = session.getMapper(ExcelDao.class);
    	result = excelDao.insert_Food_consumption_data(list);
    	session.commit();
    	session.close();
    	return result;
    }
    public static int insertImportTableFormat(List<Import_table_format> list) throws Exception{
    	int result;
    	SqlSession session = Constants.getSqlSession();
    	ExcelDao excelDao = session.getMapper(ExcelDao.class);
    	result = excelDao.insert_Import_table_format(list);
    	session.commit();
    	session.close();
    	return result;
    }
    public static int insertTableSix(List<Table_six> list) throws Exception{
    	int result;
    	SqlSession session = Constants.getSqlSession();
    	ExcelDao excelDao = session.getMapper(ExcelDao.class);
    	result = excelDao.insert_Table_six(list);
    	session.commit();
    	session.close();
    	return result;
    }
    
    public static int insertLimitingConcentration(List<Limiting_concentration> list) throws Exception{
    	int result;
    	SqlSession session = Constants.getSqlSession();
    	ExcelDao excelDao = session.getMapper(ExcelDao.class);
    	result = excelDao.insert_Limiting_Concentration(list);
    	session.commit();
    	session.close();
    	return result;
    }
    public static int insertRadios(List<Radios> list) throws Exception{
    	int result;
    	SqlSession session = Constants.getSqlSession();
    	ExcelDao excelDao = session.getMapper(ExcelDao.class);
    	result = excelDao.insert_Radios(list);
    	session.commit();
    	session.close();
    	return result;
    }
    public static int insertTableWto(List<Table_wto> list) throws Exception{
    	int result;
    	SqlSession session = Constants.getSqlSession();
    	ExcelDao excelDao = session.getMapper(ExcelDao.class);
    	result = excelDao.insert_Table_wto(list);
    	session.commit();
    	session.close();
    	return result;
    }
    
    public  DecimalFormat getDf() {  
        return df;  
    }  
    public  void setDf(DecimalFormat df) {  
        ExcelUtil.df = df;  
    }  
    public  SimpleDateFormat getSdf() {  
        return sdf;  
    }  
    public  void setSdf(SimpleDateFormat sdf) {  
        ExcelUtil.sdf = sdf;  
    }  
    public  DecimalFormat getNf() {  
        return nf;  
    }  
    public  void setNf(DecimalFormat nf) {  
        ExcelUtil.nf = nf;  
    }  
    
    //clear all tables for admin
    public static void clearAllTables() throws Exception {
    	SqlSession session = Constants.getSqlSession();
		ExcelDao excelDao = session.getMapper(ExcelDao.class);
        excelDao.clearSVT();
        excelDao.clearSVTRA();
        excelDao.clearFOUR();
        excelDao.clearLKJTABLE();
        excelDao.clearONE();
        excelDao.clearRAE();
        excelDao.clearSIX();
        excelDao.clearTWO();
        excelDao.clearWTO();
        session.commit();
        session.close();
	} 
    
    public static String changeAgeToTwoTable(String age){

		switch(age){
			case "2-7":
				return "2-7岁";
			case "8-12":
				return "7-12岁";
			case "13-17(M)":
				return "12-17岁";
			case "13-17(F)":
				return "12-17岁";
			default:
				return ">17岁";
		}			
	}
    public static List<SaveE> newGetE() throws Exception {
		List<SaveE> saveElist = new ArrayList<SaveE>();
		String radio;
		String age;
		double Wt = 0;
		boolean ifNewE;
		SqlSession session = Constants.getSqlSession();
		SaveEDao saveEDao = session.getMapper(SaveEDao.class);
		List<OrganWt> organWtList = saveEDao.queryOrganWt();
		List<OrganContent> organContentList = saveEDao.queryOrganContent();
        session.close();
        for(OrganContent aOrganContent:organContentList){
            radio = aOrganContent.getRadio();
            age = aOrganContent.getAge();
            ifNewE = true;
            for(OrganWt aOrganWt:organWtList){
            	if(aOrganWt.getOrgan().equals(aOrganContent.getOrgan())){
            		Wt = aOrganWt.getWt();
            		break;
            	}
            }
            for(SaveE aSaveE2:saveElist){
            	if(radio.equals(aSaveE2.getRadio())&&(age.equals(aSaveE2.getAge()))){
            		aSaveE2.setE((aSaveE2.getE()+(Wt*aOrganContent.getOrganContent())));
            		ifNewE = false;
            	}
            }
            if(ifNewE == true){
                SaveE aSaveE = new SaveE();
                aSaveE.setRadio(radio);
                aSaveE.setAge(age);
                aSaveE.setE(Wt*aOrganContent.getOrganContent());
                saveElist.add(aSaveE);  
            }          
        }
        //saveElist存了radio/age/E
        
        double E = 0,content = 0,consume = 0,activity = 0,transI=0.001,SVi = 0,foodConsumeType = 0,foodConsumeSum = 0;
        double consumeType = 0,contentType = 0,SVT = 0,consumeSum = 0,SVTConsume = 0,SVTRA = 0,SVTRAValue =0;
		int foodCountType = 0,foodCount = 0;
        String unit = "",foodName = "",foodType = "";
		List<SaveE> saveElistNew = new ArrayList<SaveE>();//存SVi
		List<SVTModel> SVTModelList = new ArrayList<SVTModel>();
		List<SVTRAModel> SVTRAModelList = new ArrayList<SVTRAModel>();
		Map<String, List<String>> foodNameMap = new HashMap<String, List<String>>();
		session = Constants.getSqlSession();
		saveEDao = session.getMapper(SaveEDao.class);		
		List<Radio> radioList = saveEDao.queryRadioList();
		List<FoodConsume> foodConsumeList = saveEDao.queryFoodConsumeList();
		List<FoodContent> foodContentList = saveEDao.queryFoodContentList();
		List<String> ageList = saveEDao.queryAgeList();
		List<FoodTypeModel> foodTypeList = saveEDao.queryFoodTypeList();
		List<GraphOneModel> graphOneModelList = saveEDao.queryLKJ();
		session.close();
		for(FoodContent aContent:foodContentList){
			if(foodNameMap.containsKey(aContent.getFoodType()) == false){
				List<String> aFoodNameList = new ArrayList<String>();
				aFoodNameList.add(aContent.getFoodName());
				foodNameMap.put(aContent.getFoodType(), aFoodNameList);
			}
			else{				
				if(foodNameMap.get(aContent.getFoodType()).contains(aContent.getFoodName())){
					continue;
				}
				foodNameMap.get(aContent.getFoodType()).add(aContent.getFoodName());
			}
		}
		for(FoodConsume aConsume:foodConsumeList){
			age = aConsume.getAge();
			consume = aConsume.getFoodConsume();
			foodType = aConsume.getFoodType();
			foodName = aConsume.getFoodName();
			for(Radio aRadio:radioList){
				radio = aRadio.getRadio();
				unit = aRadio.getUnit();
				activity = aRadio.getActivity();
				for(FoodContent aContent:foodContentList){
					if((aContent.getRadio().equals(radio))&&(aContent.getFoodName().equals(foodName))){
						content = aContent.getContent();
						break;
					}
				}
				for(SaveE aSaveE:saveElist){
					if(changeAgeToTwoTable(age).equals(aSaveE.getAge())&&(radio.equals(aSaveE.getRadio()))){
						E = aSaveE.getE();
						break;
					}
				}
				if(unit.equals("mg/kg")){
					SVi = E*content*consume*activity*transI;
				}
				else{
					SVi = E*content*consume;
				}
				SaveE aSaveE = new SaveE();
				aSaveE.setRadio(radio);
				aSaveE.setAge(age);
				aSaveE.setE(E);
				aSaveE.setFoodName(foodName);
				aSaveE.setFoodType(foodType);
				aSaveE.setActivity(activity);
				aSaveE.setUnit(unit);
				aSaveE.setFoodConsume(consume);
				aSaveE.setContent(content);
				aSaveE.setTransI(transI);
				aSaveE.setSVi(SVi);
				saveElistNew.add(aSaveE);				
			}
		}
		
		//saveElistNew存了SVi，可直接插入数据库rae表
	
		for(Radio aRadio:radioList){
			radio = aRadio.getRadio();
			for(String aAge:ageList){
				age = aAge;
				foodConsumeSum = 0;
				SVTRAValue = 0;
				for (Map.Entry<String,  List<String>> entry : foodNameMap.entrySet()) {
					foodType = entry.getKey();
					SVT = 0;
					foodCountType = entry.getValue().size();
					foodCount = 0;
					foodConsumeType = 0;
					for(SaveE aSaveE:saveElistNew){
						if(aSaveE.getRadio().equals(radio)&&aSaveE.getAge().equals(age)&&aSaveE.getFoodType().equals(foodType)){
							System.out.println("这个里面是不是没进来");
							SVT += aSaveE.getSVi();
							foodConsumeType += aSaveE.getFoodConsume();
							foodCount++;
							if(foodCount == foodCountType)
								break;
						}
					}
					SVTModel aSvtModel = new SVTModel();
					aSvtModel.setRadio(radio);
					aSvtModel.setAge(age);
					aSvtModel.setFoodType(foodType);
					aSvtModel.setFoodCount(foodCount);
					aSvtModel.setFoodConsume(foodConsumeType);
					aSvtModel.setSVT(SVT);
					SVTModelList.add(aSvtModel);
					SVTRAValue += (SVT*foodConsumeType);
					foodConsumeSum += foodConsumeType;
				}
				if(foodConsumeSum != 0)
					SVTRAValue /= foodConsumeSum;
				else
					SVTRAValue = 0;
//				SVTRAValue /= foodConsumeSum;
				SVTRAModel aSVTRAModel = new SVTRAModel();			
				aSVTRAModel.setRadio(radio);
				aSVTRAModel.setAge(age);
				aSVTRAModel.setFoodConsumeSum(foodConsumeSum);
				aSVTRAModel.setSVTRA(SVTRAValue);
				SVTRAModelList.add(aSVTRAModel);
			}
		}
		
		for(SVTRAModel ss:SVTRAModelList){
			System.out.println(ss.getAge()+"  "+ss.getFoodConsumeSum()+"  "+ss.getRadio()+"  "+ss.getSVTRA());
		}
		//SVTRAModelList存SVTRA表；SVTModelList存SVT表；saveElistNew存SVi(medicine.rae)表 第二类图 end
		
		//第一类图start
		double contentSum = 0,contaver = 0;
		for(Radio aRadio : radioList){
			for (Map.Entry<String,  List<String>> entry : foodNameMap.entrySet()) {
				contentSum = 0;
				for(FoodContent aContent:foodContentList){
					if((aContent.getFoodType().equals(entry.getKey()))&&(aContent.getRadio().equals(aRadio.getRadio()))){
						contentSum += aContent.getContent();
					}
				}
				foodCountType = entry.getValue().size();
				contaver = contentSum/foodCountType;
				for(GraphOneModel aGraphOneModel:graphOneModelList){
					if((aGraphOneModel.getFoodType().equals(entry.getKey()))&&(aGraphOneModel.getRadio().equals(aRadio.getRadio()))){
						aGraphOneModel.setContaver(contaver);
					}
				}
			}
		}
		//graphOneModelList存了全部图一的坐标值 end
		for(Radio aRadio:radioList){
			aRadio.setIfExceedSum(0);
		}
		for(GraphOneModel aGraphOneModel:graphOneModelList){
			for(Radio aRadio:radioList){//radioList存了图一全部坐标头及副坐标的值 
				if(aGraphOneModel.getRadio().equals(aRadio.getRadio())){
					aRadio.setIfExceedSum(aRadio.getIfExceedSum()+(aGraphOneModel.getContaver()/aGraphOneModel.getLKJ()));;
					continue;
				}
			}
		}
		for(Radio aRadio:radioList){
			System.out.println(aRadio.getRadio()+":"+aRadio.getIfExceedSum());
		}
		//radioList存了图一全部坐标头及副坐标的值 end
		//第一类图end

		//批量插入数据
		session = Constants.getSqlSession();
		saveEDao = session.getMapper(SaveEDao.class);
		saveEDao.insertRAETable(saveElistNew);
		saveEDao.insertSVTTable(SVTModelList);
		saveEDao.insertSVTRATable(SVTRAModelList);//图二副坐标与标题	
		saveEDao.clearLKJTABLE();
		saveEDao.insertLKJTABLE(graphOneModelList);//图一纵坐标加描点
		saveEDao.clearRA();
		saveEDao.insertRATABLE(radioList);//图一副坐标与标题
		session.commit();
		session.close();
		return saveElistNew;
		
		//得clear各种List
	}   
}
