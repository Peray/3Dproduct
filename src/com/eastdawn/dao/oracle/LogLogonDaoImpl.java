package  com.eastdawn.dao.oracle;

import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.eastdawn.dao.LogLogonDao;

@SuppressWarnings("serial")
public class LogLogonDaoImpl extends SqlMapClientDaoSupport implements LogLogonDao {
	
	public void add(Map queryMap) {
		getSqlMapClientTemplate().insert("logLogonMap.add", queryMap);
	}
	
}
