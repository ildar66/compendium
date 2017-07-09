<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
      <script type="text/javascript">
 		function SortBy(field, listForm) 
		{
			var e = listForm["compendium:section:content:userCrCoListForm:orderBy"];
			//alert(e.value);
			e.value = (e.value == field)?field + ' desc':field;
		    document.body.style.cursor="wait";
			listForm.submit();
			return false;
		}         
      </script>
	<h:form id="userCrCoListForm">
	    <h:inputHidden id="orderBy" value="#{userCrCoListBean.orderBy}"/>
		<h1>Пользователи</h1>
		<div class="filter">
			<fieldset>
				<legend class="x">Фильтр запроса</legend>
				<h:messages id="errorMessages" layout="table"/>
				<div class="search">
					<h:selectOneMenu value="#{userCrCoListBean.searchFilter}" onchange="submit()">
						<f:selectItem itemLabel="ФИО или Логин" itemValue="0" />
						<f:selectItem itemLabel="Логин" itemValue="1"/>
						<f:selectItem itemLabel="ФИО" itemValue="2"/>
						<f:selectItem itemLabel="Департамент" itemValue="3"/>
						<f:selectItem itemLabel="eMail" itemValue="4"/>
					</h:selectOneMenu>
										
					<h:inputText value="#{userCrCoListBean.searchStr}" size="20"></h:inputText>
					<h:commandButton value="Найти!" action="#{userCrCoListBean.search}" styleClass="button search"/>
					<h:commandButton value="Очистить фильтр" action="#{userCrCoListBean.clearFilter}" styleClass="button search"/>
				</div>
				<span class="pager">
					<h:commandButton value="<" action="#{userCrCoListBean.previousPage}" disabled="#{!userCrCoListBean.page.previousPageAvailable}" onclick="document.body.style.cursor='wait'" styleClass="button"/>
					<h:commandButton value=">" action="#{userCrCoListBean.nextPage}" disabled="#{!userCrCoListBean.page.nextPageAvailable}" onclick="document.body.style.cursor='wait'" styleClass="button"/>
				</span>						
				<span class="set_number_of_rows">Показывать по <h:inputText value="#{userCrCoListBean.count}"></h:inputText> записей на странице</span>
			</fieldset>
		</div>
		<div class="filtered_result">
			<h:dataTable id="userTable" rowClasses="a, b" styleClass="table" value="#{userCrCoListBean.page.list}" var="user">
				<h:column id="idColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Код" onclick="SortBy('id' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'id' == userCrCoListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'id desc' == userCrCoListBean.orderBy}"/>
						</f:verbatim>						
					</f:facet>
					<h:outputText value="#{user.id}" />
				</h:column>
				<h:column id="loginColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Логин" onclick="SortBy('login' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'login' == userCrCoListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'login desc' == userCrCoListBean.orderBy}"/>
						</f:verbatim>						
					</f:facet>
					<h:outputText value="#{user.login}" />
				</h:column>				
				<h:column id="nameColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="ФИО" onclick="SortBy('SURNAME' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'SURNAME' == userCrCoListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'SURNAME desc' == userCrCoListBean.orderBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{user.name.last}"/>&nbsp;
					<h:outputText value="#{user.name.first}"/>&nbsp;
					<h:outputText value="#{user.name.middle}"/>					
				</h:column>
				<h:column id="eMailColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="eMail" onclick="SortBy('eMail' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'eMail' == userCrCoListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'eMail desc' == userCrCoListBean.orderBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{user.EMail}"/>
				</h:column>				
				<h:column id="departmentColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Департамент СПО" onclick="SortBy('depName' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'depName' == userCrCoListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'depName desc' == userCrCoListBean.orderBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{user.depName}"/>
				</h:column>
				<h:column>
					<f:facet name="header">
						<!--<h:outputText value="Департаменты КК"/>-->
					</f:facet>
				    <h:outputLink>
				       <h:graphicImage url="/theme/img/edit18.png" alt="Редактировать департаменты КК"/>
				       <f:param name="chapter" value="userCrCo"/>
		         	   <f:param name="userID" value="#{user.id}"/>
			           <f:param name="action" value="EDIT"/>
				    </h:outputLink>
				</h:column>
			</h:dataTable>
			<div align="center">
				<h:outputText value="Записей не найдено" rendered="#{empty userCrCoListBean.page}"/>
			</div>
		</div>
	</h:form>		
