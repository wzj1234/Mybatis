package com.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.model.GraphOneHead;
import com.model.Radio;


public class DownloadAction {
	private String user;
    public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String DownloadActionFunc() throws Exception{
//    	String filePath = "原始数据.xlsx";
//      String path = ServletActionContext.getRequest().getRealPath("/file") + "/" + filePath; //old获取数据路径，被修改与0824
		System.out.println(getUser());
		String path = null;
		if(getUser().equals("admin")){
			System.out.println("应该在这里啊");
			path = ServletActionContext.getServletContext().getRealPath("/file/原始数据.xlsx");
		}
		else{
			System.out.println("不应该在这里啊");
			path = ServletActionContext.getServletContext().getRealPath("/file/标准表格-模板.xlsx");
		}
        System.out.println("path->"+path);
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1)
                    .toUpperCase();

            // 以流的形式下载文件。
            System.out.println("downlolad_path-->"+path);
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            String filenameString = new String(filename.getBytes("gbk"),
                    "iso-8859-1");
            response.addHeader("Content-Disposition", "attachment;filename="
                    + filenameString);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response
                    .getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (FileNotFoundException e){
        	System.out.println("can not find file!");
        	return "IOE";
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
        return null;
    }
	public String execute() throws Exception {
        
		System.out.println("execute函数");
        return DownloadActionFunc();
    }
}