<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/client/components/bootstrap/dist/css/bootstrap.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet"/>

<spring:url value="/client/components/bootstrap/dist/css/bootstrap-theme.css" var="bootstrapThemeCss" />
<link href="${bootstrapThemeCss}" rel="stylesheet"/>

<spring:url value="/client/components/components-font-awesome/css/font-awesome.css" var="fontAwesomeCss" />
<link href="${fontAwesomeCss}" rel="stylesheet"/>

<spring:url value="/client/application/css/main.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet"/>

<spring:url value="/client/components/ui-select/dist/select.css" var="uiSelectCss" />
<link href="${uiSelectCss}" rel="stylesheet"/>
