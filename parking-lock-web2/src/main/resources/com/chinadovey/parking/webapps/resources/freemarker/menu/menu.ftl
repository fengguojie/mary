<#list menus?if_exists as menu>   
<li class="treeview menu-action">
         <a ${(menu.childrens??)?string('href=#','href='+menu.menuAction!)}> 
         <i	class="fa ${menu.menuIcon!'fa-bar-chart-o'}"></i> <span>${menu.menuName!}</span> 
         <i class="fa ${(menu.childrens??)?string('fa-angle-left pull-right','')}"></i>
					</a>
			<ul class="treeview-menu">
				<#if menu.childrens??>
			      <@sub_menu menu.childrens/>
		    </#if>
		 </ul></li>
						
</#list>
<#macro sub_menu list>
	<#list list?if_exists as item>
	<li><a href="${item.menuAction!}">
		<i class="fa fa-angle-double-right"></i>${item.menuName}</a></li>
	</#list>
</#macro>
