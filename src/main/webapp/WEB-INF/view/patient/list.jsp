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
        <title><fmt:message key="patient.list.title"/></title>
    </head>

    <body>

        <jsp:include page="../header.jsp"/>

        <main class="container text-center">
            <div class="row mb-2 mt-4">
                <h3><fmt:message key="patient.list.header"/></h3>
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
                            <th><fmt:message key="patient.table.header.id"/></th>
                            <th><fmt:message key="table.header.name"/></th>
                            <th><fmt:message key="patient.table.header.date-of-birth"/></th>
                            <th><fmt:message key="patient.table.header.blood-group"/></th>
                            <th><fmt:message key="patient.table.header.mobile"/></th>
                            <th><fmt:message key="patient.table.header.status"/></th>
                            <th><fmt:message key="table.header.action"/></th>
                        </tr>
                    </thead>

                    <tbody class="text-start">
                        <c:forEach items='${patients}' var='patient'>
                            <tr>
                                <td><c:out value="${patient.patientId}"/></td>
                                <td><c:out value="${patient.name}"/></td>
                                <td><c:out value="${patient.dateOfBirth}"/></td>
                                <td><c:out value="${patient.bloodGroup.value}"/></td>
                                <td><c:out value="${patient.mobileNumber}"/></td>
                                <td><c:out value="${patient.status.value}"/></td>
                                <td>
                                    <c:url var="viewURL" value="${pageContext.request.contextPath}/patient/form">
                                        <c:param name="patientId" value="${patient.id}"/>
                                    </c:url>

                                    <a href="${viewURL}">
                                        <button type="button" class="btn btn-sm btn-secondary">View</button>
                                    </a>
                                </td>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>

        <script src="${bootstrapJs}" type="text/javascript"></script>
    </body>
</html>