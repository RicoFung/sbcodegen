<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "${className}GetDTO 详情入参")
public class ${className}GetDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "动态列", example = "[<#list table.columns as column>\"${column.columnNameLower}\"<#if column_has_next>,</#if></#list>]", position = 0)
	private java.lang.String[] dynamicColumns;

	@ApiModelProperty(value = "主键", example = "0", position = 1, required = true)
	@NotNull(message = "tcRowid不能为空！")
	private java.lang.Long tcRowid;

	public String[] getDynamicColumns()
	{
		return dynamicColumns;
	}
	
	public void setDynamicColumns(String[] dynamicColumns)
	{
		this.dynamicColumns = dynamicColumns;
	}
	
	public void setTcRowid(java.lang.Long value)
	{
		this.tcRowid = value;
	}

	public java.lang.Long getTcRowid()
	{
		return this.tcRowid;
	}
}
