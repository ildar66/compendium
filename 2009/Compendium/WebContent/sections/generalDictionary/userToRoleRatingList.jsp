<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "ru.masterdm.compendium.beans.*"%>
	  <script type="text/javascript">
 		function SortBy(field, listForm) 
		{
			var myForm = document.forms(document.forms.length-1);
			e = myForm["compendium:section:content:userToRoleRatingListForm:orderBy"];
			//alert(e.value);
			e.value = (e.value == field)?field + ' desc':field;
			document.body.style.cursor="wait";
			myForm.submit();
			return false;
		}
 		function SortAccessBy(field, listForm) 
		{
			var myForm = document.forms(document.forms.length-1);
			e = myForm["compendium:section:content:userToRoleRatingListForm:orderAccessBy"];
			//alert(e.value);
			e.value = (e.value == field)?field + ' desc':field;
			document.body.style.cursor="wait";
			myForm.submit();
			return false;
		}		
		function changeProcess(listForm){
			e = listForm["compendium:section:content:userToRoleRatingListForm:orderBy"];
			document.body.style.cursor="wait";
			listForm.submit();
		}
		function DeleteLink(roleId) {
			if(confirm('Исключить роль № '+ roleId +'?')) {
				listForm = document.forms["compendium:section:content:userToRoleRatingListForm"];
				selectedID = listForm["compendium:section:content:userToRoleRatingListForm:selectedID"];
				operation = listForm["compendium:section:content:userToRoleRatingListForm:operation"];
				selectedID.value = roleId;
				operation.value = "<%= UserToRoleRatingListBean.DELETE_LINK%>";
				document.body.style.cursor="wait";
				//listForm.submit();
				//return false;
			}else{
				alert("операция отменена");
				return false;
			}
		}
		function AddLink(roleId) {
			listForm = document.forms["compendium:section:content:userToRoleRatingListForm"];
			selectedID = listForm["compendium:section:content:userToRoleRatingListForm:selectedID"];
			operation = listForm["compendium:section:content:userToRoleRatingListForm:operation"];
			selectedID.value = roleId;
			operation.value = "<%= UserToRoleRatingListBean.ADD_LINK%>";
			document.body.style.cursor="wait";
			//listForm.submit();
		}
		function setActiveLink(roleId) {
			listForm = document.forms["compendium:section:content:userToRoleRatingListForm"];
			selectedID = listForm["compendium:section:content:userToRoleRatingListForm:selectedID"];
			operation = listForm["compendium:section:content:userToRoleRatingListForm:operation"];
			selectedID.value = roleId;
			operation.value = "<%= UserToRoleRatingListBean.ACTIVE_LINK%>";
			document.body.style.cursor="wait";
			//listForm.submit();
		}
		function setPassiveLink(roleId) {
			listForm = document.forms["compendium:section:content:userToRoleRatingListForm"];
			selectedID = listForm["compendium:section:content:userToRoleRatingListForm:selectedID"];
			operation = listForm["compendium:section:content:userToRoleRatingListForm:operation"];
			selectedID.value = roleId;
			operation.value = "<%= UserToRoleRatingListBean.PASSIVE_LINK%>";
			document.body.style.cursor="wait";
			//listForm.submit();
		}
		function setActiveAllLink() {
			listForm = document.forms["compendium:section:content:userToRoleRatingListForm"];
			operation = listForm["compendium:section:content:userToRoleRatingListForm:operation"];
			operation.value = "<%= UserToRoleRatingListBean.ACTIVE_ALL_LINK%>";
			document.body.style.cursor="wait";
			//listForm.submit();
		}		
		function setPassiveAllLink() {
			listForm = document.forms["compendium:section:content:userToRoleRatingListForm"];
			operation = listForm["compendium:section:content:userToRoleRatingListForm:operation"];
			operation.value = "<%= UserToRoleRatingListBean.PASSIVE_ALL_LINK%>";
			document.body.style.cursor="wait";
			//listForm.submit();
		}
		function addAllLink(){
			if (confirm('Добавить все роли?')) {
				listForm = document.forms["compendium:section:content:userToRoleRatingListForm"];
				operation = listForm["compendium:section:content:userToRoleRatingListForm:operation"];
				operation.value = "<%= UserToRoleRatingListBean.ADD_ALL_LINK%>";
				document.body.style.cursor="wait";		
			}
		}
		function deleteAllLink(){
			if (confirm('Исключить все роли?')){
				listForm = document.forms["compendium:section:content:userToRoleRatingListForm"];
				operation = listForm["compendium:section:content:userToRoleRatingListForm:operation"];
				operation.value = "<%= UserToRoleRatingListBean.DELETE_ALL_LINK%>";
				document.body.style.cursor="wait";		
			}
		}		
	  </script>
	<h:form id="userToRoleRatingListForm">
		<h:inputHidden id="orderBy" value="#{userToRoleRatingListBean.orderBy}"/>
		<h:inputHidden id="orderAccessBy" value="#{userToRoleRatingListBean.orderAccessBy}"/>
		<h:inputHidden id="operation" value="#{userToRoleRatingListBean.operation}"/>
		<h:inputHidden id="selectedID" value="#{userToRoleRatingListBean.selectedID}"/>
		<h1><h:outputText value="Роли пользователя #{userToRoleRatingListBean.userTO.vo.name} в системе Рейтингов, департамент #{userToRoleRatingListBean.userTO.depName}"/></h1>
		<h2>Текущие роли</h2>
		<h:commandButton rendered="#{operatorBean.adminApprove}" value="Сделать все роли активными" action="#{userToRoleRatingListBean.processOperation}" onclick="setActiveAllLink()"  styleClass="button"/>
		<h:commandButton rendered="#{operatorBean.adminApprove}" value="Сделать все роли пассивными" action="#{userToRoleRatingListBean.processOperation}" onclick="setPassiveAllLink()"  styleClass="button"/>
		<div class="filtered_result">
			<!-- Curent Role Table body -->
			<h:dataTable id="roleTable" rowClasses="a, b" styleClass="table" value="#{userToRoleRatingListBean.list}" var="item" rows="50">
				<h:column id="idColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Код" onclick="SortBy('c.role.idRole' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.role.idRole' == userToRoleRatingListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.role.idRole desc' == userToRoleRatingListBean.orderBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{item.vo.id}" />
				</h:column>
				<h:column id="statuseColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Статус роли" onclick="SortBy('c.status' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.status' == userToRoleRatingListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.status desc' == userToRoleRatingListBean.orderBy}"/>
						</f:verbatim>
					</f:facet>				
					<h:panelGroup rendered="#{'Y' == item.status}">
						<h:graphicImage url="/theme/images/ico/3413.gif" alt="Роль активна"/>
						<h:commandLink rendered="#{operatorBean.adminApprove}" action="#{userToRoleRatingListBean.processOperation}" onclick="setPassiveLink('#{item.vo.id}')">
							 Сделать пассивной
						</h:commandLink>
					</h:panelGroup>
					<h:panelGroup rendered="#{'N' == item.status}">
						<h:graphicImage url="/theme/images/ico/3411.gif" alt="Роль пассивна"/>
						<h:commandLink rendered="#{operatorBean.adminApprove}" action="#{userToRoleRatingListBean.processOperation}" onclick="setActiveLink('#{item.vo.id}')">
							Сделать активной
						</h:commandLink>
					</h:panelGroup>
				</h:column>
				<h:column id="nameColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Роль Рейтингов" onclick="SortBy('c.role.nameRole' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.role.nameRole' == userToRoleRatingListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.role.nameRole desc' == userToRoleRatingListBean.orderBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{item.vo.name}" />
				</h:column>
				<h:column>
					  <h:commandLink rendered="#{operatorBean.adminRoles}" action="#{userToRoleRatingListBean.processOperation}" onclick="return DeleteLink('#{item.vo.id}')">
						 <h:graphicImage url="/theme/img/minus.png" alt="Исключить пользователя из роли"/>
					  </h:commandLink>
				</h:column>
			</h:dataTable>
			<div align="center">
				<h:outputText value="Записей не найдено" rendered="#{empty userToRoleRatingListBean.list}"/>
			</div>
		</div>
		<!-- Table "listAccessRoleForUser" -->
		<h2>Доступные роли</h2>
		<h:commandButton rendered="#{operatorBean.adminRoles}" value="Добавить все роли" action="#{userToRoleRatingListBean.processOperation}" onclick="addAllLink()"  styleClass="button"/>
		<h:commandButton rendered="#{operatorBean.adminRoles}" value="Удалить все роли" action="#{userToRoleRatingListBean.processOperation}" onclick="deleteAllLink()"  styleClass="button"/>
		<div class="filtered_result">
			<!-- Curent Role Table body -->
			<h:dataTable id="accessRoleTable" rowClasses="a, b" styleClass="table" value="#{userToRoleRatingListBean.accessList}" var="item" rows="50">
				<h:column id="idColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Код" onclick="SortAccessBy('c.idRole' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.idRole' == userToRoleRatingListBean.orderAccessBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.idRole desc' == userToRoleRatingListBean.orderAccessBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{item.vo.id}" />
				</h:column>
				<h:column id="nameColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Роль Рейтингов" onclick="SortAccessBy('c.nameRole' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.nameRole' == userToRoleRatingListBean.orderAccessBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.nameRole desc' == userToRoleRatingListBean.orderAccessBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{item.vo.name}" />
				</h:column>
				<h:column>
					  <h:commandLink rendered="#{operatorBean.adminRoles}" action="#{userToRoleRatingListBean.processOperation}" onclick="AddLink('#{item.vo.id}')">
						 <h:graphicImage url="/theme/img/plus.png" alt="Назначить пользователя на роль"/>
					  </h:commandLink>
				</h:column>
			</h:dataTable>
			<div align="center">
				<h:outputText value="Записей не найдено" rendered="#{empty userToRoleRatingListBean.accessList}"/>
			</div>
		</div>
	</h:form>