<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="bootstrapCss" value="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" />
<c:url var="myCss" value="${pageContext.request.contextPath}/resources/css/commonCss_v1.0.css" />
<c:url var="bootstrapJs" value="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.bundle.js" />
<c:url var="homeUrl" value="${pageContext.request.contextPath}/home"/>

<%--
  User: inanmashrur
  Date: 21/12/23
--%>
<html>
    <head>
        <link href="${bootstrapCss}" type="text/css" rel="stylesheet">
        <link href="${myCss}" type="text/css" rel="stylesheet">
        <title><fmt:message key="page.message.title"/></title>
    </head>
    <body>

        <jsp:include page="header.jsp"/>

        <div class="container mt-5">
            <div class="card text-center shadow p-3">

                <h1 class="${type}"><c:out value="${msg}"/></h1>

                <a href="${homeUrl}" class="mt-3">
                    <button class="btn btn-secondary"><fmt:message key="btn.back.home"/></button>
                </a>
            </div>
        </div>

        <script src="${bootstrapJs}" type="text/javascript"></script>
    </body>
</html>