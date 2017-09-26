package com.action;

import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.struts2.ServletActionContext;

import com.dao.SaveEDao;
import com.model.GraphOne;
import com.model.GraphOneHead;
import com.model.GraphOneModel;
import com.model.GraphTwo;
import com.model.GraphTwoHead;
import com.model.Radio;
import com.model.SVTModel;
import com.model.SVTRAModel;
import com.model.SaveE;
import com.opensymphony.xwork2.ActionSupport;

public class CalculateNorUser extends ActionSupport{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//加一些判断来处理用户误操作、误上传
	//注意每个query查到空时的操作
	private List<String> radioList;
	private List<String> ageList;
    private GraphOneHead graphOneHead;
	private List<GraphOneModel> graphOneModel;
	private List<GraphOne> graphOne;//存图1多图数据
    private GraphTwoHead aGraphTwoHead;
	private List<SVTModel> sVTModel;
	private List<GraphTwo> graphTwo;//存图2多图数据
	public List<GraphOneModel> getGraphOneModel() {
		return graphOneModel;
	}
	public void setGraphOneModel(List<GraphOneModel> graphOneModel) {
		this.graphOneModel = graphOneModel;
	}
	public List<GraphOne> getGraphOne() {
		return graphOne;
	}
	public void setGraphOne(List<GraphOne> graphOne) {
		this.graphOne = graphOne;
	}
	public List<SVTModel> getSVTModel() {
		return sVTModel;
	}
	public void setSVTModel(List<SVTModel> sVTModel) {
		this.sVTModel = sVTModel;
	}
	public List<GraphTwo> getGraphTwo() {
		return graphTwo;
	}
	public void setGraphTwo(List<GraphTwo> graphTwo) {
		this.graphTwo = graphTwo;
	}
	public List<String> getRadioList() {
		return radioList;
	}
	public void setRadioList(List<String> radioList) {
		this.radioList = radioList;
	}
	public List<String> getAgeList() {
		return ageList;
	}
	public void setAgeList(List<String> ageList) {
		this.ageList = ageList;
	}
	public GraphOneHead getGraphOneHead() {
        return graphOneHead;
    }
    public void setGraphOneHead(GraphOneHead graphOneHead) {
        this.graphOneHead = graphOneHead;
    }
    public GraphTwoHead getGraphTwoHead() {
        return aGraphTwoHead;
    }
    public void setGraphTwoHead(GraphTwoHead aGraphTwoHead) {
        this.aGraphTwoHead = aGraphTwoHead;
    }
	public String execute() throws Exception{	
			getGraphOneListFunc(radioList);
			getGraphTwoListFunc(radioList,ageList);
			return "SUCCESS";
	}

	
	public List<GraphOne> getGraphOneListFunc(List<String> radioList) throws Exception {
    	SqlSession session = Constants.getSqlSession();
    	SaveEDao saveEDao = session.getMapper(SaveEDao.class);
    	List<Radio> graphOneHeads = saveEDao.queryRadioByRadiosNorUser(radioList);
    	List<GraphOneModel> graphOneModels = saveEDao.queryLKJByRadiosNorUser(radioList);
        session.close();
    	List<GraphOne> graphOne = new ArrayList<GraphOne>();
    	for(Radio aGraphOneHead:graphOneHeads){
    		GraphOne aGraphOne = new GraphOne();
    		aGraphOne.setGraphOneHead(aGraphOneHead);
    		List<GraphOneModel> newGraphOneModelList = new ArrayList<GraphOneModel>();
    		for(int i = 0;i<graphOneModels.size();i++){
    			if(graphOneModels.get(i).getRadio().equals(aGraphOneHead.getRadio())){
    				newGraphOneModelList.add(graphOneModels.get(i));
    				graphOneModels.remove(i);
    				i--;
    				continue;
    			}
    		}
    		aGraphOne.setGraphOneModel(newGraphOneModelList);
    		graphOne.add(aGraphOne);
    	}
    	setGraphOne(graphOne);
		return graphOne;		
	}
    
	 public List<GraphTwo> getGraphTwoListFunc(List<String> radioList,List<String> ageList) throws Exception {
	    	
	    	SqlSession session = Constants.getSqlSession();
	    	SaveEDao saveEDao = session.getMapper(SaveEDao.class);
	    	List<SVTRAModel> SVTRAModels = saveEDao.querySVTRAByRadiosNorUser(radioList);
	    	List<SVTModel> SVTModels = saveEDao.queryGraphTwoByRadiosNorUser(radioList);
	        session.close();
	        for(int i=0;i<SVTRAModels.size();i++){
	        	if(!ageList.contains(SVTRAModels.get(i).getAge())){
	        		SVTRAModels.remove(i);
	        		i--;
	        	}
	        }
	        for(int i=0;i<SVTModels.size();i++){
	        	if(!ageList.contains(SVTModels.get(i).getAge())){
	        		SVTModels.remove(i);
	        		i--;
	        	}
	        }
	        List<GraphTwo> graphTwo = new ArrayList<GraphTwo>();
	    	for(SVTRAModel aSVTRAModel:SVTRAModels){
	    		GraphTwo aGraphTwo = new GraphTwo();
				aGraphTwo.setGraphTwoHead(aSVTRAModel);
	    		List<SVTModel> newGraphTwoModelList = new ArrayList<SVTModel>();
	    		for(int i = 0;i<SVTModels.size();i++){
	    			if(aSVTRAModel.getRadio().equals(SVTModels.get(i).getRadio()) && aSVTRAModel.getAge().equals(SVTModels.get(i).getAge())){
	    				newGraphTwoModelList.add(SVTModels.get(i));
	    				SVTModels.remove(i);
	    				i--;
	    				continue;
	    			}
	    		}
	    		aGraphTwo.setSVTModel(newGraphTwoModelList);
	    		graphTwo.add(aGraphTwo);
	    	}
	    	setGraphTwo(graphTwo);
			return graphTwo;	
		}
	    
    //下载全部计算结果start
    public void downloadAllCalResult() throws Exception{
        getAllOutputFile();
	}

	@SuppressWarnings("deprecation")
	public void getAllOutputFile() throws Exception{
			String[] title = null;
			SqlSession session = Constants.getSqlSession();
    		SaveEDao saveEDao = session.getMapper(SaveEDao.class);
            List<Radio> radioList = saveEDao.queryRadioListNorUser();  
            List<SaveE> raeList = saveEDao.queryRAEListNorUser();
            List<SVTModel> SVTList = saveEDao.querySVTListNorUser();
            List<SVTRAModel> SVTRAList = saveEDao.querySVTRAListNorUser();
            List<GraphOneModel> GraphOneList = saveEDao.queryLKJListNorUser();
            session.close();
	        HSSFWorkbook workbook = null;  
	  
	        try {  
	  
	            workbook = new HSSFWorkbook();// 创建Excel  
	            HSSFSheet sheet = null; // 工作表  
	            HSSFRow row = null; // 行  
	            HSSFCell cell = null; // 行--列  
	  
	            // 字体  
	            HSSFFont font = workbook.createFont();  
	            font.setColor(HSSFColor.BLACK.index);  
	            font.setFontHeightInPoints((short) 10);  
	            HSSFCellStyle pStyle = workbook.createCellStyle();  
	            pStyle.setFont(font);  
	            pStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	            
	            HSSFFont tfont = workbook.createFont();  
	            tfont.setColor(HSSFColor.BLACK.index);  
	            tfont.setFontHeightInPoints((short) 11);  
	            HSSFCellStyle tStyle = workbook.createCellStyle();  
	            tStyle.setFont(tfont);  
	            tStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	            tfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
	            
	            HSSFFont rfont = workbook.createFont();  
	            rfont.setColor(HSSFColor.RED.index);  
	            rfont.setFontHeightInPoints((short) 10); 
	            HSSFCellStyle rStyle = workbook.createCellStyle();  
	            rStyle.setFont(rfont);  
	            rStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	            //国标比较表 lkjtable start
	            title = "核素,食物类型,剂量限值,实际受照剂量".split(",");  
	            sheet = workbook.createSheet("实际受照剂量与剂量限值"); // 工作簿  
	            sheet.setDefaultColumnWidth(16); 
	            sheet.setColumnWidth(4,17000); 
	            row = sheet.createRow(0);  	  
	            for (int j = 0; j < title.length; j++) {  
	                cell = row.createCell(j);  
	                cell.setCellValue(new HSSFRichTextString(title[j]));  
	                cell.setCellStyle(tStyle); 
	            }
	            cell = row.createCell(4);  
                cell.setCellValue("注：剂量限值及实际受照剂量单位为Bq/kg, Th和U的单位为mg/kg");
                cell.setCellStyle(rStyle);
                
	            for(GraphOneModel aGraphOneModel:GraphOneList) {  
	            	row = sheet.createRow(GraphOneList.indexOf(aGraphOneModel)+1);  
	                cell = row.createCell(0);  
                    cell.setCellValue(aGraphOneModel.getRadio());
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(1);  
                    cell.setCellValue(aGraphOneModel.getFoodType());
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(2);  
                    cell.setCellValue(aGraphOneModel.getLKJ());
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(3);
                    cell.setCellValue(aGraphOneModel.getContaver());                   
	                cell.setCellStyle(pStyle);
	            } 
	            //国标比较表 lkjtable end
	            
	            //总受照剂量与剂量限值之比 ra start
	            title = "核素,单位,总受照剂量与剂量限值之比".split(",");  
	            sheet = workbook.createSheet("总受照剂量与剂量限值之比"); // 工作簿  
	            sheet.setDefaultColumnWidth(25); 
	            row = sheet.createRow(0);  	  
	            for (int j = 0; j < title.length; j++) {  
	                cell = row.createCell(j);  
	                cell.setCellValue(new HSSFRichTextString(title[j]));  
	                cell.setCellStyle(tStyle); 
	            }
	            for(Radio aRadio:radioList) {
	            	row = sheet.createRow(radioList.indexOf(aRadio)+1);
	            	if(aRadio.getIfExceedSum()>1){
	            		cell = row.createCell(0);  
	                    cell.setCellValue(aRadio.getRadio());
		                cell.setCellStyle(pStyle);
		                cell = row.createCell(1);  
	                    cell.setCellValue(aRadio.getUnit());
		                cell.setCellStyle(pStyle);
		                cell = row.createCell(2);
	                    cell.setCellValue(aRadio.getIfExceedSum()); 
		                cell.setCellStyle(rStyle);
	            	}
	            	else{
	            		cell = row.createCell(0);  
	                    cell.setCellValue(aRadio.getRadio());
		                cell.setCellStyle(pStyle);
		                cell = row.createCell(1);  
	                    cell.setCellValue(aRadio.getUnit());
		                cell.setCellStyle(pStyle);
		                cell = row.createCell(2);
	                    cell.setCellValue(aRadio.getIfExceedSum()); 
		                cell.setCellStyle(pStyle);
	            	}
	            } 
	            row = sheet.createRow(radioList.size()+2);
	            cell = row.createCell(2);  
                cell.setCellValue("红色表示检测值>1");
                cell.setCellStyle(rStyle);
	            //总受照剂量与剂量限值之比 ra end

	          //各食物实际受照有效剂量 rae start
	            title = "核素,年龄,食物类型,食物名称,实际受照剂量单位,实际受照剂量,实际受照有效剂量(Sv)".split(",");  
	            sheet = workbook.createSheet("各食物实际受照有效剂量"); // 工作簿  
	            sheet.setDefaultColumnWidth(19); 
	            row = sheet.createRow(0);  	  
	            for (int j = 0; j < title.length; j++) {  
	                cell = row.createCell(j);  
	                cell.setCellValue(new HSSFRichTextString(title[j]));  
	                cell.setCellStyle(tStyle); 
	            }
	            for(SaveE aSaveE:raeList) {  
	            	row = sheet.createRow(raeList.indexOf(aSaveE)+1);  
	                cell = row.createCell(0);  
                    cell.setCellValue(aSaveE.getRadio());
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(1);  
                    cell.setCellValue(aSaveE.getAge());
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(2);  
                    cell.setCellValue(aSaveE.getFoodType());
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(3);  
                    cell.setCellValue(aSaveE.getFoodName());
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(4);  
                    cell.setCellValue(aSaveE.getUnit());
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(5);  
                    cell.setCellValue(aSaveE.getContent());
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(6);  
                    cell.setCellValue(aSaveE.getSVi());
	                cell.setCellStyle(pStyle);
	            } 
	            //各食物实际受照有效剂量 rae end
	            
	          //不同食物类别的总受照有效剂量 svttable start
	            title = "核素,年龄,食物类型,该类食物消费量(g),类别个数,实际受照有效剂量(Sv)".split(",");  
	            sheet = workbook.createSheet("不同食物类别的总受照有效剂量"); // 工作簿  
	            sheet.setDefaultColumnWidth(20); 
	            row = sheet.createRow(0);  	  
	            for (int j = 0; j < title.length; j++) {  
	                cell = row.createCell(j);  
	                cell.setCellValue(new HSSFRichTextString(title[j]));  
	                cell.setCellStyle(tStyle); 
	            }
	            for(SVTModel aSVTModel:SVTList) {  
	            	row = sheet.createRow(SVTList.indexOf(aSVTModel)+1);  
	                cell = row.createCell(0);  
                    cell.setCellValue(aSVTModel.getRadio());
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(1);  
                    cell.setCellValue(aSVTModel.getAge());
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(2);  
                    cell.setCellValue(aSVTModel.getFoodType());
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(3);  
                    cell.setCellValue(aSVTModel.getFoodConsume());                   
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(4);  
                    cell.setCellValue(aSVTModel.getFoodCount());
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(5);  
                    cell.setCellValue(aSVTModel.getSVT());
	                cell.setCellStyle(pStyle);
	            } 
	            //不同食物类别的总受照有效剂量 svttable end
	            
	          //指定核素在指定人群中的有效剂量 svtratable start
	            title = "核素,年龄,体内有效剂量(Sv)".split(",");  
	            sheet = workbook.createSheet("指定核素在指定人群中的有效剂量"); // 工作簿  
	            sheet.setDefaultColumnWidth(23); 
	            row = sheet.createRow(0);  	  
	            for (int j = 0; j < title.length; j++) {  
	                cell = row.createCell(j);  
	                cell.setCellValue(new HSSFRichTextString(title[j]));  
	                cell.setCellStyle(tStyle); 
	            }
	            for(SVTRAModel aSVTRAModel:SVTRAList) {  
	            	row = sheet.createRow(SVTRAList.indexOf(aSVTRAModel)+1);  
	                cell = row.createCell(0);  
                    cell.setCellValue(aSVTRAModel.getRadio());
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(1);  
                    cell.setCellValue(aSVTRAModel.getAge());
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(2);  
                    cell.setCellValue(aSVTRAModel.getSVTRA());
	                cell.setCellStyle(pStyle);
	            } 
	            //指定核素在指定人群中的有效剂量 svtratable end
	            
	            //excel与前端交互start
	            HttpServletResponse response = ServletActionContext.getResponse();
	            OutputStream out=response.getOutputStream();
	            response.setHeader("Content-Type","application/vnd.ms-excel");
	            response.addHeader("Content-Disposition","attachment;filename=AllCalResult.xls");
	            response.setContentType("application/octet-stream");
	            response.setCharacterEncoding("UTF-8");
	            workbook.write(out);//转成buffer可以弹出下载路径，但找不到workbook转成byte[]的正确方式
	            out.flush();
	            out.close();	           
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	//下载全部计算end
	//下载部分计算start
	public void getAskOutputFile(List<String> radios,List<String> ages) throws Exception{
		String[] title = null;
		SqlSession session = Constants.getSqlSession();
		SaveEDao saveEDao = session.getMapper(SaveEDao.class);
        List<Radio> radioList = saveEDao.queryRadioByRadiosNorUser(radios);
        List<SaveE> raeList = saveEDao.queryRAEByRadiosNorUser(radios);
        List<SVTModel> SVTList = saveEDao.querySVTByRadiosNorUser(radios);
        List<SVTRAModel> SVTRAList = saveEDao.querySVTRAByRadiosNorUser(radios);
        List<GraphOneModel> GraphOneList = saveEDao.queryLKJByRadiosNorUser(radios);
        session.close();
        for(int i=0;i<raeList.size();i++){
        	if(!ages.contains(raeList.get(i).getAge())){
        		raeList.remove(i);
        		i--;
        	}
        }
        for(int i=0;i<SVTList.size();i++){
        	if(!ages.contains(SVTList.get(i).getAge())){
        		SVTList.remove(i);
        		i--;
        	}
        }
        for(int i=0;i<SVTRAList.size();i++){
        	if(!ages.contains(SVTRAList.get(i).getAge())){
        		SVTRAList.remove(i);
        		i--;
        	}
        }
        
        HSSFWorkbook workbook = null;  
  
        try {  
  
            workbook = new HSSFWorkbook();// 创建Excel  
            HSSFSheet sheet = null; // 工作表  
            HSSFRow row = null; // 行  
            HSSFCell cell = null; // 行--列  
  
            // 字体  
            HSSFFont font = workbook.createFont();  
            font.setColor(HSSFColor.BLACK.index);  
            font.setFontHeightInPoints((short) 10);  
            HSSFCellStyle pStyle = workbook.createCellStyle();  
            pStyle.setFont(font);  
            pStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            
            HSSFFont tfont = workbook.createFont();  
            tfont.setColor(HSSFColor.BLACK.index);  
            tfont.setFontHeightInPoints((short) 11);  
            HSSFCellStyle tStyle = workbook.createCellStyle();  
            tStyle.setFont(tfont);  
            tStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            tfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
            
            HSSFFont rfont = workbook.createFont();  
            rfont.setColor(HSSFColor.RED.index);  
            rfont.setFontHeightInPoints((short) 10); 
            HSSFCellStyle rStyle = workbook.createCellStyle();  
            rStyle.setFont(rfont);  
            rStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //国标比较表 lkjtable start
            title = "核素,食物类型,剂量限值,实际受照剂量".split(",");  
            sheet = workbook.createSheet("实际受照剂量与剂量限值"); // 工作簿  
            sheet.setDefaultColumnWidth(16); 
            sheet.setColumnWidth(4,17000); 
            row = sheet.createRow(0);  	  
            for (int j = 0; j < title.length; j++) {  
                cell = row.createCell(j);  
                cell.setCellValue(new HSSFRichTextString(title[j]));  
                cell.setCellStyle(tStyle); 
            }
            cell = row.createCell(4);  
            cell.setCellValue("注：剂量限值及实际受照剂量单位为Bq/kg, Th和U的单位为mg/kg");
            cell.setCellStyle(rStyle);
            
            for(GraphOneModel aGraphOneModel:GraphOneList) {  
            	row = sheet.createRow(GraphOneList.indexOf(aGraphOneModel)+1);  
                cell = row.createCell(0);  
                cell.setCellValue(aGraphOneModel.getRadio());
                cell.setCellStyle(pStyle);
                cell = row.createCell(1);  
                cell.setCellValue(aGraphOneModel.getFoodType());
                cell.setCellStyle(pStyle);
                cell = row.createCell(2);  
                cell.setCellValue(aGraphOneModel.getLKJ());
                cell.setCellStyle(pStyle);
                cell = row.createCell(3);
                cell.setCellValue(aGraphOneModel.getContaver());                   
                cell.setCellStyle(pStyle);
            } 
            //国标比较表 lkjtable end
            
            //总受照剂量与剂量限值之比 ra start
            title = "核素,单位,总受照剂量与剂量限值之比".split(",");  
            sheet = workbook.createSheet("总受照剂量与剂量限值之比"); // 工作簿  
            sheet.setDefaultColumnWidth(25); 
            row = sheet.createRow(0);  	  
            for (int j = 0; j < title.length; j++) {  
                cell = row.createCell(j);  
                cell.setCellValue(new HSSFRichTextString(title[j]));  
                cell.setCellStyle(tStyle); 
            }
            for(Radio aRadio:radioList) {
            	row = sheet.createRow(radioList.indexOf(aRadio)+1);
            	if(aRadio.getIfExceedSum()>1){
            		cell = row.createCell(0);  
                    cell.setCellValue(aRadio.getRadio());
	                cell.setCellStyle(rStyle);
	                cell = row.createCell(1);  
                    cell.setCellValue(aRadio.getUnit());
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(2);
                    cell.setCellValue(aRadio.getIfExceedSum()); 
	                cell.setCellStyle(rStyle);
            	}
            	else{
            		cell = row.createCell(0);  
                    cell.setCellValue(aRadio.getRadio());
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(1);  
                    cell.setCellValue(aRadio.getUnit());
	                cell.setCellStyle(pStyle);
	                cell = row.createCell(2);
                    cell.setCellValue(aRadio.getIfExceedSum()); 
	                cell.setCellStyle(pStyle);
            	}
            } 
            row = sheet.createRow(radioList.size()+2);
            cell = row.createCell(2);  
            cell.setCellValue("红色表示检测值>1");
            cell.setCellStyle(rStyle);
            //总受照剂量与剂量限值之比 ra end

          //各食物实际受照有效剂量 rae start
            title = "核素,年龄,食物类型,食物名称,实际受照剂量单位,实际受照剂量,实际受照有效剂量(Sv)".split(",");  
            sheet = workbook.createSheet("各食物实际受照有效剂量"); // 工作簿  
            sheet.setDefaultColumnWidth(19); 
            row = sheet.createRow(0);  	  
            for (int j = 0; j < title.length; j++) {  
                cell = row.createCell(j);  
                cell.setCellValue(new HSSFRichTextString(title[j]));  
                cell.setCellStyle(tStyle); 
            }
            for(SaveE aSaveE:raeList) {  
            	row = sheet.createRow(raeList.indexOf(aSaveE)+1);  
                cell = row.createCell(0);  
                cell.setCellValue(aSaveE.getRadio());
                cell.setCellStyle(pStyle);
                cell = row.createCell(1);  
                cell.setCellValue(aSaveE.getAge());
                cell.setCellStyle(pStyle);
                cell = row.createCell(2);  
                cell.setCellValue(aSaveE.getFoodType());
                cell.setCellStyle(pStyle);
                cell = row.createCell(3);  
                cell.setCellValue(aSaveE.getFoodName());
                cell.setCellStyle(pStyle);
                cell = row.createCell(4);  
                cell.setCellValue(aSaveE.getUnit());
                cell.setCellStyle(pStyle);
                cell = row.createCell(5);  
                cell.setCellValue(aSaveE.getContent());
                cell.setCellStyle(pStyle);
                cell = row.createCell(6);  
                cell.setCellValue(aSaveE.getSVi());
                cell.setCellStyle(pStyle);
            } 
            //各食物实际受照有效剂量 rae end
            
          //不同食物类别的总受照有效剂量 svttable start
            title = "核素,年龄,食物类型,该类食物消费量(g),类别个数,实际受照有效剂量(Sv)".split(",");  
            sheet = workbook.createSheet("不同食物类别的总受照有效剂量"); // 工作簿  
            sheet.setDefaultColumnWidth(20); 
            row = sheet.createRow(0);  	  
            for (int j = 0; j < title.length; j++) {  
                cell = row.createCell(j);  
                cell.setCellValue(new HSSFRichTextString(title[j]));  
                cell.setCellStyle(tStyle); 
            }
            for(SVTModel aSVTModel:SVTList) {  
            	row = sheet.createRow(SVTList.indexOf(aSVTModel)+1);  
                cell = row.createCell(0);  
                cell.setCellValue(aSVTModel.getRadio());
                cell.setCellStyle(pStyle);
                cell = row.createCell(1);  
                cell.setCellValue(aSVTModel.getAge());
                cell.setCellStyle(pStyle);
                cell = row.createCell(2);  
                cell.setCellValue(aSVTModel.getFoodType());
                cell.setCellStyle(pStyle);
                cell = row.createCell(3);  
                cell.setCellValue(aSVTModel.getFoodConsume());                   
                cell.setCellStyle(pStyle);
                cell = row.createCell(4);  
                cell.setCellValue(aSVTModel.getFoodCount());
                cell.setCellStyle(pStyle);
                cell = row.createCell(5);  
                cell.setCellValue(aSVTModel.getSVT());
                cell.setCellStyle(pStyle);
            } 
            //不同食物类别的总受照有效剂量 svttable end
            
          //指定核素在指定人群中的有效剂量 svtratable start
            title = "核素,年龄,体内有效剂量(Sv)".split(",");  
            sheet = workbook.createSheet("指定核素在指定人群中的有效剂量"); // 工作簿  
            sheet.setDefaultColumnWidth(23); 
            row = sheet.createRow(0);  	  
            for (int j = 0; j < title.length; j++) {  
                cell = row.createCell(j);  
                cell.setCellValue(new HSSFRichTextString(title[j]));  
                cell.setCellStyle(tStyle); 
            }
            for(SVTRAModel aSVTRAModel:SVTRAList) {  
            	row = sheet.createRow(SVTRAList.indexOf(aSVTRAModel)+1);  
                cell = row.createCell(0);  
                cell.setCellValue(aSVTRAModel.getRadio());
                cell.setCellStyle(pStyle);
                cell = row.createCell(1);  
                cell.setCellValue(aSVTRAModel.getAge());
                cell.setCellStyle(pStyle);
                cell = row.createCell(2);  
                cell.setCellValue(aSVTRAModel.getSVTRA());
                cell.setCellStyle(pStyle);
            } 
            //指定核素在指定人群中的有效剂量 svtratable end
            
            
            //excel与前端交互start
            HttpServletResponse response = ServletActionContext.getResponse();
            OutputStream out=response.getOutputStream();
            response.setHeader("Content-Type","application/vnd.ms-excel");
//            response.addHeader("Content-Disposition","attachment;filename=ChooseCalResult.xls");
            response.addHeader("Content-Disposition","attachment;filename="+new String("评估结果.xls".getBytes("GB2312"),"iso8859-1"));
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            workbook.write(out);//转成buffer可以弹出下载路径，但找不到workbook转成byte[]的正确方式
            out.flush();
            out.close();	           
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
	public void downloadAskCalResult() throws Exception{
	        
	        getAskOutputFile(radioList,ageList);
		
	}
    //下载计算部分end
	
	//下载结论部分start
		public void downloadConclusion() throws Exception{
			
			HashSet<String> artiRadio= new HashSet<String>();
			HashSet<String> natrRadio= new HashSet<String>();
			HashMap<String, String> aRadioNameMap = new HashMap<String, String>();
			natrRadio.add("210Po");
			natrRadio.add("226Ra");
			natrRadio.add("238U");
			natrRadio.add("232Th");
			artiRadio.add("89Sr");
			artiRadio.add("90Sr");
			artiRadio.add("131I");
			artiRadio.add("137Cs");
			artiRadio.add("239Pu");
			aRadioNameMap.put("210Po", "Po-210");
			aRadioNameMap.put("226Ra", "Ra-226");
			aRadioNameMap.put("238U", "U-238");
			aRadioNameMap.put("232Th", "Th-232");
			aRadioNameMap.put("89Sr", "Sr-89");
			aRadioNameMap.put("90Sr", "Sr-90");
			aRadioNameMap.put("131I", "I-131");
			aRadioNameMap.put("137Cs", "Cs-137");
			aRadioNameMap.put("239Pu", "Pu-239");
			DecimalFormat fotmat = new DecimalFormat("#.###E0");
			
			SqlSession session = Constants.getSqlSession();
			SaveEDao saveEDao = session.getMapper(SaveEDao.class);
	        List<Radio> radioClassList = saveEDao.queryRadioByRadiosNorUser(radioList);  
	        List<SVTRAModel> SVTRAList = saveEDao.querySVTRAByRadiosNorUser(radioList);
	        session.close();
			
	        for(int i=0;i<SVTRAList.size();i++){
	        	if(!ageList.contains(SVTRAList.get(i).getAge())){
	        		SVTRAList.remove(i);
	        		i--;
	        	}
	        }
	        //radioList,ageList String型的已获得
			
			  XWPFDocument doc = new XWPFDocument(); 
			  XWPFRun run,run1,redRun;
		      //创建一个段落  
		      XWPFParagraph para = doc.createParagraph();  
		      run = para.createRun();  
		      run.setFontSize(11);
		      run.setText("根据相应年龄组别的人群食物消费量及相关放射性核素检测数据，食品放射性风险评估结论如下：");  
		      run.setBold(true);
		      for(Radio aRadio:radioClassList){
		    	  para = doc.createParagraph(); 
		    	  para = doc.createParagraph(); 
			      run1 = para.createRun(); 
			      run1.setFontSize(10);
			      run1.setText("	在所给定的膳食结构下，"); 
			      if(artiRadio.contains(aRadio.getRadio())){
			    	  run1.setText("人工放射性核素");
			      }else{
			    	  run1.setText("天然放射性核素");
			      }
			      redRun = para.createRun(); 
			      redRun.setFontSize(10);
			      redRun.setColor("FF0000");
			      redRun.setText(aRadioNameMap.get(aRadio.getRadio()));
			      run1 = para.createRun(); 
			      run1.setFontSize(10);
			      run1.setText("的人体实际受照剂量为：");
			      redRun = para.createRun(); 
			      redRun.setFontSize(10);
			      redRun.setColor("FF0000");
			      if(aRadio.getIfExceedSum()==0){
			    	  redRun.setText("0");
			      }else{
			      	redRun.setText(""+fotmat.format(aRadio.getIfExceedSum()));
			      }
			      run1 = para.createRun(); 
			      run1.setFontSize(10);
			      run1.setText(" "+aRadio.getUnit()+"，");
			      if(aRadio.getIfExceedSum()<1){
			    	  redRun = para.createRun(); 
				      redRun.setFontSize(10);
				      redRun.setColor("FF0000");
				      redRun.setText("未超出");
				   
			      }else{
			    	  redRun = para.createRun(); 
				      redRun.setFontSize(10);
				      redRun.setColor("FF0000");
				      redRun.setText("超出");
			      }
			      run1 = para.createRun(); 
			      run1.setFontSize(10);
			      run1.setText("国家规定的剂量限值。其中：");
			      for(SVTRAModel aSVTRAModel:SVTRAList){
			    	  if(aSVTRAModel.getRadio().equals(aRadio.getRadio())){
			    		  para = doc.createParagraph();
			    		  run1 = para.createRun(); 
					      run1.setFontSize(10);
					      run1.setText("	在");
					      redRun = para.createRun(); 
					      redRun.setFontSize(10);
					      redRun.setColor("FF0000");
					      redRun.setText(aSVTRAModel.getAge());
					      run1 = para.createRun(); 
					      run1.setFontSize(10);
					      run1.setText("岁的人群中实际受照有效剂量为：");
					      redRun = para.createRun(); 
					      redRun.setFontSize(10);
					      redRun.setColor("FF0000");
					      if(aSVTRAModel.getSVTRA() == 0){
					    	  redRun.setText("0");
					      }else{
						      redRun.setText(fotmat.format(aSVTRAModel.getSVTRA())+"");
					      }
					      run1 = para.createRun(); 
					      run1.setFontSize(10);
					      run1.setText(" Sv；");
			    	  }
			      }
		      }
		      
		      
		      HttpServletResponse response = ServletActionContext.getResponse();
	          OutputStream out=response.getOutputStream();
	          response.setHeader("Content-Type","application/vnd.ms-excel");
	          response.addHeader("Content-Disposition","attachment;filename="+new String("评估结论.docx".getBytes("GB2312"),"iso8859-1"));
	          response.setContentType("application/octet-stream");
	          response.setCharacterEncoding("UTF-8");
	          doc.write(out);
	          out.flush();
	          out.close();	     
		      
		}
		//下载结论部分end
}