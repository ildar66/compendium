<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "ru.masterdm.compendium.beans.*"%>
	<script type="text/javascript">
 		function SortBy(field, listForm) 
		{
			var myForm = document.forms(document.forms.length-1);
			e = myForm["compendium:section:content:userToRoleListForm:orderBy"];
			e.value = (e.value == field)?field + ' desc':field;
			document.body.style.cursor="wait";
			myForm.submit();
			return false;
		}
 		function SortAccessBy(field, listForm) 
		{
			var myForm = document.forms(document.forms.length-1);
			e = myForm["compendium:section:content:userToRoleListForm:orderAccessBy"];
			//alert(e.value);
			e.value = (e.value == field)?field + ' desc':field;
			document.body.style.cursor="wait";
			myForm.submit();
			return false;
		}		
		function changeProcess(listForm){
			e = listForm["compendium:section:content:userToRoleListForm:orderBy"];
			document.body.style.cursor="wait";
			listForm.submit();
		}
		function DeleteLink(roleId) {
			if(confirm('Исключить роль № '+ roleId +'?')) {
				listForm = document.forms["compendium:section:content:userToRoleListForm"];
				selectedID = listForm["compendium:section:content:userToRoleListForm:selectedID"];
				operation = listForm["compendium:section:content:userToRoleListForm:operation"];
				selectedID.value = roleId;
				operation.value = "<%= UserToRoleListBean.DELETE_LINK%>";
				document.body.style.cursor="wait";
				//listForm.submit();
				//return false;
			}else{
				alert("операция отменена");
				return false;
			}			
		}
		function AddLink(roleId) {
			listForm = document.forms["compendium:section:content:userToRoleListForm"];
			selectedID = listForm["compendium:section:content:userToRoleListForm:selectedID"];
			operation = listForm["compendium:section:content:userToRoleListForm:operation"];
			selectedID.value = roleId;
			operation.value = "<%= UserToRoleListBean.ADD_LINK%>";
			document.body.style.cursor="wait";
			//listForm.submit();
		}
		function setActiveLink(roleId) {
			listForm = document.forms["compendium:section:content:userToRoleListForm"];
			selectedID = listForm["compendium:section:content:userToRoleListForm:selectedID"];
			operation = listForm["compendium:section:content:userToRoleListForm:operation"];
			selectedID.value = roleId;
			operation.value = "<%= UserToRoleListBean.ACTIVE_LINK%>";
			document.body.style.cursor="wait";
			//listForm.submit();
		}
		function setPassiveLink(roleId) {
			listForm = document.forms["compendium:section:content:userToRoleListForm"];
			selectedID = listForm["compendium:section:content:userToRoleListForm:selectedID"];
			operation = listForm["compendium:section:content:userToRoleListForm:operation"];
			selectedID.value = roleId;
			operation.value = "<%= UserToRoleListBean.PASSIVE_LINK%>";
			document.body.style.cursor="wait";
			//listForm.submit();
		}
		function activeAllLink(){
			listForm = document.forms["compendium:section:content:userToRoleListForm"];
			operation = listForm["compendium:section:content:userToRoleListForm:operation"];
			operation.value = "<%= UserToRoleListBean.ACTIVE_ALL_LINK%>";
			document.body.style.cursor="wait";
		}
		function passiveAllLink(){
			listForm = document.forms["compendium:section:content:userToRoleListForm"];
			operation = listForm["compendium:section:content:userToRoleListForm:operation"];
			operation.value = "<%= UserToRoleListBean.PASSIVE_ALL_LINK%>";
			document.body.style.cursor="wait";
		}
		function addLinkByProcess(){
			if (confirm('Добавить все роли про бизнес-процессу?')) {
				listForm = document.forms["compendium:section:content:userToRoleListForm"];
				operation = listForm["compendium:section:content:userToRoleListForm:operation"];
				operation.value = "<%= UserToRoleListBean.ADD_ALL_BY_PROCESS_LINK%>";
				document.body.style.cursor="wait";		
			}
		}
		function deleteLinkByProcess(){
			if (confirm('Исключить все роли по бизнес-процессу?')){
				listForm = document.forms["compendium:section:content:userToRoleListForm"];
				operation = listForm["compendium:section:content:userToRoleListForm:operation"];
				operation.value = "<%= UserToRoleListBean.DELETE_ALL_BY_PROCESS_LINK%>";
				document.body.style.cursor="wait";		
			}
		}
	</script>
	<h:form id="userToRoleListForm">
		<h:inputHidden id="orderBy" value="#{userToRoleListBean.orderBy}"/>
		<h:inputHidden id="orderAccessBy" value="#{userToRoleListBean.orderAccessBy}"/>
		<h:inputHidden id="operation" value="#{userToRoleListBean.operation}"/>
		<h:inputHidden id="selectedID" value="#{userToRoleListBean.selectedID}"/>
		<h1><h:outputText value="Роли пользователя #{userToRoleListBean.userTO.vo.name} системы СПО, департамент #{userToRoleListBean.userTO.depName}"/></h1>
		<h2>Текущие роли</h2>
		<h:commandButton rendered="#{operatorBean.adminApprove}" value="Сделать все роли активными" action="#{userToRoleListBean.processOperation}" onclick="activeAllLink()" styleClass="button"/>
		<h:commandButton rendered="#{operatorBean.adminApprove}" value="Сделать все роли пассивными" action="#{userToRoleListBean.processOperation}" onclick="passiveAllLink()" styleClass="button"/>
		<div class="filtered_result" style="overflow: auto; height: 300px;">
			<!-- Curent Role Table body -->
			<h:dataTable id="roleTable" rowClasses="a, b" styleClass="table" value="#{userToRoleListBean.list}" var="item">
				<h:column id="idColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Код" onclick="SortBy('c.role.idRole' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.role.idRole' == userToRoleListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.role.idRole desc' == userToRoleListBean.orderBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{item.vo.id}" />
				</h:column>
				<h:column id="statuseColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Статус роли" onclick="SortBy('c.status' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.status' == userToRoleListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.status desc' == userToRoleListBean.orderBy}"/>
						</f:verbatim>
					</f:facet>				
					<h:panelGroup rendered="#{'Y' == item.status}">
						<h:graphicImage url="/theme/images/ico/3413.gif" alt="Роль активна"/>
						<h:commandLink rendered="#{operatorBean.adminApprove}" action="#{userToRoleListBean.processOperation}" onclick="setPassiveLink('#{item.vo.id}')">
							Сделать пассивной
						</h:commandLink>
					</h:panelGroup>
					<h:panelGroup rendered="#{'N' == item.status}">
						<h:graphicImage url="/theme/images/ico/3411.gif" alt="Роль пассивна"/>
						<h:commandLink rendered="#{operatorBean.adminApprove}" action="#{userToRoleListBean.processOperation}" onclick="setActiveLink('#{item.vo.id}')">
							Сделать активной
						</h:commandLink>
					</h:panelGroup>
				</h:column>
				<h:column id="nameColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Роль СПО" onclick="SortBy('c.role.nameRole' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.role.nameRole' == userToRoleListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.role.nameRole desc' == userToRoleListBean.orderBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{item.vo.name}" />
				</h:column>
				<h:column id="processColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Процесс СПО" onclick="SortBy('c.role.process.descriptionProcess' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.role.process.descriptionProcess' == userToRoleListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.role.process.descriptionProcess desc' == userToRoleListBean.orderBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{item.processName}" />
				</h:column>
				<h:column>
					<h:commandLink rendered="#{operatorBean.adminRoles}" action="#{userToRoleListBean.processOperation}" onclick="return DeleteLink('#{item.vo.id}')">
						<h:graphicImage url="/theme/img/minus.png" alt="Исключить пользователя из роли"/>
					</h:commandLink>
				</h:column>
			</h:dataTable>
			<div align="center">
				<h:outputText value="Записей не найдено" rendered="#{empty userToRoleListBean.list}"/>
			</div>
		</div>
		<!-- Table "listAccessRoleForUser" -->
		<h2>Доступные роли</h2>
		<div>Роли СПО для включения по процессам:
			<h:selectOneMenu value="#{userToRoleListBean.processID}" id="processID" onchange="changeProcess(this.form);">
				<f:selectItem itemLabel="все процессы" itemValue="ALL" />
				<f:selectItems value="#{processTypeList}"/>
			</h:selectOneMenu>
		</div>
		<h:commandButton rendered="#{operatorBean.adminRoles}" value="Добавить все роли" action="#{userToRoleListBean.processOperation}" onclick="addLinkByProcess()"  styleClass="button"/>
		<h:commandButton rendered="#{operatorBean.adminRoles}" value="Удалить все роли" action="#{userToRoleListBean.processOperation}" onclick="deleteLinkByProcess()"  styleClass="button"/>		
		<div class="filtered_result" style="overflow: auto; height: 300px;">
			<!-- Curent Role Table body -->
			<h:dataTable id="accessRoleTable" rowClasses="a, b" styleClass="Basic table" value="#{userToRoleListBean.accessList}" var="item">
				<h:column id="idColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Код" onclick="SortAccessBy('c.idRole' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.idRole' == userToRoleListBean.orderAccessBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.idRole desc' == userToRoleListBean.orderAccessBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{item.vo.id}" />
				</h:column>
				<h:column id="nameColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Роль СПО" onclick="SortAccessBy('c.nameRole' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.nameRole' == userToRoleListBean.orderAccessBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.nameRole desc' == userToRoleListBean.orderAccessBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{item.vo.name}" />
				</h:column>
				<h:column id="processColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Процесс СПО" onclick="SortAccessBy('c.process.descriptionProcess' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.process.descriptionProcess' == userToRoleListBean.orderAccessBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.process.descriptionProcess desc' == userToRoleListBean.orderAccessBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{item.processName}" />
				</h:column>
				<h:column>
					<h:commandLink rendered="#{operatorBean.adminRoles}" action="#{userToRoleListBean.processOperation}" onclick="AddLink('#{item.vo.id}')">
						<h:graphicImage url="/theme/img/plus.png" alt="Назначить пользователя на роль"/>
					</h:commandLink>
				</h:column>
			</h:dataTable>
			<div align="center">
				<h:outputText value="Записей не найдено" rendered="#{empty userToRoleListBean.accessList}"/>
			</div>
		</div>
	</h:form>