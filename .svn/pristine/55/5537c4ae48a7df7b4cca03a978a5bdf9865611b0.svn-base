package com.eastdawn.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.eastdawn.common.PageUtil;
import com.eastdawn.dao.LogLogonDao;
import com.eastdawn.dao.UserDao;
import com.eastdawn.util.DESHelper;
import com.eastdawn.bo.UserBO;
import com.eastdawn.common.PageAction;
import com.eastdawn.po.User;
import com.eastdawn.util.StaticName;

@SuppressWarnings("serial")
public class UserLogAction extends PageAction {
	
	private Long userId;//用户ID
	private String logName;//用户登录名
	private String imageCode;//验证码
	private String logPwd;//密码
	private String status;//用户状态
	private User user;
	private String checked;
	
	private UserDao userDao;
	private UserBO userBO;
	private String password;
	private List userList;
	private LogLogonDao logLogonDao;
	
	public String logon() throws IOException {
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		List<User> userList = null;
		
		try {
			System.out.println(DESHelper.encode(this.logPwd.trim())+"----"+this.logPwd.trim());
			if (this.logName != null && !this.logName.trim().equals("")) {
				queryMap.put("logName", this.logName.trim());
				queryMap.put("status", 1);
			}
			if (this.logPwd != null && !this.logPwd.trim().equals("")) {
				queryMap.put("logPwd", DESHelper.encode(this.logPwd.trim()));
			}
			
			queryMap.put(PageUtil.NUM_END, 2);
			
			userList = this.userDao.queryUserByPage(queryMap);
			System.out.println(userList.size());
			if(userList.size() != 0){
				session.setAttribute(StaticName.session_msg, null);
				session.setAttribute("logname", userList.get(0).getLogName());
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().write("1");
			return StaticName.logon_user;
		}
		
		if (userList == null || userList.size() != 1) {
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().write("1");
			return null;
		}else{
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().write("2");
			try {
				User user = userList.get(0);
				queryMap = new HashMap();
				queryMap.put("name", user.getLogName());
				queryMap.put("times", 1);
				queryMap.put("ip", ServletActionContext.getRequest().getRemoteAddr());
				queryMap.put("time", new Date());
				this.logLogonDao.add(queryMap);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	public void logout() throws IOException {
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		session.setAttribute(StaticName.session_msg, StaticName.logout_msg);
		session.setAttribute("logname", null);
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().write("1");
	}
	
	public String getUserName() throws IOException {
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		String log = (String) session.getAttribute("logname");
		response.setCharacterEncoding("UTF-8"); 
		if(log == null){
			response.getWriter().write("1");
		}else{
			response.getWriter().write(log);
		}
		return null;
	}
	
	//用户名查询
	public String searchName() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map queryMap = new HashMap();
		try {
			if(this.logName!=null && !this.logName.equals("")){
				queryMap.put("logName", this.logName);
			}else{
				queryMap.put("logName", "12BNcv3d");
			}
			userList=userDao.queryUserByPage(queryMap);
			System.out.println(userList.size()+"---"+userList);
			if (userList != null && userList.size()> 0) {
				response.setCharacterEncoding("UTF-8"); 
				response.getWriter().write("1");
			}else{
				System.out.println("用户名验证通过。");
			}
		} catch (Exception e) {
			// TODO: handle exceptio
			e.printStackTrace();
		}
		
		return null;
	}
	//添加用户 
	public String add() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map queryMap = new HashMap();
		User user = new User();
		try {
			user.setLogName(this.logName);
			user.setLogPwd(DESHelper.encode(logPwd));	
			user.setStatus("1");
			this.userId = userBO.add(user);
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().write("1");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	
	//类别添加方法
	public String beforeAdd() throws Exception {
	    return "add";
	}
	
	//查询用户
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();	
		Map queryMap = new HashMap();
		try {
			if(this.userId != null && !this.userId.equals("")){
			    queryMap.put("userId", userId);
			}
			if(this.status != null && !this.status.equals("")){
			    queryMap.put("status", status);
			}
			
			super.totalNum = userDao.getUserCountByPage(queryMap);
			super.setPageNum(10);
			super.totalPage = super.getTotalPage();
			queryMap.put(PageUtil.NUM_START, super.getNumStart());
			queryMap.put(PageUtil.NUM_END, super.getNumEnd());
			
			userList = userDao.queryUserByPage(queryMap);
		
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		return "search";	
	}
	
	//删除
	public String delete() throws Exception{
		System.out.println("Start to execute delete Action!");
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			userId = Long.parseLong(request.getParameter("userId"));
			this.deleteupdate();
			userDao.deleteById(userId);
		} catch (Exception e) {
			e.printStackTrace();
			super.addActionMessage("删除失败！");
		}
		this.execute();
		userId = null;
		return "search";
	}
	
	//删除更新
	public String deleteupdate() throws ParseException{
		
		User user = new User();
		
		user.setUserId(getUserId());
		user.setStatus("2");
		userDao.deleteUpdateById(user);
		
		super.addActionMessage("删除成功！");
		
		return "goDetail";
	}

	public String detail() throws ParseException{
		
		Map queryMap = new HashMap();
		queryMap.put("userId", userId);
		List userList = userDao.queryUserByPage(queryMap);
		for(int i=0;i<userList.size();i++){
			user = (User)userList.get(0);
		}
		return "detail";
	}
	
	//修改方法
	public String edit() throws ParseException{
		HttpServletRequest request = ServletActionContext.getRequest();	
        Map queryMap = new HashMap();
        queryMap.put("userId", userId);
        userList = userDao.queryUserByPage(queryMap);
		for(int i=0;i<userList.size();i++){
		user = (User)userList.get(0);
		}
		return "edit";
	}
	
	//密码修改
	public String passwordUpdate() throws Exception{
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute(StaticName.session_user);//从session中去值是个对象
		Map queryMap = new HashMap();
		
		User editUser = new User();
		editUser.setUserId(editUser.getUserId());
		if (password != null && !DESHelper.encode(password).equals(editUser.getLogPwd())) {
			System.out.println(DESHelper.encode(password));
			System.out.println(editUser.getLogPwd());
			super.addActionMessage("密码修改失败!");
			return "password";
		}else if(logPwd != null && logPwd.equals("")){
			System.out.println("111112211111");
			super.addActionMessage("密码修改失败!");
			return "password";
		}else{
			user.setLogPwd(DESHelper.encode(getLogPwd()));
			userBO.passwordUpdateById(user);
			
			editUser.setLogPwd(DESHelper.encode(getLogPwd()));
			
			super.addActionMessage("密码修改成功!");
			return "password";
		}
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	public String getLogPwd() {
		return logPwd;
	}

	public void setLogPwd(String logPwd) {
		this.logPwd = logPwd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserBO getUserBO() {
		return userBO;
	}

	public void setUserBO(UserBO userBO) {
		this.userBO = userBO;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List getUserList() {
		return userList;
	}

	public void setUserList(List userList) {
		this.userList = userList;
	}

	public LogLogonDao getLogLogonDao() {
		return logLogonDao;
	}

	public void setLogLogonDao(LogLogonDao logLogonDao) {
		this.logLogonDao = logLogonDao;
	}

	public String getChecked() {
		return checked;
	}
	
	public void setChecked(String checked) {
		this.checked = checked;
	}	
	
}