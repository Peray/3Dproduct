package com.eastdawn.action;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Decoder;

import com.eastdawn.bo.FilesBO;
import com.eastdawn.bo.MetadataBO;
import com.eastdawn.bo.ServiceGlBO;
import com.eastdawn.dao.FilesDao;
import com.eastdawn.dao.LogsDao;
import com.eastdawn.dao.MetadataDao;
import com.eastdawn.dao.ServiceGlDao;
import com.eastdawn.po.Files;
import com.eastdawn.po.Logs;
import com.eastdawn.po.Metadata;
import com.eastdawn.po.ServiceGl;
import com.eastdawn.po.User;
import com.eastdawn.util.StaticName;

@SuppressWarnings("serial")
public class ServiceGlAction{
	
	private Long glId;//服务管理ID
	private String glNumber;//服务编号
	private String glName;//服务名称
	private String address;//地址
	private String content;//描述
	private Date fbTime;//服务发布时间
	private String fbPop;//发布人
	private String imgPath;//缩略图
	private Integer browse;//浏览量
	
	private Long statr;
	private Long totalNum;
	private String arltGeo;
	private String arltWms;
	private String fileName;
	private ServiceGl serviceGl;
	private ServiceGlDao glDao;
	private ServiceGlBO glBO;
	
	private Files files;
	private FilesDao fileDao;
	private FilesBO fileBO;
	private List glList;
	private List fileList;
	private List metaList;
	
	private Long dataId;//服务ID
	private String fwContent;//方位描述
	private String zdx;//地理信息坐标(最大X)
	private String zdy;//最大Y
	private String zxx;//最小X
	private String zxy;//最小Y
	private String fbl;//影像分辨率
	private String fwlx;//服务类型
	private String yxSource;//来源
	private String yxcjdw;//原始影像采集单位
	private String yxcldw;//影像处理单位
	private String yxsdr;//影像审定者
	private String yxsddw;//影像审定单位
	private String yxsdsj;//影像审定时间
	private String sjmj;//数据密集
	private String cqgs;//知识产权归属
    private String shPop;//审核人
    private String shTime;//审核时间
    private Long parentId;//所属记录ID
    private String sx;//时相
    private String wxlx;//卫星类型
    private String info;//查询关键字
    
    private String type;//分类查询名称
    private String val;//分类查询值
    
    private Long xgId;//管理ID
    
    private String sluUrl;//缩略图URL
    private String imgName;//缩略图名称
    private String editImgName;//编辑名称
    
    private MetadataDao metadataDao;
    private MetadataBO metadataBO;
    private LogsDao logsDao;
    
	//服务管理添加
	public String add() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletResponse response =ServletActionContext.getResponse();
		Map queryMap = new HashMap();
		ServiceGl gl = new ServiceGl();
		User user =(User)session.getAttribute(StaticName.session_user);
		System.out.println(this.imgName);
		try {
			gl.setGlName(this.glName);
			gl.setGlNumber(this.glNumber);
			gl.setFbTime(new Date());
			if(this.arltGeo != null && !this.arltGeo.equals("")){
				gl.setAddress(this.arltGeo);
			}
			if(this.arltWms != null && !this.arltWms.equals("")){
				gl.setAddress(this.arltWms);
			}
			gl.setContent(this.content);
			gl.setFbPop(user.getLogName());
			gl.setImgPath(this.imgName+".png");
			glBO.add(gl);
			glId = glDao.getGLById(queryMap);
			if(this.arltGeo != null && !this.arltGeo.equals("")){
				this.fileAdd();
			}
			this.metadataAdd();
			this.logAdd("服务管理-新增");
			response.sendRedirect("../Service-manag.html");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	
	public String fileAdd() throws Exception {
		Files files = new Files();
//		String []list=arlt.split("\\");
		String image = arltGeo.substring(arltGeo.lastIndexOf("/")+1);
	    files.setOnlinePath(image);
	    files.setFileName(image);
	    files.setParentId(this.glId);
	    fileBO.add(files);
	    return null;
	}
	
	public String metadataAdd() throws Exception {
		Metadata metadata = new Metadata();
		
		metadata.setFwContent(this.fwContent);
		metadata.setZdx(this.zdx);
		metadata.setZdy(this.zdy);
		metadata.setZxx(this.zxx);
		metadata.setZxy(this.zxy);
		metadata.setFbl(this.fbl);
		metadata.setFwlx(this.fwlx);
		metadata.setYxSource(this.yxSource);
		metadata.setYxcjdw(this.yxcjdw);
		metadata.setYxcldw(this.yxcldw);
		metadata.setYxsdr(this.yxsdr);
		metadata.setYxsddw(this.yxsddw);
		metadata.setYxsdsj(this.yxsdsj);
		metadata.setSjmj(this.sjmj);
		metadata.setCqgs(this.cqgs);
		metadata.setShPop(this.shPop);
		metadata.setShTime(this.shTime);
		metadata.setParentId(this.glId);
		metadata.setSx(this.sx);
		metadata.setWxlx(this.wxlx);
		metadataBO.add(metadata);
	    return null;
	}
	
	//服务查询
	public String execute() throws Exception {
		HttpServletResponse iResponse =ServletActionContext.getResponse();
		
		Map queryMap = new HashMap();
		try {
			if(this.glId != null && !this.glId.equals("")){
			    queryMap.put("glId", glId);
			}
			totalNum = glDao.getGLCountByPage(queryMap);
			System.out.println(totalNum);
			if(statr == null){
				queryMap.put("numStart", 0);
			}else{
				queryMap.put("numStart", (statr-1)*10);
			}
			glList = glDao.queryGLByPage(queryMap);
			
			JSONObject object = new JSONObject();
	        object.put("num", totalNum); 
	        object.put("list", glList); 
	        System.out.println(totalNum+object.toString());
	        iResponse.setCharacterEncoding("utf-8");
	        iResponse.getWriter().write(object.toString());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public String detail() throws ParseException, IOException{
//		this.update();
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletResponse iResponse =ServletActionContext.getResponse();
		Map queryMap = new HashMap();
		ServiceGl gl = new ServiceGl();
		try {
			queryMap.put("glId", glId);
			queryMap.put("parentId", glId);
			queryMap.put("parentId", glId);
			glList = glDao.queryGLByPage(queryMap);
			for(int i=0;i<glList.size();i++){
				gl = (ServiceGl)glList.get(0);
			}
			gl.setBrowse(gl.getBrowse()+1);
			glDao.updateBrowseById(gl);
			fileList = fileDao.queryFileByPage(queryMap);
			metaList = metadataDao.queryMetadataByPage(queryMap);
			
			JSONObject object = new JSONObject();  
	        object.put("list", glList); 
	        object.put("fileList", fileList);
	        object.put("metaList", metaList);
	        System.out.println(object.toString());
	        iResponse.setCharacterEncoding("utf-8");
	        iResponse.getWriter().write(object.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public void delete() throws Exception{
		System.out.println("Start to execute delete Action!");
		HttpServletResponse iResponse =ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map queryMap = new HashMap();
		String path = request.getSession().getServletContext().getRealPath("\\share");  
		
		try {
			glId = Long.parseLong(request.getParameter("glId"));
			queryMap.put("glId", glId);
			queryMap.put("parentId", glId);
			queryMap.put("parentId", glId);
			
			glList = glDao.queryGLByPage(queryMap);
			fileList = fileDao.queryFileByPage(queryMap);
			metaList = metadataDao.queryMetadataByPage(queryMap);
			
			for(int i=0;i<fileList.size();i++){
				files = (Files)fileList.get(i);
		        String filePath = path + "\\" + files.getOnlinePath();
		        System.out.println(filePath);
		        File file = new File(filePath);
		        System.out.println(file.exists() +"-----"+ file.isFile());
				if (file.exists() && file.isFile()) {
		           file.delete();
		           System.out.println("删除成功");
		        }
			}
			glDao.deleteById(glId);
			fileDao.deleteById(glId);
			metadataDao.deleteById(glId);
			this.logAdd("服务管理-删除");
			iResponse.getWriter().write("1");
		} catch (Exception e) {
			e.printStackTrace();
			iResponse.getWriter().write("2");
		}
	}
	
	//服务更新
	public void update() throws ParseException{
		HttpServletResponse response =ServletActionContext.getResponse();
		ServiceGl gl = new ServiceGl();
		try{
			xgId = getGlId();
			gl.setGlId(getGlId());
			gl.setGlName(getGlName());
			gl.setGlNumber(getGlNumber());
			if(this.arltGeo != null && !this.arltGeo.equals("")){
				gl.setAddress(this.arltGeo);
			}
			if(this.arltWms != null && !this.arltWms.equals("")){
				gl.setAddress(this.arltWms);
			}
			gl.setContent(getContent());
			gl.setFbTime(new Date());
			gl.setImgPath(this.editImgName);
			glBO.updateById(gl);
			if(this.arltGeo != null && !this.arltGeo.equals("")){
				this.fileUpdata();
			}
			this.metadataUpdata();
			this.logAdd("服务管理-更新ID为"+getGlId()+"的记录");
			response.sendRedirect("../Service-manag.html");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String fileUpdata() throws Exception {
		Files files = new Files();
		System.out.print(xgId);
//		String []list=arlt.split("\\");
		String image = arltGeo.substring(arltGeo.lastIndexOf("/")+1);
	    files.setOnlinePath(image);
	    files.setFileName(image);
	    files.setParentId(xgId);
	    fileBO.updateById(files);
	    return null;
	}
	
	public String metadataUpdata() throws Exception {
		Metadata metadata = new Metadata();
		
		metadata.setFwContent(this.fwContent);
		metadata.setZdx(this.zdx);
		metadata.setZdy(this.zdy);
		metadata.setZxx(this.zxx);
		metadata.setZxy(this.zxy);
		metadata.setFbl(this.fbl);
		metadata.setFwlx(this.fwlx);
		metadata.setYxSource(this.yxSource);
		metadata.setYxcjdw(this.yxcjdw);
		metadata.setYxcldw(this.yxcldw);
		metadata.setYxsdr(this.yxsdr);
		metadata.setYxsddw(this.yxsddw);
		metadata.setYxsdsj(this.yxsdsj);
		metadata.setSjmj(this.sjmj);
		metadata.setCqgs(this.cqgs);
		metadata.setShPop(this.shPop);
		metadata.setShTime(this.shTime);
		metadata.setParentId(xgId);
		metadata.setSx(this.sx);
		metadata.setWxlx(this.wxlx);
		metadataBO.updateById(metadata);
	    return null;
	}
	
	public String deleteFile() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();	
		HttpServletResponse response = ServletActionContext.getResponse();
		String path = request.getSession().getServletContext().getRealPath("\\share");  
        System.out.println(path);
        String filePath = path + "\\" + this.fileName;
        System.out.println(filePath);
        File file = new File(filePath);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            file.delete();
            response.setCharacterEncoding("UTF-8"); 
			response.getWriter().write("1");
        } else {
        	response.setCharacterEncoding("UTF-8"); 
			response.getWriter().write("2");
        }
		return null;
    }
	
	//关键字查询
	public String searchKey() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		List<String> paramList = new ArrayList();
		Map queryMap = new HashMap();
		try {
			if (info != null && !info.trim().equals("")) {
				this.infoAssay(paramList);
				queryMap.put("paramList", paramList);
			}
			totalNum = glDao.getSearchKeyCountByNum(queryMap);
			System.out.println(totalNum);
			if(statr == null){
				queryMap.put("numStart", 0);
			}else{
				queryMap.put("numStart", (statr-1)*10);
			}
			List searchList = glDao.querySearchKeyByPage(queryMap);
			JSONObject object = new JSONObject();  
	        object.put("num", totalNum); 
	        object.put("list", searchList);
	        System.out.println(object.toString());
	        response.setCharacterEncoding("utf-8");
	        response.getWriter().write(object.toString());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//分类查询
	public String categorySearch() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		Map queryMap = new HashMap();
		try {
			if(type != null &&  !type.trim().equals("") && type.equals("fbl")){
				queryMap.put("fbl", val);
			}
			if(type != null &&  !type.trim().equals("") && type.equals("fwlx")){
				queryMap.put("fwlx", val);
			}
			if(type != null &&  !type.trim().equals("") && type.equals("sx")){
				queryMap.put("sx", val);
			}
			if(type != null &&  !type.trim().equals("") && type.equals("wxlx")){
				queryMap.put("wxlx", val);
			}
			totalNum = glDao.getCategoryCountByNum(queryMap);
			System.out.println(totalNum);
			if(statr == null){
				queryMap.put("numStart", 0);
			}else{
				queryMap.put("numStart", (statr-1)*10);
			}
			List categoryList = glDao.queryCategoryByPage(queryMap);
			JSONObject object = new JSONObject();  
	        object.put("num", totalNum); 
	        object.put("list", categoryList);
	        System.out.println(object.toString());
	        response.setCharacterEncoding("utf-8");
	        response.getWriter().write(object.toString());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//搜索栏信息分析
	private void  infoAssay(List paramList) {
		String[] keywords = this.info.trim().split(" ");
		for (int i = 0; i < keywords.length; i++) {
			if (!keywords[i].equals("")) {
				paramList.add(keywords[i].toLowerCase());
			}
		}
	}
	//查询当日发布数据
	public String searchNumber() throws IOException{
		HttpServletResponse iResponse =ServletActionContext.getResponse();
		Map queryMap = new HashMap();
		try {
		    queryMap.put("fbTime", new Date());
			totalNum = glDao.getGLCountByNum(queryMap);
			JSONObject object = new JSONObject();  
	        object.put("num", totalNum); 
	        iResponse.setCharacterEncoding("utf-8");
	        iResponse.getWriter().write(object.toString());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String logAdd(String event) throws Exception {
		try {
			HttpSession session = ServletActionContext.getRequest().getSession();
			User user =(User)session.getAttribute(StaticName.session_user);
			Logs logs = new Logs();
			logs.setUserName(user.getLogName());
			logs.setUserId(user.getUserId());
			logs.setIp(ServletActionContext.getRequest().getRemoteAddr());
			logs.setEvent(event);
			logs.setTime(new Date());
			logs.setTimes(1);
			this.logsDao.add(logs);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}
	//生成缩略图
	public void decodeBase64ToImage() {  
		HttpServletRequest request = ServletActionContext.getRequest();
        String path = request.getSession().getServletContext().getRealPath("\\share");  
        BASE64Decoder decoder = new BASE64Decoder();  
        String str = this.sluUrl.substring(22);
        System.out.println(str);
        try { 
            FileOutputStream write = new FileOutputStream(new File(path + "\\"+this.imgName+".png"));  
            byte[] decoderBytes = decoder.decodeBuffer(str);  
            write.write(decoderBytes);  
            write.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
	
	public Long getGlId() {
		return glId;
	}

	public void setGlId(Long glId) {
		this.glId = glId;
	}

	public String getGlNumber() {
		return glNumber;
	}

	public void setGlNumber(String glNumber) {
		this.glNumber = glNumber;
	}

	public String getGlName() {
		return glName;
	}

	public void setGlName(String glName) {
		this.glName = glName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getFbTime() {
		return fbTime;
	}

	public void setFbTime(Date fbTime) {
		this.fbTime = fbTime;
	}

	public String getFbPop() {
		return fbPop;
	}

	public void setFbPop(String fbPop) {
		this.fbPop = fbPop;
	}

	public Long getStatr() {
		return statr;
	}

	public void setStatr(Long statr) {
		this.statr = statr;
	}

	public Long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ServiceGl getServiceGl() {
		return serviceGl;
	}

	public void setServiceGl(ServiceGl serviceGl) {
		this.serviceGl = serviceGl;
	}

	public ServiceGlDao getGlDao() {
		return glDao;
	}

	public void setGlDao(ServiceGlDao glDao) {
		this.glDao = glDao;
	}

	public ServiceGlBO getGlBO() {
		return glBO;
	}

	public void setGlBO(ServiceGlBO glBO) {
		this.glBO = glBO;
	}

	public Files getFiles() {
		return files;
	}

	public void setFiles(Files files) {
		this.files = files;
	}

	public FilesDao getFileDao() {
		return fileDao;
	}

	public void setFileDao(FilesDao fileDao) {
		this.fileDao = fileDao;
	}

	public FilesBO getFileBO() {
		return fileBO;
	}

	public void setFileBO(FilesBO fileBO) {
		this.fileBO = fileBO;
	}

	public List getGlList() {
		return glList;
	}

	public void setGlList(List glList) {
		this.glList = glList;
	}

	public List getFileList() {
		return fileList;
	}

	public void setFileList(List fileList) {
		this.fileList = fileList;
	}

	public List getMetaList() {
		return metaList;
	}

	public void setMetaList(List metaList) {
		this.metaList = metaList;
	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public String getFwContent() {
		return fwContent;
	}

	public void setFwContent(String fwContent) {
		this.fwContent = fwContent;
	}

	public String getZdx() {
		return zdx;
	}

	public void setZdx(String zdx) {
		this.zdx = zdx;
	}

	public String getZdy() {
		return zdy;
	}

	public void setZdy(String zdy) {
		this.zdy = zdy;
	}

	public String getZxx() {
		return zxx;
	}

	public void setZxx(String zxx) {
		this.zxx = zxx;
	}

	public String getZxy() {
		return zxy;
	}

	public void setZxy(String zxy) {
		this.zxy = zxy;
	}

	public String getFbl() {
		return fbl;
	}

	public void setFbl(String fbl) {
		this.fbl = fbl;
	}

	public String getYxSource() {
		return yxSource;
	}

	public void setYxSource(String yxSource) {
		this.yxSource = yxSource;
	}

	public String getYxcjdw() {
		return yxcjdw;
	}

	public void setYxcjdw(String yxcjdw) {
		this.yxcjdw = yxcjdw;
	}

	public String getYxcldw() {
		return yxcldw;
	}

	public void setYxcldw(String yxcldw) {
		this.yxcldw = yxcldw;
	}

	public String getYxsdr() {
		return yxsdr;
	}

	public void setYxsdr(String yxsdr) {
		this.yxsdr = yxsdr;
	}

	public String getYxsddw() {
		return yxsddw;
	}

	public void setYxsddw(String yxsddw) {
		this.yxsddw = yxsddw;
	}

	public String getYxsdsj() {
		return yxsdsj;
	}

	public void setYxsdsj(String yxsdsj) {
		this.yxsdsj = yxsdsj;
	}

	public String getSjmj() {
		return sjmj;
	}

	public void setSjmj(String sjmj) {
		this.sjmj = sjmj;
	}

	public String getCqgs() {
		return cqgs;
	}

	public void setCqgs(String cqgs) {
		this.cqgs = cqgs;
	}

	public String getShPop() {
		return shPop;
	}

	public void setShPop(String shPop) {
		this.shPop = shPop;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public MetadataDao getMetadataDao() {
		return metadataDao;
	}

	public void setMetadataDao(MetadataDao metadataDao) {
		this.metadataDao = metadataDao;
	}

	public MetadataBO getMetadataBO() {
		return metadataBO;
	}

	public void setMetadataBO(MetadataBO metadataBO) {
		this.metadataBO = metadataBO;
	}

	public Long getXgId() {
		return xgId;
	}

	public void setXgId(Long xgId) {
		this.xgId = xgId;
	}

	public String getFwlx() {
		return fwlx;
	}

	public void setFwlx(String fwlx) {
		this.fwlx = fwlx;
	}

	public String getWxlx() {
		return wxlx;
	}

	public void setWxlx(String wxlx) {
		this.wxlx = wxlx;
	}

	public String getShTime() {
		return shTime;
	}

	public void setShTime(String shTime) {
		this.shTime = shTime;
	}

	public String getSx() {
		return sx;
	}

	public void setSx(String sx) {
		this.sx = sx;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public LogsDao getLogsDao() {
		return logsDao;
	}

	public void setLogsDao(LogsDao logsDao) {
		this.logsDao = logsDao;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getBrowse() {
		return browse;
	}

	public void setBrowse(Integer browse) {
		this.browse = browse;
	}

	public String getSluUrl() {
		return sluUrl;
	}

	public void setSluUrl(String sluUrl) {
		this.sluUrl = sluUrl;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getArltGeo() {
		return arltGeo;
	}

	public void setArltGeo(String arltGeo) {
		this.arltGeo = arltGeo;
	}

	public String getArltWms() {
		return arltWms;
	}

	public void setArltWms(String arltWms) {
		this.arltWms = arltWms;
	}

	public String getEditImgName() {
		return editImgName;
	}

	public void setEditImgName(String editImgName) {
		this.editImgName = editImgName;
	}
	
}