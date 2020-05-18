<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign classNameLowerCase = table.classNameLowerCase>  
<#assign classNameFirstLower = table.classNameFirstLower>  
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import chok.devwork.BaseController;
import chok.util.CollectionUtil;
import ${basepackage}.${subpkg}.${module}.service.${className}Service;
import ${basepackage}.${subpkg}.${module}.entity.${className};

@Scope("prototype")
@Controller
@RequestMapping("/${classNameLowerCase}")
public class ${className}Api extends BaseController<${className}>
{
	@Autowired
	private ${className}Service service;
	
	@RequestMapping("/add")
	public void add(${className} po) 
	{
		try
		{
			service.add(po);
			printJson(result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			printJson(result);
		}
	}
	
	@RequestMapping("/del")
	public void del() 
	{
		try
		{
			service.del(CollectionUtil.strToLongArray(req.getString("tcRowid"), ","));
			result.setSuccess(true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}
		printJson(result);
	}
	
	@RequestMapping("/upd")
	public void upd(${className} po) 
	{
		try
		{
			service.upd(po);
			printJson(result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			printJson(result);
		}
	}

	@RequestMapping("/get")
	public void get() 
	{
		try
		{
			result.put("po", service.get(req.getLong("tcRowid")));
			printJson(result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			printJson(result);
		}
	}
	
	@RequestMapping("/query")
	public void query()
	{
		try
		{
			Map<String, Object> m = req.getParameterValueMap(false, true);
			result.put("total",service.getCount(m));
			result.put("rows",service.query(req.getDynamicSortParameterValueMap(m)));
			printJson(result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			printJson(result);
		}
	}
}
