<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
      <script type="text/javascript">
 		function SortBy(field, listForm) 
		{
			var myForm = document.forms(document.forms.length-1);
			var e = myForm["compendium:section:content:userListForm:orderBy"];
			e.value = (e.value == field)?field + ' desc':field;
		    document.body.style.cursor="wait";
			myForm.submit();
			return false;
		}         
      </script>
	<h:form id="userListForm">
	    <h:inputHidden id="orderBy" value="#{userListBean.orderBy}"/>
		<h1>Пользователи</h1>
		<div class="filter">
			<fieldset>
				<legend class="x">Фильтр запроса</legend>
				<div class="search"><h:messages id="errorMessages" layout="table"/>
					<h:selectOneMenu value="#{userListBean.searchFilter}" onchange="submit()">
						<f:selectItem itemLabel="ФИО или Логин" itemValue="0" />
						<f:selectItem itemLabel="Логин" itemValue="1"/>
						<f:selectItem itemLabel="ФИО" itemValue="2"/>
						<f:selectItem itemLabel="Департамент" itemValue="3"/>
						<f:selectItem itemLabel="eMail" itemValue="4"/>
					</h:selectOneMenu>
					<h:inputText value="#{userListBean.searchStr}" size="20"></h:inputText>
					<h:commandButton value="Найти!" action="#{userListBean.search}" styleClass="button search"/>					
					<!-- <td>Старт:<h:inputText value="#{userListBean.start}" size="5"></h:inputText></td> -->
					<h:commandButton value="Очистить фильтр" action="#{userListBean.clearFilter}" styleClass="button search"/>
				</div>
				<span class="set_number_of_rows">Показывать по <h:inputText value="#{userListBean.count}" styleClass="text"></h:inputText> записей на странице</span>
				<span class="pager">
					<h:commandButton title="Предыдущая страница" value="<" action="#{userListBean.previousPage}" rendered="#{userListBean.page.previousPageAvailable}" onclick="document.body.style.cursor='wait'" styleClass="button"/>
					<h:commandButton title="Следующая страница" value=">" action="#{userListBean.nextPage}" rendered="#{userListBean.page.nextPageAvailable}" onclick="document.body.style.cursor='wait'" styleClass="button"/>
				</span>
			</fieldset>
		</div>
			
		<div class="filtered_result"  style="overflow: auto; height: 600px;">
			<h:dataTable id="userTable" rowClasses="a, b" styleClass="Basic table" value="#{userListBean.page.list}" var="user">
				<h:column>
					<f:facet name="header">
						<h:commandLink action="#{userBean.initVO}" rendered="#{operatorBean.adminUsers}">
							<h:graphicImage value="/theme/img/plus.png" alt="Создать"/>
						    <f:param name="chapter" value="user"/>
				         	<f:param name="action" value="ADD"/>									
						</h:commandLink>
					</f:facet>				
				    <h:commandLink action="#{userBean.initVO}" rendered="#{operatorBean.adminUsers}">
				       <h:graphicImage url="/theme/img/edit18.png" alt="Редактировать"/>
				       <f:param name="chapter" value="user"/>
		         	   <f:param name="id" value="#{user.vo.id}"/>
			           <f:param name="action" value="EDIT"/>
				    </h:commandLink>
				    <h:commandLink action="#{userBean.initVO}" rendered="#{!operatorBean.adminUsers}">
				       <h:graphicImage url="/theme/img/edit18.png" alt="Просмотр"/>
				       <f:param name="chapter" value="user"/>
		         	   <f:param name="id" value="#{user.vo.id}"/>
			           <f:param name="action" value="VIEW"/>
				    </h:commandLink>
				</h:column>
				<h:column id="idColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Код" onclick="SortBy('id' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'id' == userListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'id desc' == userListBean.orderBy}"/>
						</f:verbatim>						
					</f:facet>
					<h:outputText value="#{user.vo.id}" />
				</h:column>
				<h:column id="loginColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Логин" onclick="SortBy('login' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'login' == userListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'login desc' == userListBean.orderBy}"/>
						</f:verbatim>						
					</f:facet>
					<h:outputText value="#{user.vo.login}" />
				</h:column>				
				<h:column id="nameColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="ФИО" onclick="SortBy('SURNAME' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'SURNAME' == userListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'SURNAME desc' == userListBean.orderBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{user.vo.name.last}"/> <h:outputText value="#{user.vo.name.first}"/> <h:outputText value="#{user.vo.name.middle}"/>
				</h:column>
				<h:column id="eMailColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="eMail" onclick="SortBy('eMail' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'eMail' == userListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'eMail desc' == userListBean.orderBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{user.vo.EMail}"/>
				</h:column>				
				<h:column id="departmentColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Департамент" onclick="SortBy('depName' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'depName' == userListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'depName desc' == userListBean.orderBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{user.depName}"/>
				</h:column>
				<h:column>
					 <f:facet name="header">
					 	<h:outputText value="СПО"/>
					 </f:facet>
				     <h:outputLink>
				     	<h:outputText value="Роли"/>
				        <f:param name="chapter" value="userToRoleList"/>
				        <f:param name="id" value="#{user.vo.id}"/>
				     </h:outputLink>				
				</h:column>
				<h:column>
					 <f:facet name="header">
					 	<h:outputText value="КК"/>
					 </f:facet>
				     <h:outputLink>
				     	<h:outputText value="Роли"/>
				        <f:param name="chapter" value="userToRoleCreditCommitteeList"/>
				        <f:param name="id" value="#{user.vo.id}"/>
				     </h:outputLink>				
				</h:column>
				<h:column>
					 <f:facet name="header">
					 	<h:outputText value="Рейтинги"/>
					 </f:facet>
				     <h:outputLink>
				     	<h:outputText value="Роли"/>
				        <f:param name="chapter" value="userToRoleRatingList"/>
				        <f:param name="id" value="#{user.vo.id}"/>
				     </h:outputLink>				
				</h:column>
				<h:column>
				      <h:commandLink action="#{userBean.initVO}" rendered="#{operatorBean.adminUsers}">
				         <h:graphicImage url="/theme/img/delete.gif" alt="Удалить"/>
				         <f:param name="chapter" value="user"/>
				         <f:param name="id" value="#{user.vo.id}"/>
				         <f:param name="action" value="DELETE"/>
				      </h:commandLink>				
				</h:column>
			</h:dataTable>
			<div align="center">
				<h:outputText value="Записей не найдено" rendered="#{empty userListBean.page}"/>
			</div>
		</div>
	</h:form>		
