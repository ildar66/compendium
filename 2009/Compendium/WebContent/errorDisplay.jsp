<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
   <%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
   <%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
   
   <f:loadBundle basename="com.corejsf.messages" var="msgs" />
   <f:subview id="dummy">
      <head>                  
         <title><h:outputText value="#{msgs.title}"/></title>
      </head>
      <body class="error">
         <h:form>
            <h1><h:outputText value="#{msgs.errorOccurred}"/></h1>
            <p><h:outputText value="#{msgs.copyReport}"/></p>
            <h:panelGrid><a href="./">На главную страницу</a></h:panelGrid>
            <h:inputTextarea value="#{error.stackTrace}" 
               rows="40" cols="120" readonly="true"/>
         </h:form>
      </body>
   </f:subview>
</html>
