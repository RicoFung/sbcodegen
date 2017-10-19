<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${subpackage}.dao;

import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import chok.devwork.springboot.BaseDao;
import ${basepackage}.${subpackage}.entity.${className};

@Repository
public class ${className}Dao extends BaseDao<${className},Long>
{
	@Resource(name = "firstSqlSessionTemplate")
	private SqlSession sqlSession;

	@Override
	protected SqlSession getSqlSession()
	{
		return sqlSession;
	}
	
	@Override
	public Class<${className}> getEntityClass()
	{
		return ${className}.class;
	}
}
