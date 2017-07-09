<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<h:panelGrid columns="2" styleClass="sectionHeader">
   <h:graphicImage value="#{section.image}"/>
   <h:outputText value="#{msgs[section.titleKey]}" styleClass="sectionTitle"/>
</h:panelGrid>
