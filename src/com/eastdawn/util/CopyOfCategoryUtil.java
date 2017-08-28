//package com.eastdawn.util;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.ServletContext;
//
//import org.apache.log4j.Logger;
//import org.apache.struts2.ServletActionContext;
//import org.springframework.context.ApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import com.eastdawn.dao.CategoryDao;
//import com.eastdawn.po.Category;
//
///**
// * 类别工具类
// * @author YangJian
// * @date Nov 24, 2011 3:37:50 PM 
// * @version v1.00
// */
//public class CategoryUtil {
//	private static Logger logger = Logger.getLogger(CategoryUtil.class);
//	private static CategoryDao categoryDao;
//	private static List<Category> categoryList;
//	
//	static {
//		ServletContext context = ServletActionContext.getServletContext();
//		ApplicationContext ctx = 
//			WebApplicationContextUtils.getWebApplicationContext(context);
//		Map<String, Object> queryMap = new HashMap<String, Object>();
//		categoryDao = (CategoryDao) ctx.getBean("categoryDao");
//		queryMap.put("parentIsNull", true);
//		categoryList = categoryDao.queryCategoryByPage(queryMap);
//		foreach(categoryList);
//	}
//	
//	/**
//	 * 遍历所有类型，加载到内存
//	 * @param categoryList
//	 */
//	private static void foreach(List<Category> categoryList) {
//		if (categoryList != null) {
//			for (Category category : categoryList) {
//				foreach(category.getCategoryList());
//			}
//		}
//	}
//	
//	/**
//	 * 通过ID查找
//	 * @param categoryId
//	 * @return null : Category
//	 */
//	public static Category getCategoryById(Long categoryId, List<Category> categoryList) {
//		if (categoryId != null) {
//			for (Category category : categoryList) {
////				System.out.println("categoryId====>" + category.getCategoryId());
//				if (categoryId.equals(category.getCategoryId())) {
//					return category;
//				}
//				Category sonCategory = getCategoryById(categoryId, category.getCategoryList());
//				if (sonCategory != null){
//					return sonCategory;
//				}
//			}
//		}
//		return null;
//	}
//	
//	/**
//	 * 通过名称查找
//	 * @param categoryName
//	 * @return null : Category
//	 */
//	public static Category getCategoryByName(String categoryName, List<Category> categoryList) {
//		if (categoryName != null && !categoryName.trim().equals("")) {
//			for (Category category : categoryList) {
////				System.out.println("categoryName====>" + category.getCategoryName());
//				if (categoryName.trim().equals(category.getCategoryName().trim().toLowerCase())) {
//					return category;
//				}
//				Category sonCategory = getCategoryByName(categoryName, category.getCategoryList());
//				if (sonCategory != null){
//					return sonCategory;
//				}
//			}
//		}
//		return null;
//	}
//	
//	/**
//	 * 通过parentID查找
//	 * @param parentId
//	 * @return List<Category>
//	 */
//	public static List<Category> queryCategoryByParentId(Long parentId, List<Category> categoryList) {
//		if (parentId != null) {
//			for (Category category : categoryList) {
//				if (parentId.equals(category.getCategoryId())) {
//					return category.getCategoryList();
//				}
//				List<Category> sonCategoryList = queryCategoryByParentId(parentId, category.getCategoryList());
//				if (sonCategoryList != null && sonCategoryList.size() > 0){
//					return sonCategoryList;
//				}
//			}
//		} else {
//			return categoryList;
//		}
//		return new ArrayList<Category>();
//	}
//	
//	/**
//	 * 添加类别
//	 * @param category
//	 */
//	public static void addCategory(Category category) {
//		Date date1 = new Date();
//		Category parentCategory = null;
//		if (category != null) {
//			parentCategory = getCategoryById(category.getParentId(), CategoryUtil.getCategoryList());
//			if (parentCategory == null) {
//				categoryList.add(category);
//			} else {
//				parentCategory.getCategoryList().add(category);
//			}
//		}
//		Date date2 = new Date();
//		logger.info("=====>用时：" + (date2.getTime() - date1.getTime()));
//	}
//	
//	/**
//	 * 修改类别
//	 * @param category
//	 */
//	public static void updateCategory(Category category) {
//		Category originalCategory = null;
//		if (category != null) {
//			originalCategory = getCategoryById(category.getCategoryId(), CategoryUtil.getCategoryList());
//			if (originalCategory == null) {
//				addCategory(category);
//			} else {
//				if (category.getCategoryName() != null) {
//					originalCategory.setCategoryName(category.getCategoryName());
//				}
//				if (category.getPath() != null) {
//					originalCategory.setPath(category.getPath());
//				}
//				if (category.getParentId() != null) {
//					originalCategory.setParentId(category.getParentId());
//				}
//				if (category.getCategoryList() != null) {
//					originalCategory.setCategoryList(category.getCategoryList());
//				}
//			}
//		}
//	}
//	
//	/**
//	 * 删除类别
//	 * @param categoryId
//	 */
//	public static void deleteCategory(Long categoryId) {
//		Date date1 = new Date();
//		Category category = null;
//		Category parentCategory = null;
//		if (categoryId != null) {
//			category = getCategoryById(categoryId, CategoryUtil.getCategoryList());
//			if (category != null) {
//				parentCategory = getCategoryById(category.getParentId(), CategoryUtil.getCategoryList());
//				if (parentCategory != null) {
//					parentCategory.getCategoryList().remove(category);
//				} else {
//					CategoryUtil.getCategoryList().remove(category);
//				}
//			}
//		}
//		Date date2 = new Date();
//		logger.info("=====>用时：" + (date2.getTime() - date1.getTime()));
//	}
//	
//	/**
//	 * 返回主键组合
//	 * @param category
//	 * @return 例如：1\101\201
//	 */
//	public static String getCategoryMap(Category category) {
//		StringBuffer sb = new StringBuffer();
//		
//		for (;;) {
//			sb.insert(0, "\\" + category.getCategoryId());
//			category = CategoryUtil.getCategoryById(category.getParentId(), CategoryUtil.getCategoryList());
//			if (category == null) {
//				break;
//			}
//		}
//		
//		return sb.substring(1).toString();
//	}
//	
//	/**
//	 * 返回路径组合
//	 * @param category
//	 * @return 例如：/10/101
//	 */
//	public static String getCategoryDirs(Category category) {
//		StringBuffer sb = new StringBuffer();
//		
//		for (;;) {
//			sb.insert(0, "/" + category.getCategoryId());
//			category = CategoryUtil.getCategoryById(category.getParentId(), CategoryUtil.getCategoryList());
//			if (category == null) {
//				break;
//			}
//		}
//		
//		return sb.toString();
//	}
//	
//	/**
//	 * 返回路径组合
//	 * @param category
//	 * @return 例如：fruitapple
//	 */
//	public static String getCategoryPath(Category category) {
//		StringBuffer sb = new StringBuffer();
//		
//		for (;;) {
//			sb.insert(0, category.getPath());
//			category = CategoryUtil.getCategoryById(category.getParentId(), CategoryUtil.getCategoryList());
//			if (category == null) {
//				break;
//			}
//		}
//		
//		return sb.toString();
//	}
//
//	public static CategoryDao getCategoryDao() {
//		return categoryDao;
//	}
//
//	/**
//	 * 直接返回一级类别
//	 * @return List<Category>
//	 */
//	public static List<Category> getCategoryList() {
//		return categoryList;
//	}
//
//}
