<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign classNameLowerCase = table.classNameLowerCase>  
<#assign classNameFirstLower = table.classNameFirstLower>  
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "${className}QueryDTO 列表入参")
public class ${className}QueryDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	// ********************************************************************************************
	// 1.默认参数
	// ********************************************************************************************

	@ApiModelProperty(value = "动态列", example = "[<#list table.columns as column>\"${column.columnNameLower}\"<#if column_has_next>,</#if></#list>]", position = 0)
	private java.lang.String[] dynamicColumns;
	
	@ApiModelProperty(value = "动态排序", example = "[<#if table.pkCount gte 1><#list table.compositeIdColumns as column>{\"sortName\":\"${column.columnNameLower}\",\"sortOrder\":\"DESC\"}<#if column_has_next>, </#if></#list></#if>]", position = 1)
	private List<Map<String, Object>> dynamicOrder;

	@ApiModelProperty(value = "页码", example = "1", position = 2)
	private int page;

	@ApiModelProperty(value = "页大小", example = "10", position = 3)
	private int pagesize;

	public String[] getDynamicColumns()
	{
		return dynamicColumns;
	}
	
	public void setDynamicColumns(String[] dynamicColumns)
	{
		this.dynamicColumns = dynamicColumns;
	}
	
	public List<Map<String, Object>> getDynamicOrder()
	{
		return dynamicOrder;
	}
	public void setDynamicOrder(List<Map<String, Object>> dynamicOrder)
	{
		this.dynamicOrder = dynamicOrder;
	}
	
	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public int getPagesize()
	{
		return pagesize;
	}

	public void setPagesize(int pagesize)
	{
		this.pagesize = pagesize;
	}

	// ********************************************************************************************
	// 2.表单参数
	// ********************************************************************************************
	
	<#list table.columns as column>
    // ${column.columnAlias!}       db_column: ${column.sqlName} 
	@ApiModelProperty(value = "${column.columnAlias!}", example = "\"\"", position = ${column_index+4})
	private ${column.javaType} ${column.columnNameLower};
	</#list>

<@generateJavaColumns/>
}

<#macro generateJavaColumns>
	<#list table.columns as column>
		<#if column.isDateTimeColumn>
	public String get${column.columnName}String() 
	{
		return DateConvertUtils.format(get${column.columnName}(), FORMAT_${column.constantName});
	}
	public void set${column.columnName}String(String value) 
	{
		set${column.columnName}(DateConvertUtils.parse(value, FORMAT_${column.constantName},${column.javaType}.class));
	}
	
		</#if>	
	public void set${column.columnName}(${column.javaType} value) 
	{
		this.${column.columnNameLower} = value;
	}
	
	public ${column.javaType} get${column.columnName}() 
	{
		return this.${column.columnNameLower};
	}
	</#list>
</#macro>