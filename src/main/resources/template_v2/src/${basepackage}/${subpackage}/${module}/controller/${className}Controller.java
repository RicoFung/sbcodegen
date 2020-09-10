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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ${basepackage}.${subpkg}.entity.${className};
import ${basepackage}.${subpkg}.${module}.dto.${className}AddDTO;
import ${basepackage}.${subpkg}.${module}.dto.${className}DelDTO;
import ${basepackage}.${subpkg}.${module}.dto.${className}ExpDTO;
import ${basepackage}.${subpkg}.${module}.dto.${className}GetDTO;
import ${basepackage}.${subpkg}.${module}.dto.${className}ImpDTO;
import ${basepackage}.${subpkg}.${module}.dto.${className}QueryDTO;
import ${basepackage}.${subpkg}.${module}.dto.${className}UpdDTO;

import ${basepackage}.${subpkg}.${module}.service.${className}Service;

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
	// --------------------------------------------------------------------------------------- //
	// value: 指定请求的实际地址， 比如 /action/info之类
	// method： 指定请求的method类型， GET、POST、PUT、DELETE等
	// consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
	// produces: 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回
	// params： 指定request中必须包含某些参数值是，才让该方法处理
	// headers： 指定request中必须包含某些指定的header值，才能让该方法处理请求
	// --------------------------------------------------------------------------------------- //
	
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
				restResult.setCode(RestConstants.ERROR_CODE1);
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
			restResult.setCode(RestConstants.ERROR_CODE1);
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
				restResult.setCode(RestConstants.ERROR_CODE1);
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
			restResult.setCode(RestConstants.ERROR_CODE1);
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
				restResult.setCode(RestConstants.ERROR_CODE1);
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
				restResult.setCode(RestConstants.ERROR_CODE1);
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
			restResult.setCode(RestConstants.ERROR_CODE1);
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
			restResult.setCode(RestConstants.ERROR_CODE1);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}
	
	@ApiOperation("上传")
	@RequestMapping(value = "/imp", method = RequestMethod.POST, consumes = "multipart/*", headers = "content-type=multipart/form-data")
	public RestResult imp(@RequestParam("file") MultipartFile file, @ApiParam(name="json", value = "{\"param1\":\"1\",\"param2\":\"2\"}") @RequestParam("json") String json)
	{
		restResult = new RestResult();
		try
		{
			if (log.isDebugEnabled())
			{
				log.debug("==> requestDto：filename={}, json={}", file.getOriginalFilename(), json);
			}
			restResult = validImportBefore(file, json, ${className}ImpDTO.class);
			if (!restResult.isSuccess())
			{
				return restResult;
			}
//			String tcUuid = UUID.randomUUID().toString();
			List<${className}> impList = new ArrayList<${className}>();
			List<String[]> excelList = POIUtil.readExcel(file, 1);
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
	
	@ApiOperation("导出")
	@RequestMapping(value = "/exp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public void exp(@RequestBody @Validated ${className}ExpDTO ${classNameFirstLower}ExpDTO, BindingResult validResult) throws Exception
	{
		if (log.isDebugEnabled())
		{
			log.debug("==> requestDto：{}", restMapper.writeValueAsString(${classNameFirstLower}ExpDTO));
		}
		if (validResult.hasErrors())
		{
			throw new Exception(getValidMsgs(validResult));
		}
		// 查询参数
		Map<String, Object> param = restMapper.convertValue(${classNameFirstLower}ExpDTO,
				new TypeReference<Map<String, Object>>()
				{
				});
		if (0 < ${classNameFirstLower}ExpDTO.getTcRowids().length)
		{
			param.clear();
			param.put("tcRowidArray", ${classNameFirstLower}ExpDTO.getTcRowids());
		}
		// 限制导出数量必须小于1000
		int count = service.getCount(param);
		if (1000 < count)
		{
			throw new Exception("导出数量不能大于1000条！");
		}
		// 查询
		List<${className}> list = service.queryDynamic(param);
		// 导出至excel
		export(list, 
				${classNameFirstLower}ExpDTO.getShowFilename(),
				${classNameFirstLower}ExpDTO.getShowTitle(),
				StringUtils.join(${classNameFirstLower}ExpDTO.getShowAlias(), ","),
				StringUtils.join(${classNameFirstLower}ExpDTO.getShowColumns(), ","),
				"xlsx");
	}
	
}
