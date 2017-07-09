<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core"  prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html"  prefix="h" %>
<%@ taglib uri="http://jakarta.apache.org/tiles" prefix="tiles" %>

<tiles:importAttribute scope="request"/>
<table>
	<tr>
		<td class="LeftSideBar">
			<div class="LeftMenu">
				<div class="t"></div>
				<div class="m">
					<f:subview id="menu">
						<tiles:insert attribute="menu" flush="false"/>
					</f:subview>
				</div>
				<div class="b"></div>
			</div>
		</td>
		<td class="MainContent">
			<table class="MainContent">
				<tr>
					<td class="lt"><div/></td>
					<td class="t"/>
					<td class="rt"><div/></td>
				</tr>
				<tr>
					<td class="l"/>
					<td class="c">
						<f:subview id="content">
							<tiles:insert attribute="content" flush="false"/>
						</f:subview>
					</td>
					<td class="r"/>
				</tr>
				<tr>
					<td class="lb"/>
					<td class="b"/>
					<td class="rb"/>
				</tr>
			</table>
		</td>
	</tr>
</table>