package com.eastdawn.util;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.eastdawn.po.Hupo;
import com.eastdawn.util.ReadUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

@SuppressWarnings("serial")
public class HupoAction{
	
	private Long fId;//jsonId
	private String geometry;//坐标点
	private String lakeCode;//
	private String shapeLeng;
	private String name;
	private String shapeArea;
	private String point;
	
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
         //湖泊查询语句
//         String sql = "select FID,substring_index(substr(geometry,36,88),']',1) as geometry from tb_hupo"; 
         
         //河流查询语句
         String sql = "select FID,substring_index(substr(geometry,38,88),']',1) as geometry from tb_heliu_two"; 
         
         try {   
	             stmt = (Statement) conn.createStatement();   
	     } catch (SQLException e) {   
	             e.printStackTrace();   
	     }   
	     //4、执行语句   
	     try {   
	            ResultSet rs = stmt.executeQuery(sql);
	   	        //将结果集封装到List中
	   	        List<Hupo> list = new ArrayList();
	   	        Hupo hupo = new Hupo();
	   	        int count = 0;
	   	        while(rs.next()){
	   	        	count++;
	   	        	System.out.println(count);
	   	          // 可以根据列名称也可以根据列索引
	   	          hupo.setFId(rs.getLong("FID"));
	   	          hupo.setPoint(rs.getString("geometry"));
	   	          //将对象存放到list容器中
  	              //String sqlupdata = "update tb_hupo set point='"+rs.getString("geometry")+"' where FID='"+rs.getLong("FID")+"'";   
  	              String sqlupdata = "update tb_heliu_two set point='"+rs.getString("geometry")+"' where FID='"+rs.getLong("FID")+"'";   
  	              try {   
                       stmt = (Statement) conn.createStatement();   
                  } catch (SQLException e) {   
                       e.printStackTrace();   
                  }   
                  //4、执行语句   
                  try {   
                       stmt.executeUpdate(sqlupdata);   
                  } catch (SQLException e) {   
                       e.printStackTrace();   
                  }  
	   	        }
	   	        System.out.println(list.size());
	     } catch (SQLException e) {   
	             e.printStackTrace();   
	     }  
	     
	     
	     

         /**
         Hupo hupo = new Hupo();
 		 String JsonContext = new ReadUtil().ReadFile("C:\\Users\\lwhou\\Desktop\\geojson\\quanshenghupo.json");
         System.out.println(JsonContext);
         JSONArray jsonArray = JSONArray.fromObject(JsonContext);  
         System.out.println(jsonArray);
         int size = jsonArray.size();  
         System.out.println("Size: " + size);  
         
         try {
         	for(int  i = 0; i < size; i++){  
                 JSONObject jsonObject = jsonArray.getJSONObject(i);  
                 hupo.setGeometry(jsonObject.get("geometry").toString());
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
                     System.out.println("[" + i + "]LAKE_CODE=" + info.get("LAKE_CODE").toString());  
                     System.out.println("[" + i + "]Shape_Leng=" + info.get("Shape_Leng").toString()); 
                     System.out.println("[" + i + "]NAME=" + info.get("NAME").toString()); 
                     System.out.println("[" + i + "]Shape_Area=" + info.get("Shape_Area").toString());
                   //3、向数据库中插入一条数据   
                     String sql = "INSERT INTO tb_hupo(geometry,lake_Code,shape_Leng,name,shape_Area) values ('"+jsonObject.get("geometry").toString()+"','"+info.get("LAKE_CODE").toString()+"','"+info.get("Shape_Leng").toString()+"','"+info.get("NAME").toString()+"','"+info.get("Shape_Area").toString()+"')";   
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
 		}*/
          
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

	public String getLakeCode() {
		return lakeCode;
	}

	public void setLakeCode(String lakeCode) {
		this.lakeCode = lakeCode;
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

	public String getShapeArea() {
		return shapeArea;
	}

	public void setShapeArea(String shapeArea) {
		this.shapeArea = shapeArea;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
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