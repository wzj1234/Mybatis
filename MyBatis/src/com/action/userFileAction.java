package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.utils.ExcelUtil;
import com.utils.userExcelUtil;

public class userFileAction extends ActionSupport {
	private File file;
    private String fileFileName;
    private String fileFileContentType;

    private String message = "上传成功";
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileFileContentType() {
        return fileFileContentType;
    }

    public void setFileFileContentType(String fileFileContentType) {
        this.fileFileContentType = fileFileContentType;
    }

    @SuppressWarnings("deprecation")
    @Override
    public String execute() throws Exception {
        
        //String path = ServletActionContext.getRequest().getRealPath("/file");//第一版
        //String path = ServletActionContext.getServletContext().getRealPath("WEB-INF/upload/paydetail");
    	String path = ServletActionContext.getServletContext().getRealPath("/file");
        String ss = null;
        File savefile = new File(new File(path), "普通用户原始数据.xlsx");
        if (!savefile.getParentFile().exists())
		{
			savefile.getParentFile().mkdirs();
		}
        try {
            File f = this.getFile();
            System.out.println("f-->"+f);
            if(f==null){
                message="上传文件为空!!!";
                return ERROR;
            }
            if(this.getFileFileName().endsWith(".exe")){
                message="上传文件格式错误!!!";
                return ERROR;
            }
            FileInputStream inputStream = new FileInputStream(f);
            System.out.println(inputStream.available()+"文件长度");
            System.out.println("path-->"+path);
            System.out.println(getFileFileName());
            
            ss = path+"/"+"普通用户原始数据.xlsx";
//            ss = path+"\\" +"原始数据.xlsx";
            FileOutputStream outputStream = new FileOutputStream(ss);
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, length);
            }
            System.out.println("上传的路径为->"+ss);
            message = ss;
            inputStream.close();
            outputStream.flush();
            outputStream.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            message = "有错误!!!!";
        }
        System.out.println("准备读文件:"+getFileFileName()+";;;path="+path);
        System.out.println(ss);
        System.out.println(path);      
        savefile = new File(ss);
		userExcelUtil.readuserExcel(savefile);
        return SUCCESS;
    }
}
