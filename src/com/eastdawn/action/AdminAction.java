package com.eastdawn.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.eastdawn.bo.AdminBO;
import com.eastdawn.common.PageAction;
import com.eastdawn.dao.AdminDao;
import com.eastdawn.po.Admin;
import com.eastdawn.util.FileUtils;
import com.eastdawn.util.StaticName;

@SuppressWarnings("serial")
public class AdminAction extends PageAction {
	
	private AdminBO adminBO;
	private String adminName;
	private String adminPwd;
	private String userName;
	private String yanzhengma;
	private String validationMessage;
	private AdminDao adminDao;
	
	private List adminList;
	
	private Admin resultAdmin;
	
	@Override
	public String execute() throws Exception {
		adminBO.getAdminById(1);
		return SUCCESS;
	}
	
	
	public String logon() throws Exception {

	    HttpSession session = ServletActionContext.getRequest().getSession();
	    Integer imageCode = (Integer) session.getAttribute("imageCode");
	    
	    if (adminName == null || adminName.trim().equals("") && adminPwd == null || adminPwd.trim().equals("")) {
	    	super.addActionMessage("用户名或密码不能为空!");
			return "error";
		}
		
		if (yanzhengma == null || yanzhengma.trim().equals("") || imageCode == null || !yanzhengma.trim().equals(imageCode.toString())) {
			super.addActionMessage("验证码有误!");
			return "error";
		}
	    
	    Map queryMap = new HashMap();
	    
		try {
			if (adminName!=null && adminPwd!=null){
				queryMap.put("adminName", adminName);
				queryMap.put("adminPwd", adminPwd);
				adminList = adminDao.queryAdminByPage(queryMap);
			}
			if(adminList.size() > 0){
				Admin admin = (Admin) adminList.get(0);
				session.setAttribute(StaticName.session_admin, admin );

				return "success";
				
			} else {
				super.addActionMessage("请输入正确的用户名和密码!");
				return "error";
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			super.addActionMessage("请输入正确的用户名和密码!");
			return "error";
		}
	}
	
	public String logout() {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute(StaticName.session_admin, null );
		return "logout";
	}
	public void download() {
		
		FileUtils.download("c:\\你好.txt", ServletActionContext.getResponse());
		
	}

	public AdminBO getAdminBO() {
		return adminBO;
	}

	public void setAdminBO(AdminBO adminBO) {
		this.adminBO = adminBO;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public String getYanzhengma() {
		return yanzhengma;
	}

	public void setYanzhengma(String yanzhengma) {
		this.yanzhengma = yanzhengma;
	}

	public String getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}

	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public Admin getResultAdmin() {
		return resultAdmin;
	}

	public void setResultAdmin(Admin resultAdmin) {
		this.resultAdmin = resultAdmin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List getAdminList() {
		return adminList;
	}

	public void setAdminList(List adminList) {
		this.adminList = adminList;
	}

}
