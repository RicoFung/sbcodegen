<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign classNameLowerCase = table.classNameLowerCase>  
<#assign subpkg = subpackage?replace("/",".")>
<#assign splitIndex = subpkg?index_of(".")>
<#assign prefix = subpkg?substring(splitIndex+1)>
package ${basepackage}.${subpkg}.${module}.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import chok.devwork.springboot.BaseDao;
import chok.devwork.springboot.BaseService;
import ${basepackage}.${subpkg}.${module}.dao.${className}Dao;
import ${basepackage}.${subpkg}.${module}.entity.${className};

@Service(value = "${prefix}${className}Service")
public class ${className}Service extends BaseService<${className}, Long>
{
	@Autowired
	private ${className}Dao dao;

	@Override
	public BaseDao<${className}, Long> getEntityDao() 
	{
		return dao;
	}
}
