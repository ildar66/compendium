<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%> 
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<hx:scriptCollector id="chapter4ScriptCollector">
<h:form id="departmentListForm">
	<h1>Департаменты ВТБ</h1>
		<h:panelGrid columns="1">
			<h:message for="serverTree" showSummary="false" styleClass="error"/>
			<h:message for="nodePath" styleClass="error"/>
			<h:panelGroup>
				<h:outputText value="Поиск совпадений по имени: "/>
				<h:inputText id="nodePath" value="#{departmentListBean.nodePath}" styleClass="text"/>
				<h:commandButton value="Найти" action="#{departmentListBean.searchByNodeName}" styleClass="button"/>
			</h:panelGroup>
		</h:panelGrid>
		<h:panelGrid columns="2">
			<h:panelGroup>
				<h:commandLink value="развернуть все" action="#{departmentListBean.expandAll}"/>
				<div style="height:600px; width:35em; overflow:auto;">
					<t:tree2 id="serverTree" value="#{departmentListBean.expandedTreeData}" var="node" varNodeToggler="t" clientSideToggle="false" binding="#{departmentListBean.tree}">
						<f:facet name="general-folder">
							<h:panelGroup>
								<t:graphicImage value="theme/images/yellow-folder-open.png" rendered="#{t.nodeExpanded}" alt="Свернуть"/>
								<t:graphicImage value="theme/images/yellow-folder-closed.png" rendered="#{!t.nodeExpanded}" alt="Развернуть"/>
								<h:outputText value="#{node.description}" styleClass="nodeFolder"/>
								<h:outputText value=" Всего центральных(#{node.childCount})" styleClass="childCount" rendered="#{!empty node.children}"/>
							</h:panelGroup>
						</f:facet>
						<f:facet name="centralDepartment">
							<h:panelGroup>
								<h:commandLink action="#{departmentListBean.setDocNum}" immediate="true" styleClass="#{t.nodeSelected ? 'documentSelected':'document'}" actionListener="#{t.setNodeSelected}">
									<t:graphicImage value="theme/images/person.png" rendered="#{t.nodeExpanded}" border="0" alt=""/>
									<t:graphicImage value="theme/images/person.png" rendered="#{!t.nodeExpanded}" border="0" alt=""/>
									<h:outputText value="#{node.description}" styleClass="nodeFolder"/>
									<h:outputText value=" (#{node.childCount})" styleClass="childCount" rendered="#{!empty node.children}"/>
									<f:param name="docNum" value="#{node.identifier}"/>
								</h:commandLink>
							</h:panelGroup>
						</f:facet>
						<f:facet name="type2-folder">
							<h:panelGroup>
								<t:graphicImage value="theme/images/blue-folder-open.gif" rendered="#{t.nodeExpanded}" border="0" alt=""/>
								<t:graphicImage value="theme/images/blue-folder-closed.png" rendered="#{!t.nodeExpanded}" border="0" alt=""/>
								<h:outputText value="#{node.description}" styleClass="nodeFolder"/>
								<h:outputText value=" (#{node.childCount})" styleClass="childCount" rendered="#{!empty node.children}"/>
							</h:panelGroup>
						</f:facet>
						<f:facet name="childDepartmentNode">
							<h:panelGroup>
								<h:commandLink action="#{departmentListBean.setDocNum}" immediate="true" styleClass="#{t.nodeSelected ? 'documentSelected':'document'}" actionListener="#{t.setNodeSelected}">
									<t:graphicImage value="theme/images/document.png" border="0" alt=""/>
									<h:outputText value="#{node.description}"/>
									<f:param name="docNum" value="#{node.identifier}"/> 
								</h:commandLink>
							</h:panelGroup>
						</f:facet>
					</t:tree2>
				</div>
			</h:panelGroup>
			<h:panelGroup rendered="true" styleClass="content" rendered="#{operatorBean.adminDictionary}">
				<h:messages id="errorMessages" layout="table"/>
	   			<f:verbatim>
					<h3>				
						<h:outputText rendered="#{departmentListBean.edit}">Редактирование департамента</h:outputText>
						<h:outputText rendered="#{departmentListBean.delete}">Удаление департамента</h:outputText>
						<h:outputText rendered="#{departmentListBean.add}">Создание департамента</h:outputText>
					</h3>   			
	   				<table class="Basic">
						<tr>
							<td class="right">Родительский департамент:</td>
							<td>
								<h:inputText styleClass="inputText" size="40" id="curDepParentName" value="#{departmentListBean.curDepParentName}"></h:inputText>
								<h:inputHidden id="curDepParent" value="#{departmentListBean.curDepParent}"/>							
								<jsp:include page="/sections/popup/departmentListDialog.jsp" flush="false"/>
								<h:commandButton type="button" title="" value="Выбрать" styleClass="button" id="btnSelectDepartmentList"/>
							</td>
						</tr>			
						<tr>
							<td class="right">Код:</td>
							<td><h:inputText styleClass="inputText" size="60" id="idDepartment" required="true" value="#{departmentListBean.curDocument.id}"></h:inputText></td>
						</tr>
						<tr>
							<td class="right">Полное Название:</td>
							<td><h:inputText styleClass="inputText" size="60" id="fullName" required="true" value="#{departmentListBean.curDocument.fullName}"></h:inputText></td>
						</tr>
						<tr>
							<td class="right">Краткое Название:</td>
							<td><h:inputText styleClass="inputText" size="60" id="shortName" value="#{departmentListBean.curDocument.shortName}"></h:inputText></td>
						</tr>
						<tr>
							<td class="right">Тип кредитного комитета:</td>
							<td>
								<h:selectOneMenu value="#{departmentListBean.curDocument.crCoType}" id="idCommitteeType">
									<f:selectItem itemLabel="" itemValue="" />
									<f:selectItem itemValue="1" itemLabel="Кредитный комитет" />
									<f:selectItem itemValue="2" itemLabel="Малый кредитный комитет" />
									<f:selectItem itemValue="3" itemLabel="Кредитный комитет по Московскому региону" />
									<f:selectItem itemValue="4" itemLabel="Кредитный комитет филиала" />
								</h:selectOneMenu>							
							</td>
						</tr>
						<tr>
							<td class="right">CB_CD:</td>
							<td><h:inputText styleClass="inputText" size="60" id="cbCd" value="#{departmentListBean.curDocument.code}"></h:inputText></td>
						</tr>
						<tr>
							<td></td>
							<td>
							</td>
						</tr>
					</table>		
				</f:verbatim>
				<div class="commands">
					<h:commandButton rendered="#{departmentListBean.add}" value="Создать" action="#{departmentListBean.createAction}" styleClass="commandExButton button"/>
					<h:commandButton rendered="#{departmentListBean.edit}" value="Сохранить" action="#{departmentListBean.saveAction}" styleClass="commandExButton button"/>
					<h:commandButton rendered="#{departmentListBean.edit}" value="Удалить" action="#{departmentListBean.deleteAction}" styleClass="commandExButton button"/>
					<h:commandButton rendered="#{departmentListBean.edit}" value="Отменить" immediate="true" action="#{departmentListBean.canselAction}" styleClass="commandExButton button"/>
				</div>
			</h:panelGroup>
		</h:panelGrid>
	</h:form>		
</hx:scriptCollector>