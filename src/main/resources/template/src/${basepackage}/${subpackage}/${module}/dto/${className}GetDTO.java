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
	
	@NotNull(message = "tcRowid不能为空！")
	private java.lang.Long tcRowid;

	public void setTcRowid(java.lang.Long value)
	{
		this.tcRowid = value;
	}

	public java.lang.Long getTcRowid()
	{
		return this.tcRowid;
	}

	@Override
	public String toString()
	{
		return "TbDemo [tcRowid=" + tcRowid + "]";
	}
}
