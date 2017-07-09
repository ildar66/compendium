<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<h:form id="sectionSelectorForm">
	<h:panelGrid columns="2">
		<h:panelGroup>
			<h:outputText value="Выберите справочник: "/>
			<h:selectOneMenu onchange="submit()" value="#{compendium.section}" valueChangeListener="#{compendium.sectionSelected}">
				<f:selectItems value="#{compendium.sectionItems}"/>
			</h:selectOneMenu>
		</h:panelGroup>
		<h:panelGroup>
			<h:outputText value="Оператор:"/>
			<B><%=request.getRemoteUser()%></B>
		</h:panelGroup>
	</h:panelGrid>
</h:form>