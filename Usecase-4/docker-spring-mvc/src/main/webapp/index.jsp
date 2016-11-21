<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Docker image - Welcome page</title>
</head>
<body>
	<c:set var="req" value="${pageContext.request}" />
	<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
    <c:redirect url="${baseURL}/docker"/>
</body>
</html>