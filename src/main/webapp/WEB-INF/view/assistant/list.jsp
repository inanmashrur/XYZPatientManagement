<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:url var="bootstrapCss" value="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css"/>
<c:url var="bootstrapJs" value="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.bundle.js"/>
<c:url var="jQueryJs" value="${pageContext.request.contextPath}/resources/jQuery/jquery.min_v1.11.2.js"/>

<%--
  User: inanmashrur
  Date: 09/05/23
--%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="${bootstrapCss}" type="text/css" rel="stylesheet">
        <script src="${jQueryJs}" type="text/javascript"></script>
        <title><fmt:message key="assistant.list.title"/></title>
    </head>

    <body>

        <jsp:include page="../header.jsp"/>

        <main class="container text-center">
            <div class="row mb-2 mt-4">
                <h3><fmt:message key="assistant.list.header"/></h3>
            </div>

            <div class="row col-10 m-auto p-0 mb-1">
                <div class="col text-start">
                    <a href="${pageContext.request.contextPath}/home">
                        <button class="btn btn-secondary m-auto"><fmt:message key="btn.back"/></button>
                    </a>
                </div>
            </div>

            <div class="row col-10 card border-0 shadow p-1 m-auto">
                <table class="table align-middle m-auto">
                    <thead class="table-light text-start">
                        <tr class="table-primary">
                            <th><fmt:message key="table.header.name"/></th>
                            <th><fmt:message key="assistant.table.header.username"/></th>
                            <th><fmt:message key="assistant.table.header.doc-name"/></th>
                            <th><fmt:message key="assistant.table.header.joined"/></th>
                            <th><fmt:message key="doctor.table.header.status"/></th>
                        </tr>
                    </thead>

                    <tbody class="text-start">
                        <c:forEach items='${assistants}' var='assistant'>
                            <tr>
                                <td><c:out value="${assistant.user.name}"/></td>
                                <td><c:out value="${assistant.user.username}"/></td>
                                <td><c:out value="${assistant.doctor.user.name}"/></td>
                                <td><c:out value="${assistant.createdAt}"/></td>
                                <td><c:out value="${assistant.status.value}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>

        <script src="${bootstrapJs}" type="text/javascript"></script>
    </body>
</html>