package com.action;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.ServletActionContext;

import com.dao.SaveEDao;
import com.model.Radio;


public class DownloadAllResultAction {
    private static int NUM = 10000;// 一个sheet的记录数  
    private static String level1 = "1";  
    private static String level2 = "2";  
    private static String level3 = "3";  
    private static String level4 = "4";  
	public void downloadCalResult() throws Exception{
		System.out.println("downloadCalResult执行了");
		 String[] title = "ID,核素,活化度,单位,是否超出".split(",");  
	        String[] column = "id,radio,activity,unit,ifExceedSum".split(",");  
	        String fileName = "ExportExcelByMyBatis";  
	        SqlSession session = Constants.getSqlSession();
			SaveEDao saveEDao = session.getMapper(SaveEDao.class);
	        List<Radio> data = saveEDao.queryRadioList();  
	        session.close();
	        getOutputFile(title, column, data, fileName);
		
	}
	public void getOutputFile(String[] title, String[] column,List<Radio> data, String fileName) throws Exception{
		 if (title == null || title.equals("")) {  
	            System.out.println("Excel表格 标题(表头)为空");  
	        }  
	        if (column == null || column.equals("")) {  
	            System.out.println("没有定义取值字段集合");  
	        }  
	        if (data == null || data.equals("")) {  
	            System.out.println("没有定义导出数据集合");  
	        }  
	        if (fileName == null || fileName.equals("")) {  
	            System.out.println("没有定义输出文件名");  
	        }  
	  
	        HSSFWorkbook workbook = null;  
	  
	        try {  
	            HttpServletResponse response = ServletActionContext.getResponse();
	  
	            workbook = new HSSFWorkbook();// 创建Excel  
	            HSSFSheet sheet = null; // 工作表  
	            HSSFRow row = null; // 行  
	            HSSFCell cell = null; // 行--列  
	  
	            Iterator it = data.iterator();  
	            int i = 0;  
	  
	            // 字体  
	            HSSFFont font = workbook.createFont();  
	            font.setColor(HSSFColor.BLACK.index);  
	            font.setFontHeightInPoints((short) 10);  
	            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
	  
	            // 父告警标题样式  
	            HSSFCellStyle pStyle = workbook.createCellStyle();  
	            pStyle.setFont(font);  
	  
	            // 子告警标题样式  
	            // HSSFCellStyle sStyle = workbook.createCellStyle();  
	            // sStyle.setFont(font);  
	            // sStyle.setFillBackgroundColor((short) 0x3399CC);  
	  
	            // 告警样式  
	            HSSFCellStyle level1Style = workbook.createCellStyle();  
	            HSSFPalette palette = workbook.getCustomPalette();  
	            palette.setColorAtIndex((short) 9, (byte) (0xFF), (byte) (0x00),  
	                    (byte) (0x00));  
	            palette.setColorAtIndex((short) 10, (byte) (0xFF), (byte) (0xA5),  
	                    (byte) (0x00));  
	            palette.setColorAtIndex((short) 11, (byte) (0xFF), (byte) (0xFF),  
	                    (byte) (0x00));  
	            palette.setColorAtIndex((short) 12, (byte) (0x41), (byte) (0x69),  
	                    (byte) (0xE1));  
	            level1Style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	            level1Style.setFillForegroundColor((short) 9);  
	            HSSFCellStyle level2Style = workbook.createCellStyle();  
	            level2Style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	            level2Style.setFillForegroundColor((short) 10);  
	            HSSFCellStyle level3Style = workbook.createCellStyle();  
	            level3Style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	            level3Style.setFillForegroundColor((short) 11);  
	            HSSFCellStyle level4Style = workbook.createCellStyle();  
	            level4Style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	            level4Style.setFillForegroundColor((short) 12);  
	  
	              
	            sheet = workbook.createSheet("Sheet0"); // 工作簿  
	            row = sheet.createRow(0);  
	  
	            // 在每一页的第一行输入标题  
	            for (int j = 0; j < title.length; j++) {  
	                cell = row.createCell(j);  
	                cell.setCellValue(new HSSFRichTextString(title[j]));  
	                cell.setCellStyle(pStyle);  
	            }  
	  

	            
	            OutputStream out=response.getOutputStream();
	            response.setHeader("Content-Type","application/vnd.ms-excel");
	            response.addHeader("Content-Disposition","attachment;filename=mydata.xls");
	            response.setContentType("application/octet-stream");
	            response.setCharacterEncoding("UTF-8");
	            workbook.write(out);//转成buffer可以弹出下载路径，但找不到workbook转成byte[]的正确方式
	            //out.write(workbook.getBytes());
	            out.flush();
	            out.close();
	            
//	            ByteArrayOutputStream out = new ByteArrayOutputStream();
//	            response.setHeader("Content-Type","application/vnd.ms-excel");
//	            response.addHeader("Content-Disposition","attachment;filename=mydata.xls");
//	            response.setContentType("application/octet-stream");
//	            response.setCharacterEncoding("UTF-8");
//	            workbook.write(out);//转成buffer可以弹出下载路径，但找不到workbook转成byte[]的正确方式
//	            byte[] bytes = out.toByteArray();
//	            out.write(bytes);
//	            out.flush();
//	            out.close();
	            
//	        	FileOutputStream fos;  
//	            fos = new FileOutputStream("D://" + fileName + ".xls");  
//	            workbook.write(fos);  
//	            fos.flush();  
//	            fos.close();  
	            System.out.println("Excel 文件导出完成");  
	        } catch (Exception e) {  
	            System.out.println("Excel导出错误: ");  
	            e.printStackTrace();  
	        }  
			System.out.println("downloadCalResult执行了enddd");
	    }  
}