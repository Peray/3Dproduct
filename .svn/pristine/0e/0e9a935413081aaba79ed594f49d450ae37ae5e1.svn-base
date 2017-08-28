package  com.eastdawn.dao.oracle;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import  com.eastdawn.dao.CategoryDao;
import  com.eastdawn.po.Category;

public class CategoryDaoImpl extends SqlMapClientDaoSupport implements CategoryDao{
	
	public Category getCategoryById(Long categoryId){
		return (Category) getSqlMapClientTemplate().queryForObject("categoryMap.getCategoryById", categoryId);
	}
	
	public List queryCategoryByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return (List)getSqlMapClientTemplate().queryForList("categoryMap.queryCategoryByPage", queryMap);
	}

	public Long getCategoryCountByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return (Long)getSqlMapClientTemplate().queryForObject("categoryMap.getCategoryCountByPage", queryMap);
	}
	
	public void add(Category category) {
		 getSqlMapClientTemplate().insert("categoryMap.add", category);
	}
	
	public void deleteById(Long categoryId) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("categoryMap.deleteById", categoryId);

	}
	
	public void updateById(Category category) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("categoryMap.updateById", category);

	}

}
