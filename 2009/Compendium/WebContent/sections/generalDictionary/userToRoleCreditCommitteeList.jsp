<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "ru.masterdm.compendium.beans.*"%>
	  <script type="text/javascript">
 		function SortBy(field, listForm) 
		{
			var myForm = document.forms(document.forms.length-1);
			e = myForm["compendium:section:content:userToRoleCreditCommitteeListForm:orderBy"];
			//alert(e.value);
			e.value = (e.value == field)?field + ' desc':field;
			document.body.style.cursor="wait";
			myForm.submit();
			return false;
		}
 		function SortAccessBy(field, listForm) 
		{
			var myForm = document.forms(document.forms.length-1);
			e = myForm["compendium:section:content:userToRoleCreditCommitteeListForm:orderAccessBy"];
			//alert(e.value);
			e.value = (e.value == field)?field + ' desc':field;
			document.body.style.cursor="wait";
			myForm.submit();
			return false;
		}		
		function changeProcess(listForm){
			e = listForm["compendium:section:content:userToRoleCreditCommitteeListForm:orderBy"];
			document.body.style.cursor="wait";
			listForm.submit();
		}
		function DeleteLink(roleId) {
			if(confirm('Исключить роль № '+ roleId +'?')) {
				listForm = document.forms["compendium:section:content:userToRoleCreditCommitteeListForm"];
				selectedID = listForm["compendium:section:content:userToRoleCreditCommitteeListForm:selectedID"];
				operation = listForm["compendium:section:content:userToRoleCreditCommitteeListForm:operation"];
				selectedID.value = roleId;
				operation.value = "<%= UserToRoleCreditCommitteeListBean.DELETE_LINK%>";
				document.body.style.cursor="wait";
				//listForm.submit();
				//return false;
			}else{
				alert("операция отменена");
				return false;
			}
		}
		function AddLink(roleId) {
			listForm = document.forms["compendium:section:content:userToRoleCreditCommitteeListForm"];
			selectedID = listForm["compendium:section:content:userToRoleCreditCommitteeListForm:selectedID"];
			operation = listForm["compendium:section:content:userToRoleCreditCommitteeListForm:operation"];
			selectedID.value = roleId;
			operation.value = "<%= UserToRoleCreditCommitteeListBean.ADD_LINK%>";
			document.body.style.cursor="wait";
			//listForm.submit();
		}
		function setActiveLink(roleId) {
			listForm = document.forms["compendium:section:content:userToRoleCreditCommitteeListForm"];
			selectedID = listForm["compendium:section:content:userToRoleCreditCommitteeListForm:selectedID"];
			operation = listForm["compendium:section:content:userToRoleCreditCommitteeListForm:operation"];
			selectedID.value = roleId;
			operation.value = "<%= UserToRoleCreditCommitteeListBean.ACTIVE_LINK%>";
			document.body.style.cursor="wait";
			//listForm.submit();
		}
		function setPassiveLink(roleId) {
			listForm = document.forms["compendium:section:content:userToRoleCreditCommitteeListForm"];
			selectedID = listForm["compendium:section:content:userToRoleCreditCommitteeListForm:selectedID"];
			operation = listForm["compendium:section:content:userToRoleCreditCommitteeListForm:operation"];
			selectedID.value = roleId;
			operation.value = "<%= UserToRoleCreditCommitteeListBean.PASSIVE_LINK%>";
			document.body.style.cursor="wait";
			//listForm.submit();
		}
		function setActiveAllLink(){
			listForm = document.forms["compendium:section:content:userToRoleCreditCommitteeListForm"];
			operation = listForm["compendium:section:content:userToRoleCreditCommitteeListForm:operation"];
			operation.value = "<%= UserToRoleCreditCommitteeListBean.ACTIVE_ALL_LINK%>";
			document.body.style.cursor="wait";		
		}
		function setPassiveAllLink(){
			listForm = document.forms["compendium:section:content:userToRoleCreditCommitteeListForm"];
			operation = listForm["compendium:section:content:userToRoleCreditCommitteeListForm:operation"];
			operation.value = "<%= UserToRoleCreditCommitteeListBean.PASSIVE_ALL_LINK%>";
			document.body.style.cursor="wait";		
		}
		function addAllLink(){
			if (confirm('Добавить все роли?')) {
				listForm = document.forms["compendium:section:content:userToRoleCreditCommitteeListForm"];
				operation = listForm["compendium:section:content:userToRoleCreditCommitteeListForm:operation"];
				operation.value = "<%= UserToRoleCreditCommitteeListBean.ADD_ALL_LINK%>";
				document.body.style.cursor="wait";		
			}
		}
		function deleteAllLink(){
			if (confirm('Исключить все роли?')){
				listForm = document.forms["compendium:section:content:userToRoleCreditCommitteeListForm"];
				operation = listForm["compendium:section:content:userToRoleCreditCommitteeListForm:operation"];
				operation.value = "<%= UserToRoleCreditCommitteeListBean.DELETE_ALL_LINK%>";
				document.body.style.cursor="wait";		
			}
		}
	  </script>
	<h:messages id="errorMessages" layout="table"/>
	<h:form id="userToRoleCreditCommitteeListForm">
		<h:inputHidden id="orderBy" value="#{userToRoleCreditCommitteeListBean.orderBy}"/>
		<h:inputHidden id="orderAccessBy" value="#{userToRoleCreditCommitteeListBean.orderAccessBy}"/>
		<h:inputHidden id="operation" value="#{userToRoleCreditCommitteeListBean.operation}"/>
		<h:inputHidden id="selectedID" value="#{userToRoleCreditCommitteeListBean.selectedID}"/>
		
		<h1><h:outputText value="Роли пользователя #{userToRoleCreditCommitteeListBean.userTO.vo.name} в системе Кредитных комитетов, департамент #{userToRoleCreditCommitteeListBean.userTO.depName}"/></h1>
		<h2>Текущие роли</h2>
		<h:commandButton rendered="#{operatorBean.adminApprove}" value="Сделать все роли активными" action="#{userToRoleCreditCommitteeListBean.processOperation}" onclick="setActiveAllLink()"  styleClass="button"/>
		<h:commandButton rendered="#{operatorBean.adminApprove}" value="Сделать все роли пассивными" action="#{userToRoleCreditCommitteeListBean.processOperation}" onclick="setPassiveAllLink()"  styleClass="button"/>
		<div class="filtered_result">
			<!-- Curent Role Table body -->
			<h:dataTable id="roleTable" rowClasses="a, b" styleClass="table" value="#{userToRoleCreditCommitteeListBean.list}" var="item" rows="50">
				<h:column id="idColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Код" onclick="SortBy('c.role.idRole' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.role.idRole' == userToRoleCreditCommitteeListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.role.idRole desc' == userToRoleCreditCommitteeListBean.orderBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{item.vo.id}" />
				</h:column>
				<h:column id="statuseColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Статус роли" onclick="SortBy('c.status' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.status' == userToRoleCreditCommitteeListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.status desc' == userToRoleCreditCommitteeListBean.orderBy}"/>
						</f:verbatim>
					</f:facet>					
					<h:panelGroup rendered="#{'Y' == item.status}">
						<h:graphicImage url="/theme/images/ico/3413.gif" alt="Роль активна"/> 
					  	<h:commandLink rendered="#{operatorBean.adminApprove}" action="#{userToRoleCreditCommitteeListBean.processOperation}" onclick="setPassiveLink('#{item.vo.id}')">
							Сделать пассивной
					    </h:commandLink>
					</h:panelGroup>
					<h:panelGroup rendered="#{'N' == item.status}">
						<h:graphicImage url="/theme/images/ico/3411.gif" alt="Роль пассивна"/> 
						<h:commandLink rendered="#{operatorBean.adminApprove}" action="#{userToRoleCreditCommitteeListBean.processOperation}" onclick="setActiveLink('#{item.vo.id}')">
							Сделать активной
						</h:commandLink>
					</h:panelGroup>	  
				</h:column>
				<h:column id="nameColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Роль Кредитного комитета" onclick="SortBy('c.role.roleName' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.role.roleName' == userToRoleCreditCommitteeListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.role.roleName desc' == userToRoleCreditCommitteeListBean.orderBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{item.vo.name}" />
				</h:column>
				<h:column>
					  <h:commandLink rendered="#{operatorBean.adminRoles}" action="#{userToRoleCreditCommitteeListBean.processOperation}" onclick="return DeleteLink('#{item.vo.id}')">
						 <h:graphicImage url="/theme/img/minus.png" alt="Исключить пользователя из роли"/>
					  </h:commandLink>
				</h:column>
			</h:dataTable>
			<div align="center">
				<h:outputText value="Записей не найдено" rendered="#{empty userToRoleCreditCommitteeListBean.list}"/>
			</div>
		</div>
					
		<!-- Table "listAccessRoleForUser" -->
		<h2>Доступные роли</h2>
		<h:commandButton rendered="#{operatorBean.adminRoles}" value="Добавить все роли" action="#{userToRoleCreditCommitteeListBean.processOperation}" onclick="addAllLink()"  styleClass="button"/>
		<h:commandButton rendered="#{operatorBean.adminRoles}" value="Удалить все роли" action="#{userToRoleCreditCommitteeListBean.processOperation}" onclick="deleteAllLink()"  styleClass="button"/>
		<div class="filtered_result">
			<!-- Curent Role Table body -->
			<h:dataTable id="accessRoleTable" rowClasses="a, b" styleClass="table" value="#{userToRoleCreditCommitteeListBean.accessList}" var="item" rows="50">
				<h:column id="idColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Код" onclick="SortAccessBy('c.idRole' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.idRole' == userToRoleCreditCommitteeListBean.orderAccessBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.idRole desc' == userToRoleCreditCommitteeListBean.orderAccessBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{item.vo.id}" />
				</h:column>
				<h:column id="nameColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Роль Кредитного комитета" onclick="SortAccessBy('c.roleName' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.roleName' == userToRoleCreditCommitteeListBean.orderAccessBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.roleName desc' == userToRoleCreditCommitteeListBean.orderAccessBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{item.vo.name}" />
				</h:column>
				<h:column>
					<h:commandLink rendered="#{operatorBean.adminRoles}" action="#{userToRoleCreditCommitteeListBean.processOperation}" onclick="AddLink('#{item.vo.id}')">
						<h:graphicImage url="/theme/img/plus.png" alt="Назначить пользователя на роль"/>
					</h:commandLink>
				</h:column>
			</h:dataTable>
			<div align="center">
				<h:outputText value="Записей не найдено" rendered="#{empty userToRoleCreditCommitteeListBean.accessList}"/>
			</div>
		</div>
	</h:form>
