package com.eastdawn.util;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.eastdawn.po.Heliu;
import com.eastdawn.po.Hupo;
import com.eastdawn.util.ReadUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

@SuppressWarnings("serial")
public class HeliuAction{
	
	private Long fId;//jsonId
	private String geometry;//坐标点
	private String hydCode;//
	private String shapeLeng;
	private String name;
	private String objectId;
	
	public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";   
    public static final String DBURL = "jdbc:mysql://192.168.10.210:3306/spacedata";   
    //现在使用的是mysql数据库，是直接连接的，所以此处必须有用户名和密码   
    public static final String USERNAME = "root";   
    public static final String PASSWORD = "123456";
    
	public static void main(String[] args) throws Exception {  
		 Connection conn = null;   
         //数据库操作对象   
         Statement stmt = null;   
         //1、加载驱动程序   
         try {   
                 Class.forName(DBDRIVER);   
         } catch (ClassNotFoundException e) {   
                 e.printStackTrace();   
         }   
         //2、连接数据库   
         //通过连接管理器连接数据库   
         try {   
                 //在连接的时候直接输入用户名和密码才可以连接   
                 conn = (Connection) DriverManager.getConnection(DBURL,USERNAME,PASSWORD);   
         } catch (SQLException e) {   
                 e.printStackTrace();   
         }   
         
         Heliu heliu = new Heliu();
 		String JsonContext = new ReadUtil().ReadFile("C:\\Users\\lwhou\\Desktop\\geojson\\heliu1.json");
         System.out.println(JsonContext);
         JSONArray jsonArray = JSONArray.fromObject(JsonContext);  
//         System.out.println(jsonArray);
         int size = jsonArray.size();  
         System.out.println("Size: " + size);  
         
         try {
         	for(int  i = 0; i < size; i++){  
                 JSONObject jsonObject = jsonArray.getJSONObject(i);  
                 heliu.setGeometry(jsonObject.get("geometry").toString());
                 System.out.println("[" + i + "]geometry=" + jsonObject.get("geometry").toString());  
                 System.out.println("[" + i + "]type=" + jsonObject.get("type").toString());  
                 JSONArray zilei = JSONArray.fromObject(jsonObject.get("properties"));
                 for(int j = 0; j < zilei.size(); j++){
                 	JSONObject info = zilei.getJSONObject(j);
//                 	hupo.setLakeCode(info.get("LAKE_CODE").toString());
//                 	hupo.setShapeLeng(info.get("Shape_Leng").toString());
//                 	hupo.setName(info.get("NAME").toString());
//                 	hupo.setShapeArea(info.get("Shape_Area").toString());
                 	System.out.println("[" + i + "]FID=" + info.get("FID").toString().toString());  
                     System.out.println("[" + i + "]LAKE_CODE=" + info.get("HYD_CODE").toString());  
                     System.out.println("[" + i + "]Shape_Leng=" + info.get("Shape_Leng").toString()); 
                     System.out.println("[" + i + "]NAME=" + info.get("OBJECTID").toString()); 
                     System.out.println("[" + i + "]Shape_Area=" + info.get("NAME").toString());
                   //3、向数据库中插入一条数据   
                     String sql = "INSERT INTO tb_heliu(geometry,hyd_Code,shape_Leng,object_Id,name) values ('"+jsonObject.get("geometry").toString()+"','"+info.get("HYD_CODE").toString()+"','"+info.get("Shape_Leng").toString()+"','"+info.get("OBJECTID").toString()+"','"+info.get("NAME").toString()+"')";   
                     try {   
                             stmt = (Statement) conn.createStatement();   
                     } catch (SQLException e) {   
                             e.printStackTrace();   
                     }   
                     //4、执行语句   
                     try {   
                             stmt.executeUpdate(sql);   
                     } catch (SQLException e) {   
                             e.printStackTrace();   
                     }  
                 }
                
         	}
 		} catch (Exception e) {
 			// TODO: handle exception
 			e.printStackTrace();
 		}
          
         //5、关闭操作，步骤相反哈~   
         try {   
                 stmt.close();   
                 conn.close();   
         } catch (SQLException e) {   
                 e.printStackTrace();   
         }   
    }

	public Long getFId() {
		return fId;
	}

	public void setFId(Long id) {
		fId = id;
	}

	public String getGeometry() {
		return geometry;
	}

	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}

	public String getShapeLeng() {
		return shapeLeng;
	}

	public void setShapeLeng(String shapeLeng) {
		this.shapeLeng = shapeLeng;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHydCode() {
		return hydCode;
	}

	public void setHydCode(String hydCode) {
		this.hydCode = hydCode;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public static String getDBDRIVER() {
		return DBDRIVER;
	}

	public static String getDBURL() {
		return DBURL;
	}

	public static String getUSERNAME() {
		return USERNAME;
	}

	public static String getPASSWORD() {
		return PASSWORD;
	}
	
}