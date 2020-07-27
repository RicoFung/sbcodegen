<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign classNameLowerCase = table.classNameLowerCase>  
<#assign classNameFirstLower = table.classNameFirstLower>  
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ${basepackage}.${subpkg}.${module}.dto.${className}AddDTO;
import ${basepackage}.${subpkg}.${module}.dto.${className}DelDTO;
import ${basepackage}.${subpkg}.${module}.dto.${className}GetDTO;
import ${basepackage}.${subpkg}.${module}.dto.${className}QueryDTO;
import ${basepackage}.${subpkg}.${module}.dto.${className}UpdDTO;
import ${basepackage}.${subpkg}.${module}.entity.${className};
import ${basepackage}.${subpkg}.${module}.service.${className}Service;
import com.fasterxml.jackson.core.type.TypeReference;

import chok.common.RestResult;
import chok.devwork.springboot.BaseRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "${classNameLowerCase}管理")
@RestController
@RequestMapping("/${subpkg}/${classNameLowerCase}")
public class ${className}Controller extends BaseRestController<${className}>
{
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ${className}Service service;

	
	@ApiOperation("新增")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public RestResult add(@RequestBody @Validated ${className}AddDTO ${classNameFirstLower}AddDTO, BindingResult validResult)
	{
		try
		{
			if (log.isDebugEnabled())
			{
				log.debug("==> requestDto：{}", restMapper.writeValueAsString(${classNameFirstLower}AddDTO));
			}
			if (validResult.hasErrors()) 
			{
				restResult.setSuccess(false);
				restResult.setMsg(getValidMsgs(validResult));
				return restResult;
			}
			${className} ${classNameFirstLower} = new ${className}();
			BeanUtils.copyProperties(${classNameFirstLower}AddDTO, ${classNameFirstLower});
			service.add(${classNameFirstLower});
		}
		catch(Exception e)
		{
			log.error("<== Exception：{}", e);
			restResult.setSuccess(false);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}

	@ApiOperation("删除")
	@RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public RestResult del(@RequestBody @Validated ${className}DelDTO ${classNameFirstLower}DelDTO, BindingResult validResult) 
	{
		try
		{
			if (log.isDebugEnabled())
			{
				log.debug("==> requestDto：{}", restMapper.writeValueAsString(${classNameFirstLower}DelDTO));
			}
			if (validResult.hasErrors()) 
			{
				restResult.setSuccess(false);
				restResult.setMsg(getValidMsgs(validResult));
				return restResult;
			}
			service.del(${classNameFirstLower}DelDTO.getTcRowids());
			restResult.setSuccess(true);
		}
		catch(Exception e)
		{
			log.error("<== Exception：{}", e);
			restResult.setSuccess(false);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}

	@ApiOperation("修改")
	@RequestMapping(value = "/upd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public RestResult upd(@RequestBody @Validated ${className}UpdDTO ${classNameFirstLower}UpdDTO, BindingResult validResult) 
	{
		try
		{
			if (log.isDebugEnabled())
			{
				log.debug("==> requestDto：{}", restMapper.writeValueAsString(${classNameFirstLower}UpdDTO));
			}
			if (validResult.hasErrors()) 
			{
				restResult.setSuccess(false);
				restResult.setMsg(getValidMsgs(validResult));
				return restResult;
			}
			${className} ${classNameFirstLower} = new ${className}();
			BeanUtils.copyProperties(${classNameFirstLower}UpdDTO, ${classNameFirstLower});
			service.upd(${classNameFirstLower});
		}
		catch(Exception e)
		{
			log.error("<== Exception：{}", e);
			restResult.setSuccess(false);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}

	@ApiOperation("明细")
	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public RestResult get(@RequestBody @Validated ${className}GetDTO ${classNameFirstLower}GetDTO, BindingResult validResult) 
	{
		try
		{
			if (log.isDebugEnabled())
			{
				log.debug("==> requestDto：{}", restMapper.writeValueAsString(${classNameFirstLower}GetDTO));
			}
			if (validResult.hasErrors()) 
			{
				restResult.setSuccess(false);
				restResult.setMsg(getValidMsgs(validResult));
				return restResult;
			}
			Map<String, Object> param = restMapper.convertValue(${classNameFirstLower}GetDTO,
					new TypeReference<Map<String, Object>>()
			{
			});
			restResult.put("row", service.getDynamic(param));
		}
		catch(Exception e)
		{
			log.error("<== Exception：{}", e);
			restResult.setSuccess(false);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}
	
	@ApiOperation("列表")
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public RestResult query(@RequestBody ${className}QueryDTO ${classNameFirstLower}QueryDTO)
	{
		try
		{
			if (log.isDebugEnabled())
			{
				log.debug("==> requestDto：{}", restMapper.writeValueAsString(${classNameFirstLower}QueryDTO));
			}
			Map<String, Object> param = restMapper.convertValue(${classNameFirstLower}QueryDTO, new TypeReference<Map<String, Object>>(){});
	        restResult.put("total", service.getCount(param));
			restResult.put("rows", service.queryDynamic(param));
		}
		catch (Exception e)
		{
			log.error("<== Exception：{}", e);
			restResult.setSuccess(false);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}
}
