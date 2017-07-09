<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core"  prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html"  prefix="h" %>
<%@ taglib uri="http://jakarta.apache.org/tiles" prefix="tiles" %>

<h:panelGrid columns="1" styleClass="section" headerClass="compendiumHeader">
   <f:facet name="header">
      <f:subview id="header">
         <tiles:insert attribute="header" flush="false"/>
      </f:subview>
   </f:facet>

   <f:subview id="section">
      <tiles:insert attribute="section" flush="false"/>
   </f:subview>
</h:panelGrid>
