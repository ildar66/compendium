<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<hx:scriptCollector id="userCrCoScriptCollector">
<h:form id="userCrCoForm" styleClass="form">
<h:inputHidden  id="action" value="#{userCrCoBean.action}"/>
<h:inputHidden  id="userID" value="#{userCrCoBean.userID}"/>
	<h1>
		<h:outputText rendered="#{userCrCoBean.edit}">Привязка пользователя к департаментам Кредитного комитета</h:outputText>
		<h:outputText rendered="#{userCrCoBean.delete}">Удаление пользователя КК</h:outputText>
		<h:outputText rendered="#{userCrCoBean.add}">Создание пользователя КК</h:outputText>
	</h1>
	<fieldset><legend class="x">Параметры</legend>
	<h:messages id="errorMessages" layout="list" errorClass="errorMessage" />
	<table class="Basic">
		<tr>
			<td class="right">№:</td>
			<td><h:outputText styleClass="inputText" value="#{userCrCoBean.vo.id}"></h:outputText></td>
		</tr>
		<tr>
			<td class="right">Логин:</td>
			<td><h:outputText styleClass="inputText" value="#{userCrCoBean.vo.login}"></h:outputText></td>
		</tr>
		<tr>
			<td class="right">Фамилия</td>
			<td><h:outputText styleClass="inputText" value="#{userCrCoBean.vo.name.last}"></h:outputText></td>
		</tr>		
		<tr>
			<td class="right">Имя:</td>
			<td><h:outputText styleClass="inputText" value="#{userCrCoBean.vo.name.first}"></h:outputText></td>
		</tr>
		<tr>
			<td class="right">Отчество:</td>
			<td><h:outputText styleClass="inputText" value="#{userCrCoBean.vo.name.middle}"></h:outputText></td>
		</tr>
		<tr>
			<td class="right">eMail:</td>
			<td><h:outputText styleClass="inputText" value="#{userCrCoBean.vo.EMail}"></h:outputText></td>
		</tr>
		<tr>
			<td class="right">Департамент:</td>
			<td>
				<h:inputText styleClass="inputText" size="70" id="curDepName" 
					value="#{userCrCoBean.departmentName}"></h:inputText>
				<h:inputHidden id="curDepartment" value="#{userCrCoBean.department}"/>							
				<jsp:include page="/sections/popup/departmentCrCoListDialog.jsp" flush="false"/> 
				<h:commandButton type="button" title="" value="Выбрать"
												 styleClass="button" id="btnSelectDepartmentList"/>				
			</td>
		</tr>
		<tr>
			<td class="right">Коллегиальный орган:</td>
			<td>
				<h:inputText styleClass="inputText" size="70" id="collegOrganName" 
					value="#{userCrCoBean.collegOrganName}"></h:inputText>
				<h:inputHidden id="collegOrgan" value="#{userCrCoBean.collegOrgan}"/>							
				<jsp:include page="/sections/popup/departmentCollegOrganListDialog.jsp" flush="false"/> 
				<h:commandButton type="button" title="" value="Выбрать"
												 styleClass="button" id="btnCollegOrganList"/>				
			</td>
		</tr>		
	</table>
	</fieldset>
	<div class="commands">
		<h:commandButton value="Сохранить" action="#{userCrCoBean.saveAction}" styleClass="commandExButton button">
			<f:param name="chapter" value="questionList"/>
		</h:commandButton>
		<h:commandButton value="Отменить" immediate="true" action="#{userCrCoBean.cancel}" styleClass="commandExButton button">
			<f:param name="chapter" value="questionList"/>
		</h:commandButton>
	</div>
</h:form>
</hx:scriptCollector>
