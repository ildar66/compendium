<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
      <script type="text/javascript">
 		function SortBy(field, listForm) 
		{
			var myForm = document.forms(document.forms.length-1);
			var e = myForm["compendium:section:content:questionListForm:orderBy"];
			//alert(e.value);
			e.value = (e.value == field)?field + ' desc':field;
		    document.body.style.cursor="wait";
			myForm.submit();
			return false;
		}         
      </script>
	<h:form id="questionListForm">
	    <h:inputHidden id="orderBy" value="#{questionListBean.orderBy}"/>
		<h1>Классификатор вопросов</h1>
		<div class="filter">
			<fieldset>
				<legend class="x">Фильтр классификатора</legend>
				<h:messages id="errorMessages" layout="table"/>
				Строка запроса: <h:inputText value="#{questionListBean.searchStr}" size="20"></h:inputText>						
				<h:commandButton value="Найти!" action="#{questionListBean.search}" styleClass="button search"/>
				<h:commandButton value="Очистить фильтр" action="#{questionListBean.clearFilter}" styleClass="button search"/>
			</fieldset>
		</div>
		<div class="filtered_result">
			<h:dataTable id="questionTable" rowClasses="a, b" styleClass="table" value="#{questionListBean.list}" var="question" rows="100">
				<h:column>
					<f:facet name="header">
							<h:commandLink action="#{questionTypeBean.initVO}" rendered="#{operatorBean.adminDictionary}">
								<h:graphicImage value="/theme/img/plus.png" alt="Создать"/>
						        <f:param name="chapter" value="questionType"/>
				         		<f:param name="action" value="ADD"/>									
							</h:commandLink>
					</f:facet>				
				    <h:commandLink action="#{questionTypeBean.initVO}" rendered="#{operatorBean.adminDictionary}">
				       <h:graphicImage url="/theme/img/edit18.png" alt="Редактировать"/>
				       <f:param name="chapter" value="questionType"/>
		         	   <f:param name="id" value="#{question.id}"/>
			           <f:param name="action" value="EDIT"/>
				    </h:commandLink>
				    <h:commandLink action="#{questionTypeBean.initVO}" rendered="#{!operatorBean.adminDictionary}">
				       <h:graphicImage url="/theme/img/edit18.png" alt="Просмотр"/>
				       <f:param name="chapter" value="questionType"/>
		         	   <f:param name="id" value="#{question.id}"/>
			           <f:param name="action" value="VIEW"/>
				    </h:commandLink>
				</h:column>
				<h:column id="idColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Код" onclick="SortBy('c.idQuestionType' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.idQuestionType' == questionListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.idQuestionType desc' == questionListBean.orderBy}"/>
						</f:verbatim>						
					</f:facet>
					<h:outputText value="#{question.id}" />
				</h:column>
				<h:column id="nameColumn">
					<f:facet name="header">
						<f:verbatim>
							<h:commandLink value="Наименование" onclick="SortBy('c.questionType' ,this.form)"/>
							<h:graphicImage value="/theme/img/sort-up.gif" rendered="#{'c.questionType' == questionListBean.orderBy}"/>
							<h:graphicImage value="/theme/img/sort-down.gif" rendered="#{'c.questionType desc' == questionListBean.orderBy}"/>
						</f:verbatim>
					</f:facet>
					<h:outputText value="#{question.name}"/>
				</h:column>
				<h:column>
				      <h:commandLink action="#{questionTypeBean.initVO}" rendered="#{operatorBean.adminDictionary}">
				         <h:graphicImage url="/theme/img/delete.gif" alt="Удалить"/>
				         <f:param name="chapter" value="questionType"/>
				         <f:param name="id" value="#{question.id}"/>
				         <f:param name="action" value="DELETE"/>
				      </h:commandLink>				
				</h:column>
			</h:dataTable>
			<div align="center">
				<h:outputText value="Записей не найдено" rendered="#{empty questionListBean.list}"/>
			</div>
		</div>
	</h:form>		
