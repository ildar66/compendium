<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="" location="" --%><%-- /jsf:pagecode --%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
	<script type="text/javascript" language="JavaScript1.2">
			//сохранить:
            function doSave(value, name) {
               var btn=event.srcElement;
               var myForm=document.forms(document.forms.length-1);
               var formId = myForm.id;
               document.forms[formId][formId + ":curDepartment"].value = value;
               document.forms[formId][formId + ":curDepName"].value = name;
               //логика для коллегиальных органов:
               var node=document.all('collOrg');
               if (node && node.style.display=='none'){
               		node.style.display='';
               }
               //закрыть окно:               
 			   var node=document.all[formId + ':departmentCrCoSelect_cb'];
			   if (node) node.fireEvent('onclick');
			       return true;
            }
            //сохранить и скрыть row collegOrgan:
            function doSaveAndHideCollegOrgan(value, name) {
               var btn=event.srcElement;
               var myForm=document.forms(document.forms.length-1);
               var formId = myForm.id;
               document.forms[formId][formId + ":curDepartment"].value = value;
               document.forms[formId][formId + ":curDepName"].value = name;
               //логика для коллегиальных органов:
               var node=document.all('collOrg');
               if (node){
               		node.style.display='none';
               		document.forms[formId][formId + ":collegOrgan"].value = "";
               		document.forms[formId][formId + ":collegOrganName"].value = "";               		
               }
               //закрыть окно:
			   var node=document.all[formId + ':departmentCrCoSelect_cb'];
			   if (node) node.fireEvent('onclick');
			       return true;
            } 
	</script>
<hx:panelDialog type="modal" id="departmentCrCoSelect"
	for="btnSelectDepartmentCrCoList" styleClass="panelDialog" saveState="true">
	<h:outputText value="Выберите департамент" />
	<div style="overflow: auto; height: 400px;">
	<t:tree2 id="clientTree" value="#{departmentCrCoListBean.expandedTreeData}"
		var="node" varNodeToggler="t">
		<f:facet name="general-folder">
			<h:panelGroup>
					<t:graphicImage value="theme/images/yellow-folder-open.png"
						rendered="#{t.nodeExpanded}" border="0" alt="" />
					<t:graphicImage value="theme/images/yellow-folder-closed.png"
						rendered="#{!t.nodeExpanded}" border="0" alt="" />
					<h:outputText value="#{node.description}" styleClass="nodeFolder" />
					<h:outputText value=" Всего центральных(#{node.childCount})"
						styleClass="childCount" rendered="#{!empty node.children}" />
			</h:panelGroup>
		</f:facet>
		<f:facet name="centralDepartment">
				<h:outputLink value="#"
					onclick="return doSave('#{node.identifier}', '#{node.description}');">
					<t:graphicImage value="theme/images/person.png" border="0" alt="" />
					<h:outputText value="#{node.description}" styleClass="nodeFolder" />
					<h:outputText value=" (#{node.childCount})" styleClass="childCount"
						rendered="#{!empty node.children}" />
				</h:outputLink>			
		</f:facet>
		<f:facet name="centralDepartment_IdCommitteeType_1">
				<h:outputLink value="#"
					onclick="return doSave('#{node.identifier}', '#{node.description}');">
					<t:graphicImage value="theme/images/person.png" border="0" alt="" />
					<h:outputText value="#{node.description}" styleClass="nodeFolder" />
					<h:outputText value=" (#{node.childCount})" styleClass="childCount"
						rendered="#{!empty node.children}" />
				</h:outputLink>		
		</f:facet>
		<f:facet name="centralDepartment_IdCommitteeType_2">
				<h:outputLink value="#"
					onclick="return doSave('#{node.identifier}', '#{node.description}');">
					<t:graphicImage value="theme/images/person.png" border="0" alt="" />
					<h:outputText value="#{node.description}" styleClass="nodeFolder" />
					<h:outputText value=" (#{node.childCount})" styleClass="childCount"
						rendered="#{!empty node.children}" />
				</h:outputLink>			
		</f:facet>
		<f:facet name="centralDepartment_IdCommitteeType_3">
				<h:outputLink value="#"
					onclick="return doSave('#{node.identifier}', '#{node.description}');">
					<t:graphicImage value="theme/images/person.png" border="0" alt="" />
					<h:outputText value="#{node.description}" styleClass="nodeFolder" />
					<h:outputText value=" (#{node.childCount})" styleClass="childCount"
						rendered="#{!empty node.children}" />
				</h:outputLink>			
		</f:facet>
		<f:facet name="centralDepartment_IdCommitteeType_4">
				<h:outputLink value="#"
					onclick="return doSaveAndHideCollegOrgan('#{node.identifier}', '#{node.description}');">
					<t:graphicImage value="theme/images/person.png" border="0" alt="" />
					<h:outputText value="#{node.description}" styleClass="nodeFolder" />
					<h:outputText value=" (#{node.childCount})" styleClass="childCount"
						rendered="#{!empty node.children}" />
				</h:outputLink>			
		</f:facet>
		<f:facet name="childDepartmentNode">
				<h:outputLink value="#"
					onclick="return doSave('#{node.identifier}', '#{node.description}');">
					<t:graphicImage value="theme/images/document.png" border="0" alt="" />
					<h:outputText value="#{node.description}" />
				</h:outputLink>
		</f:facet>
		<f:facet name="childDepartmentNode_IdCommitteeType_1">
				<h:outputLink value="#"
					onclick="return doSave('#{node.identifier}', '#{node.description}');">
					<t:graphicImage value="theme/images/document.png" border="0" alt="" />
					<h:outputText value="#{node.description}" />
				</h:outputLink>
		</f:facet>
		<f:facet name="childDepartmentNode_IdCommitteeType_2">
				<h:outputLink value="#"
					onclick="return doSave('#{node.identifier}', '#{node.description}');">
					<t:graphicImage value="theme/images/document.png" border="0" alt="" />
					<h:outputText value="#{node.description}" />
				</h:outputLink>
		</f:facet>
		<f:facet name="childDepartmentNode_IdCommitteeType_3">
				<h:outputLink value="#"
					onclick="return doSave('#{node.identifier}', '#{node.description}');">
					<t:graphicImage value="theme/images/document.png" border="0" alt="" />
					<h:outputText value="#{node.description}" />
				</h:outputLink>
		</f:facet>
		<f:facet name="childDepartmentNode_IdCommitteeType_4">
				<h:outputLink value="#"
					onclick="return doSaveAndHideCollegOrgan('#{node.identifier}', '#{node.description}');">
					<t:graphicImage value="theme/images/document.png" border="0" alt="" />
					<h:outputText value="#{node.description}" />
				</h:outputLink>
		</f:facet>
	</t:tree2>
	</div>
</hx:panelDialog>