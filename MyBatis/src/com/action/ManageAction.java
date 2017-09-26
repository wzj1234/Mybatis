package com.action;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import com.dao.SaveEDao;

	
public class ManageAction {
	
	private String managerName ;
	private String managerPSW ;
	private String loginStatus ;
	private String tempPSW ;
	public String getManagerName() {
        return managerName;
    }
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    public String getManagerPSW() {
        return managerPSW;
    }
    public void setManagerPSW(String managerPSW) {
        this.managerPSW = managerPSW;
    }
    public String getLoginStatus() {
        return loginStatus;
    }
    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }
    public String getPSW(String managerName) throws Exception {
    	SqlSession session = Constants.getSqlSession();
		SaveEDao saveEDao = session.getMapper(SaveEDao.class);
        String PSW = saveEDao.queryPSW(managerName);
        session.commit();
        session.close();
        return PSW;
	}
	public String execute() throws Exception {	
	tempPSW = getPSW(getManagerName());
	if(tempPSW == null){
		setLoginStatus("账号不存在");
        return "FAIL";
	}
	if(getManagerPSW().equals(tempPSW)){
		setLoginStatus("登陆成功");
        return "SUCCESS";
	}
	setLoginStatus("密码错误");
        return "FAIL";
    }
}