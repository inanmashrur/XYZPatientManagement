<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="bootstrapCss" value="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" />
<c:url var="bootstrapJs" value="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.bundle.js" />

<%--
  User: inanmashrur
  Date: 06/02/23
--%>
<html>
  <head>
    <link href="${bootstrapCss}" type="text/css" rel="stylesheet">
    <title><fmt:message key="page.unauthorized.title"/></title>
  </head>
  <body>

    <div class="container mt-5">
      <div class="card text-center shadow p-3">
        <h1 style="color: darkred"><fmt:message key="unauthorized.error.header"/></h1>

        <h2><fmt:message key="unauthorized.error.msg"/></h2>

        <a href="/home" class="mt-3">
          <button class="btn btn-secondary"><fmt:message key="btn.back.home"/></button>
        </a>
      </div>
    </div>

    <script src="${bootstrapJs}" type="text/javascript">
    </script>

  </body>
</html>
