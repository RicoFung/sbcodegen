/**********************************************************/
/** 全局函数 **/
/**********************************************************/
$(function() {
	$chok.view.fn.selectSidebarMenu($param_menuId, $param_menuPermitId, $param_menuName);
	$chok.view.query.init.toolbar();
	$chok.view.query.init.modalFormQuery();
	$chok.view.query.init.table($queryParams_f_page, $queryParams_f_pageSize);
	$chok.auth.btn($chok.view.menuPermitId,$g_btnJson);
});
/**********************************************************/
/** 初始化配置 **/
/**********************************************************/
$chok.view.query.config.setPreFormParams = function(){
	$("#f_tcField").val(typeof($queryParams_f_tcField)=="undefined"?"":$queryParams_f_tcField);
};
$chok.view.query.config.formParams = function(p){
	p.tcField = $("#f_tcField").val();
    return p;
};
$chok.view.query.config.urlParams = function(){
	return {
			f_tcField : $("#f_tcField").val()
	};
};
// config-定义表格列
$chok.view.query.config.tableColumns = 
[
	<#list table.columns as column>
    {title:"${column.columnNameLower}", field:"${column.columnNameLower}", align:"center", valign:"middle", sortable:true}<#if column_has_next>,</#if>
	</#list>
];
//是否可显示隐藏列
$chok.view.query.config.showColumns = true;
// config-是否显示复合排序
$chok.view.query.config.showMultiSort = true;
// config-默认排序字段
$chok.view.query.config.sortPriority = [{"sortName":"", "sortOrder":"asc"}];
// callback-加载数据成功后
$chok.view.query.callback.onLoadSuccess = function(){
	$chok.auth.btn($chok.view.menuPermitId,$g_btnJson);
};
// OVERWRITE-自定义工具栏
$chok.view.query.init.toolbar = function(){
	$("#bar_btn_exp").click(function(){
		$chok.view.query.fn.exp("exp.action", 
				                "${table.sqlName}",
				                "${table.sqlName}", 
				                "<#list table.columns as column>${column.underscoreName}<#if column_has_next>,</#if></#list>",
				                "<#list table.columns as column>${column.columnNameLower}<#if column_has_next>,</#if></#list>");
	});
};
// OVERWRITE-表格第一二列
$chok.view.query.fn.getColumns = function(){
	return $.merge([],$chok.view.query.config.tableColumns);
};
// 用户自定义
$chok.view.fn.customize = function(){
};