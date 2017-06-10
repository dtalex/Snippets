<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />

<title>My Company - Struts2 jQuery Grid</title>

<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
<!--[if lt IE 9]>
	<script src="js/html5.js"></script>
	<![endif]-->

<!--  Struts2 Taglib Resources -->
<sj:head loadAtOnce="true" jquerytheme="cupertino" />
<sb:head />
<link href="${pageContext.request.contextPath}/styles/content.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<s:i18n name="Utenti">
		<s:form action="/prova.action" method="POST">
			<s:actionerror />
			<s:textfield key="currentUser.nome" name="currentUser.nome"
				label="il tuo nome" />
			<s:textfield key="currentUser.cognome" name="currentUser.cognome"
				label="il tuo cognome" />
			<s:textfield key="currentUser.email" name="currentUser.email"
				label="la tua email" />
			<s:submit value="salva" />
		</s:form>


		<s:form method="list" action="select">
			<s:submit value="list" />
		</s:form>

		<s:form action="/readMetadata.action" method="POST">
			<s:actionerror />
			<s:textfield key="remapField.remapId" name="remapField.remapId"
				label="remapId" />
			<s:textfield key="remapField.valueToRemap"
				name="remapField.valueToRemap" label="valueToRemap" />

			<s:submit value="vedi" />
		</s:form>

		<s:form method="listStatistics" action="listStatistics">
			<s:submit value="ispeziona cache" />
		</s:form>

	</s:i18n>
</body>
</html>
