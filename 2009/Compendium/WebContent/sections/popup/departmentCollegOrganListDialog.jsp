<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="" location="" --%><%-- /jsf:pagecode --%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
	<script type="text/javascript" language="JavaScript1.2">
            function doSaveCollegOrgan(value, name) {
               //var formId = window.openerFormId;
               //opener.document.forms[formId][formId + ":state"].value = value;
               //window.close();
               var btn=event.srcElement;
               //var myForm=getParent(btn,'FORM');
               var myForm=document.forms(document.forms.length-1);
               //var formId = "compendium:section:content:departmentListForm";
               var formId = myForm.id;
               document.forms[formId][formId + ":collegOrgan"].value = value;
               document.forms[formId][formId + ":collegOrganName"].value = name;
               //alert(value);
			   var node=document.all[formId + ':collegOrganSelect_cb'];
			   //alert(node);
			   if (node) node.fireEvent('onclick');
			       return true;
            }               
	</script>
<hx:panelDialog type="modal" id="collegOrganSelect"
	for="btnCollegOrganList" styleClass="panelDialog" saveState="true">
	<h:outputText value="Выберите Коллегиальный орган" />
	<div style="overflow: auto; height: 400px;">
	<t:tree2 id="client2Tree" value="#{departmentCrCoListBean.expandedTreeData}"
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
				<h:panelGroup>
					<t:graphicImage value="theme/images/person.png" border="0" alt="" />
					<h:outputText value="#{node.description}" styleClass="nodeFolder" />
					<h:outputText value=" (#{node.childCount})" styleClass="childCount"
						rendered="#{!empty node.children}" />
				</h:panelGroup>				
		</f:facet>
		<f:facet name="centralDepartment_IdCommitteeType_1">
				<h:outputLink value="#"
					onclick="return doSaveCollegOrgan('#{node.identifier}', '#{node.description}');">
					<t:graphicImage value="theme/images/person.png" border="0" alt="" />
					<h:outputText value="#{node.description}" styleClass="nodeFolder" />
					<h:outputText value=" (#{node.childCount})" styleClass="childCount"
						rendered="#{!empty node.children}" />
				</h:outputLink>		
		</f:facet>
		<f:facet name="centralDepartment_IdCommitteeType_2">
				<h:outputLink value="#"
					onclick="return doSaveCollegOrgan('#{node.identifier}', '#{node.description}');">
					<t:graphicImage value="theme/images/person.png" border="0" alt="" />
					<h:outputText value="#{node.description}" styleClass="nodeFolder" />
					<h:outputText value=" (#{node.childCount})" styleClass="childCount"
						rendered="#{!empty node.children}" />
				</h:outputLink>			
		</f:facet>
		<f:facet name="centralDepartment_IdCommitteeType_3">
				<h:outputLink value="#"
					onclick="return doSaveCollegOrgan('#{node.identifier}', '#{node.description}');">
					<t:graphicImage value="theme/images/person.png" border="0" alt="" />
					<h:outputText value="#{node.description}" styleClass="nodeFolder" />
					<h:outputText value=" (#{node.childCount})" styleClass="childCount"
						rendered="#{!empty node.children}" />
				</h:outputLink>			
		</f:facet>
		<f:facet name="centralDepartment_IdCommitteeType_4">
				<h:panelGroup>
					<t:graphicImage value="theme/images/person.png" border="0" alt="" />
					<h:outputText value="#{node.description}" styleClass="nodeFolder" />
					<h:outputText value=" (#{node.childCount})" styleClass="childCount"
						rendered="#{!empty node.children}" />
				</h:panelGroup>				
		</f:facet>
		<f:facet name="childDepartmentNode">
				<h:panelGroup>
					<t:graphicImage value="theme/images/document.png" border="0" alt="" />
					<h:outputText value="#{node.description}" />
				</h:panelGroup>
		</f:facet>
		<f:facet name="childDepartmentNode_IdCommitteeType_1">
				<h:outputLink value="#"
					onclick="return doSaveCollegOrgan('#{node.identifier}', '#{node.description}');">
					<t:graphicImage value="theme/images/document.png" border="0" alt="" />
					<h:outputText value="#{node.description}" />
				</h:outputLink>
		</f:facet>
		<f:facet name="childDepartmentNode_IdCommitteeType_2">
				<h:outputLink value="#"
					onclick="return doSaveCollegOrgan('#{node.identifier}', '#{node.description}');">
					<t:graphicImage value="theme/images/document.png" border="0" alt="" />
					<h:outputText value="#{node.description}" />
				</h:outputLink>
		</f:facet>
		<f:facet name="childDepartmentNode_IdCommitteeType_3">
				<h:outputLink value="#"
					onclick="return doSaveCollegOrgan('#{node.identifier}', '#{node.description}');">
					<t:graphicImage value="theme/images/document.png" border="0" alt="" />
					<h:outputText value="#{node.description}" />
				</h:outputLink>
		</f:facet>
		<f:facet name="childDepartmentNode_IdCommitteeType_4">
				<h:panelGroup>
					<t:graphicImage value="theme/images/document.png" border="0" alt="" />
					<h:outputText value="#{node.description}" />
				</h:panelGroup>
		</f:facet>
	</t:tree2>
	</div>
</hx:panelDialog>