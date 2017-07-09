<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="" location="" --%><%-- /jsf:pagecode --%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<h:form id="guestionType" styleClass="form">
<h:inputHidden  id="action" value="#{questionTypeBean.action}"/>
	<h1>
		<h:outputText rendered="#{questionTypeBean.edit}">Редактирование Классификатора вопроса</h:outputText>
		<h:outputText rendered="#{questionTypeBean.delete}">Удаление Классификатора вопроса</h:outputText>
		<h:outputText rendered="#{questionTypeBean.add}">Создание Классификатора вопроса</h:outputText>
	</h1>
	<fieldset><legend class="x">Параметры</legend>
	<h:messages id="errorMessages" layout="list" errorClass="errorMessage" />
	<table class="Basic">
		<tr>
			<td class="right">Код:</td>
			<td><h:inputText styleClass="inputText" size="100"
				value="#{questionTypeBean.vo.id}"></h:inputText></td>
		</tr>
		<tr>
			<td class="right">Название:</td>
			<td><h:inputText styleClass="inputText" size="100"
				value="#{questionTypeBean.vo.name}"></h:inputText></td>
		</tr>
	</table>
	</fieldset>
	<div class="commands">
		<h:commandButton value="Создать" rendered="#{questionTypeBean.add}" action="#{questionTypeBean.createAction}" styleClass="commandExButton button">
			<f:param name="chapter" value="questionList"/>
		</h:commandButton>
		<h:commandButton value="Сохранить" rendered="#{questionTypeBean.edit}" action="#{questionTypeBean.saveAction}" styleClass="commandExButton button">
			<f:param name="chapter" value="questionList"/>
		</h:commandButton>
		<h:commandButton value="Удалить" rendered="#{questionTypeBean.delete}" action="#{questionTypeBean.deleteAction}" styleClass="commandExButton button">
			<f:param name="chapter" value="questionList"/>
		</h:commandButton>
		<h:commandButton value="Отменить" immediate="true" action="#{questionTypeBean.cancel}" styleClass="commandExButton button">
			<f:param name="chapter" value="questionList"/>
		</h:commandButton>
	</div>
</h:form>
