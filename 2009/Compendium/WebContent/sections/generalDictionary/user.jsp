<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<script language="JavaScript" type="text/javascript"
	src="${pageContext.request.contextPath}/common/validate.js"></script>
<script language="javascript">
	function Check(){
		var theForm = document.forms["compendium:section:content:userForm"];
		with(theForm){
			//if(!field_check(theForm['compendium:section:content:userForm:userID'], '№', 3, true)) return false;
			if(!field_check(theForm['compendium:section:content:userForm:login'], 'Логин', 0, true)) return false;
			if(!field_check(theForm['compendium:section:content:userForm:lastName'], 'Фамилия', 0, true)) return false;
			if(!field_check(theForm['compendium:section:content:userForm:curDepartment'], 'Департамент', 3, true)) return false;
			if(!field_check(theForm['compendium:section:content:userForm:eMail'], 'eMail', 5, false)) return false;
		}
		//storeID();
		return true;
	}
</script>
<hx:scriptCollector id="userScriptCollector">
<h:form id="userForm" styleClass="form"> 
<h:inputHidden  id="action" value="#{userBean.action}"/>
<h:inputHidden  id="userID" value="#{userBean.vo.id}"/>
	<h1>
		<h:outputText rendered="#{userBean.edit}">Редактирование пользователя</h:outputText>
		<h:outputText rendered="#{userBean.delete}">Удаление пользователя</h:outputText>
		<h:outputText rendered="#{userBean.add}">Создание пользователя</h:outputText>
	</h1>
	<fieldset><legend class="x">Параметры</legend>
	<h:messages errorClass="errors" layout="table"/>
	<table class="Basic">
		<tr>
			<td class="right">Логин:</td>
			<td><h:inputText styleClass="inputText" size="100" id="login"
				value="#{userBean.vo.login}"></h:inputText></td>
		</tr>
		<tr>
			<td class="right">Фамилия</td>
			<td><h:inputText styleClass="inputText" size="100" id="lastName"
				value="#{userBean.vo.name.last}"></h:inputText></td>
		</tr>		
		<tr>
			<td class="right">Имя:</td>
			<td><h:inputText styleClass="inputText" size="100"
				value="#{userBean.vo.name.first}"></h:inputText></td>
		</tr>
		<tr>
			<td class="right">Отчество:</td>
			<td><h:inputText styleClass="inputText" size="100"
				value="#{userBean.vo.name.middle}"></h:inputText></td>
		</tr>
		<tr>
			<td class="right">eMail:</td>
			<td><h:inputText styleClass="inputText" size="100" id="eMail"
				value="#{userBean.vo.EMail}"></h:inputText></td>
		</tr>
		<tr>
			<td class="right">Департамент:</td>
			<td>
				<h:inputText styleClass="inputText" size="70" id="curDepName"
					value="#{userBean.departmentName}"></h:inputText>
				<h:inputHidden id="curDepartment" value="#{userBean.vo.departmentID}"/>							
				<jsp:include page="/sections/popup/departmentCrCoListDialog.jsp" flush="false"/>
				<h:commandButton type="button" title="" value="Выбрать" rendered="#{operatorBean.adminUsers}"
												 styleClass="button" id="btnSelectDepartmentCrCoList"/>				
			</td>
		</tr>
		<tr id="collOrg">
			<td class="right">Коллегиальный орган:</td>
			<td>
				<h:inputText styleClass="inputText" size="70" id="collegOrganName" 
					value="#{userBean.collegOrganName}"></h:inputText>
				<h:inputHidden id="collegOrgan" value="#{userBean.collegOrgan}"/>							
				<jsp:include page="/sections/popup/departmentCollegOrganListDialog.jsp" flush="false"/> 
				<h:commandButton type="button" title="" value="Выбрать" rendered="#{operatorBean.adminUsers}"
												 styleClass="button" id="btnCollegOrganList"/>				
			</td>
		</tr>		
	</table>
	</fieldset>
	<div class="commands">
		<h:commandButton onclick="return Check();" rendered="#{userBean.add}" value="Создать" action="#{userBean.createAction}" styleClass="commandExButton button">
			<f:param name="chapter" value="questionList"/>
		</h:commandButton>
		<h:commandButton onclick="return Check();" rendered="#{userBean.edit}" value="Сохранить" action="#{userBean.saveAction}" styleClass="commandExButton button">
			<f:param name="chapter" value="questionList"/>
		</h:commandButton>
		<h:commandButton rendered="#{userBean.delete}" value="Удалить" action="#{userBean.deleteAction}" styleClass="commandExButton button">
			<f:param name="chapter" value="questionList"/>
		</h:commandButton>
		<h:commandButton value="Отменить" immediate="true" action="#{userBean.cancel}" styleClass="commandExButton button">
			<f:param name="chapter" value="questionList"/>
		</h:commandButton>
	</div>
</h:form>
</hx:scriptCollector>

<h:panelGrid rendered="#{!userBean.showCollegOrgan}">
<script language="javascript">
	//alert("логика для коллегиальных органов");
    var node=document.all('collOrg');
    if (node){
		node.style.display='none';
	}	
</script>
</h:panelGrid>