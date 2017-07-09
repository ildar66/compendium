<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<h:form id="sectionMenuForm">
<h:dataTable value="#{section.chapterKeys}" var="chapterKey" styleClass="links" columnClasses="linksColumn">
   <h:column>
      <h:commandLink immediate="true">
         <h:outputText value="#{msgs[chapterKey]}"/>
         <f:param name="chapter" value="#{chapterKey}"/>
      </h:commandLink>
   </h:column>
</h:dataTable>
</h:form>
