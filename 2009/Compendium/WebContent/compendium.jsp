<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/tiles" prefix="tiles"%>
<html>
<f:view>
	<f:loadBundle basename="com.corejsf.messages" var="msgs" />
	<head>
		<link href="theme/stylesheet.css" rel="stylesheet" type="text/css" />
		<link href="theme/tree.css" rel="stylesheet" type="text/css" />
		<title><h:outputText value="#{msgs.compendiumWindowTitle}" /></title>
	</head>
	<body>
		<table class="HeaderTable" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td><img src="theme/img/logo-vtb.gif" width="125" style="margin: 10px;" /></td>
				<td class="Gradient"><img src="theme/img/gradient.jpg"></td>
				<td class="TitleCaption"><h:outputText value="Администрирование" styleClass="sectionTitle" /></td>
			</tr>
		</table>
		<f:subview id="compendium">
			<tiles:insert definition="compendium" flush="false" />
		</f:subview>
		<table id="FooterBand">
			<tr>
				<td>ВТБ</td>
				<td class="ReverseGradient"><img src="theme/img/gradient2.jpg"></td>
			</tr>
		</table>
		<div id="Copyright">Разработка ООО «Мастер Домино», 2009 год</div>
	</body>
</f:view>
</html>