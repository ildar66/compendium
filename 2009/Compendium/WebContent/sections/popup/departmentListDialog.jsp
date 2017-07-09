<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="" location="" --%><%-- /jsf:pagecode --%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
	<script type="text/javascript" language="JavaScript1.2">
            function doSave(value, name) {
               //var formId = window.openerFormId;
               //opener.document.forms[formId][formId + ":state"].value = value;
               //window.close();
               var btn=event.srcElement;
               //var myForm=getParent(btn,'FORM');
               var myForm=document.forms(document.forms.length-1);
               //var formId = "compendium:section:content:departmentListForm";
               var formId = myForm.id;
               document.forms[formId][formId + ":curDepParent"].value = value;
               document.forms[formId][formId + ":curDepParentName"].value = name;
               //alert(value);
			   var node=document.all[formId + ':departmentSelect_cb'];
			   //alert(node);
			   if (node) node.fireEvent('onclick');
			       return true;
            }               
	</script>
<hx:panelDialog type="modal" id="departmentSelect"
	for="btnSelectDepartmentList" styleClass="panelDialog" saveState="true">
	<h:outputText value="Выберите родительский департамент" />
	<div style="overflow: auto; height: 400px;">
	<t:tree2 id="clientTree" value="#{departmentListBean.expandedTreeData}" var="node" varNodeToggler="t">
		<f:facet name="general-folder">
			<h:panelGroup>
				<h:outputLink value="#"	onclick="return doSave('#{node.identifier}', '#{node.description}');">			
					<t:graphicImage value="theme/images/yellow-folder-open.png"
						rendered="#{t.nodeExpanded}" border="0" alt="" />
					<t:graphicImage value="theme/images/yellow-folder-closed.png"
						rendered="#{!t.nodeExpanded}" border="0" alt="" />
					<h:outputText value="#{node.description}" styleClass="nodeFolder" />
					<h:outputText value=" Всего центральных(#{node.childCount})"
						styleClass="childCount" rendered="#{!empty node.children}" />
				</h:outputLink>	
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
		<f:facet name="childDepartmentNode">
				<h:outputLink value="#"
					onclick="return doSave('#{node.identifier}', '#{node.description}');">
					<t:graphicImage value="theme/images/document.png" border="0" alt="" />
					<h:outputText value="#{node.description}" />
				</h:outputLink>
		</f:facet>
	</t:tree2>
	</div>
</hx:panelDialog>