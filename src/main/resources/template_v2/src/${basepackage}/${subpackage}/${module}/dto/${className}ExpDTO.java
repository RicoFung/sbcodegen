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

@ApiModel(description = "${className}ExpDTO 导出入参")
public class ${className}ExpDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	// ********************************************************************************************
	// 0.文件参数
	// ********************************************************************************************

	@ApiModelProperty(value = "文件名", example = "导出文件", position = 0)
	private java.lang.String showFilename;
	
	@ApiModelProperty(value = "标题名", example = "导出数据", position = 1)
	private java.lang.String showTitle;
	
	@ApiModelProperty(value = "列名", example = "[<#list table.columns as column>\"${column.columnNameLower}\"<#if column_has_next>,</#if></#list>]", position = 2)
	private java.lang.String[] showColumns;
	
	@ApiModelProperty(value = "列别名", example = "[<#list table.columns as column>\"${column.columnName}\"<#if column_has_next>,</#if></#list>]", position = 3)
	private java.lang.String[] showAlias;
	
	public java.lang.String getShowFilename()
	{
		return showFilename;
	}

	public void setShowFilename(java.lang.String showFilename)
	{
		this.showFilename = showFilename;
	}

	public java.lang.String getShowTitle()
	{
		return showTitle;
	}

	public void setShowTitle(java.lang.String showTitle)
	{
		this.showTitle = showTitle;
	}

	public java.lang.String[] getShowColumns()
	{
		return showColumns;
	}

	public void setShowColumns(java.lang.String[] showColumns)
	{
		this.showColumns = showColumns;
	}

	public java.lang.String[] getShowAlias()
	{
		return showAlias;
	}
	
	public void setShowAlias(java.lang.String[] showAlias)
	{
		this.showAlias = showAlias;
	}
	
	// ********************************************************************************************
	// 1.默认参数
	// ********************************************************************************************

	@ApiModelProperty(value = "动态列", example = "[<#list table.columns as column>\"${column.columnNameLower}\"<#if column_has_next>,</#if></#list>]", position = 4)
	private java.lang.String[] dynamicColumns;
	
	@ApiModelProperty(value = "动态排序", example = "[<#if table.pkCount gte 1><#list table.compositeIdColumns as column>{\"sortName\":\"${column.columnNameLower}\",\"sortOrder\":\"DESC\"}<#if column_has_next>, </#if></#list></#if>]", position = 5)
	private List<Map<String, Object>> dynamicOrder;

	@ApiModelProperty(value = "主键数组（用于导出被勾选的行）", example = "[]", position = 6)
	private Long[] tcRowids;

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
	
	public Long[] getTcRowids()
	{
		return tcRowids;
	}
	
	public void setTcRowids(Long[] tcRowids)
	{
		this.tcRowids = tcRowids;
	}
	
	// ********************************************************************************************
	// 2.表单参数
	// ********************************************************************************************
	
	<#list table.columns as column>
    // ${column.columnAlias!}       db_column: ${column.sqlName} 
	@ApiModelProperty(value = "${column.columnAlias!}", example = "\"\"", position = ${column_index+7})
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