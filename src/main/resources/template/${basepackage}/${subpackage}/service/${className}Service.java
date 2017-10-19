<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${subpackage}.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import chok.devwork.springboot.BaseDao;
import chok.devwork.springboot.BaseService;
import ${basepackage}.${subpackage}.dao.${className}Dao;
import ${basepackage}.${subpackage}.entity.${className};

@Service
public class ${className}Service extends BaseService<${className},Long>
{
	@Autowired
	private ${className}Dao dao;

	@Override
	public BaseDao<${className},Long> getEntityDao() 
	{
		return dao;
	}
}
