<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign classNameLowerCase = table.classNameLowerCase>  
<#assign classNameFirstLower = table.classNameFirstLower>  
<#assign subpkg = subpackage?replace("/",".")>
<#assign splitIndex = subpkg?index_of(".")>
<#assign prefix = subpkg?substring(splitIndex+1)>
package ${basepackage}.${subpkg}.${module}.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ${basepackage}.${subpkg}.${module}.dto.${className}AddDTO;
import ${basepackage}.${subpkg}.${module}.dto.${className}DelDTO;
import ${basepackage}.${subpkg}.${module}.dto.${className}GetDTO;
import ${basepackage}.${subpkg}.${module}.dto.${className}QueryDTO;
import ${basepackage}.${subpkg}.${module}.dto.${className}UpdDTO;
import ${basepackage}.${subpkg}.${module}.service.${className}Service;
import ${basepackage}.${subpkg}.entity.${className};

import com.fasterxml.jackson.core.type.TypeReference;

import chok.common.RestConstants;
import chok.common.RestResult;
import chok.devwork.springboot.BaseRestController;
import chok.util.POIUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "${prefix}-${className}")
@RestController(value = "${prefix}${className}Controller")
@RequestMapping("/${subpackage}/${classNameLowerCase}")
public class ${className}Controller extends BaseRestController<${className}>
{
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ${className}Service service;

	
	@ApiOperation("新增")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public RestResult add(@RequestBody @Validated ${className}AddDTO ${classNameFirstLower}AddDTO, BindingResult validResult)
	{
		restResult = new RestResult();
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
		restResult = new RestResult();
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
		restResult = new RestResult();
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
		restResult = new RestResult();
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
		restResult = new RestResult();
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
	
	@ApiOperation("上传")
	@RequestMapping(value = "/imp", method = RequestMethod.POST, consumes = "multipart/*", headers = "content-type=multipart/form-data")
	public RestResult imp(@ApiParam(value = "上传文件", required = true) @RequestPart("tcFile") @Valid MultipartFile tcFile)
	{
		try
		{
//			String tcUuid = UUID.randomUUID().toString();
			List<TbDemo> impList = new ArrayList<TbDemo>();
			List<String[]> excelList = POIUtil.readExcel(tcFile, 1);
			// Excel 2 List<T>
			for(String[] excelRow : excelList)
			{
				${className} ${classNameFirstLower} = new ${className}();
				Map<String, Object> m = new HashMap<String, Object>();
				<#list table.notPkColumns as column>
				m.put("${column.columnNameLower}", excelRow[${column_index}]);
				</#list>
				org.apache.commons.beanutils.BeanUtils.populate(${classNameFirstLower}, m);
				impList.add(${classNameFirstLower});
			}
			// 分批写入，每批100条
			service.addBatch(impList, 100);
		}
		catch (Exception e)
		{
			log.error("<== Exception：{}", e);
			restResult.setSuccess(false);
			restResult.setCode(RestConstants.ERROR_CODE1);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}
	
}
