<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="bootstrapCss" value="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" />
<c:url var="bootstrapJs" value="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.bundle.js" />

<%--
  User: inanmashrur
  Date: 04/04/23
--%>
<html>
    <head>
        <link href="${bootstrapCss}" type="text/css" rel="stylesheet">
        <title><fmt:message key="home.title"/></title>
    </head>
    <body>

        <jsp:include page="header.jsp"/>

        <main class="container text-center m-auto">

            <c:if test="${sessionScope.token.admins}">
                <div class="card text-center shadow m-auto p-2">
                    <div class="card-header">
                        <h5><fmt:message key="card.header.admin"/></h5>
                    </div>

                    <div class="card-body row m-auto">
                        <div class="col">
                            <a href="${pageContext.request.contextPath}/user/list">
                                <button class="btn btn-lg btn-primary"><fmt:message key="btn.users"/></button>
                            </a>
                        </div>

                        <div class="col">
                            <a href="${pageContext.request.contextPath}/doctor/list">
                                <button class="btn btn-lg btn-primary">Doctors</button>
                            </a>
                        </div>

                        <div class="col">
                            <a href="${pageContext.request.contextPath}/assistant/list">
                                <button class="btn btn-lg btn-primary">Assistants</button>
                            </a>
                        </div>

                        <div class="col">
                            <a href="${pageContext.request.contextPath}/patient/list">
                                <button class="btn btn-lg btn-primary">Patients</button>
                            </a>
                        </div>
                    </div>

                    <div class="card-body row m-auto">
                        <div class="col">
                            <a href="${pageContext.request.contextPath}/doctor/basicInfo">
                                <button class="btn btn-lg btn-primary">Add New Doctor</button>
                            </a>
                        </div>

<%--                        <div class="col">--%>
<%--                            <a href="${pageContext.request.contextPath}/assistant">--%>
<%--                                <button class="btn btn-lg btn-primary">Add New Assistant</button>--%>
<%--                            </a>--%>
<%--                        </div>--%>
                    </div>
                </div>
            </c:if>

            <c:if test="${sessionScope.token.doctors}">
                <div class="card text-center shadow m-auto mt-4 p-2">
                    <div class="card-header">
                        <h5><fmt:message key="card.header.doctor"/></h5>
                    </div>

                    <div class="card-body row m-auto">
                        <div class="col">
                            <a href="${pageContext.request.contextPath}/assistant/list">
                                <button class="btn btn-lg btn-primary">Assistants</button>
                            </a>
                        </div>

                        <div class="col">
                            <a href="${pageContext.request.contextPath}/patient/list">
                                <button class="btn btn-lg btn-primary">Patients</button>
                            </a>
                        </div>
                    </div>

                    <div class="card-body row m-auto">
                        <div class="col">
                            <a href="${pageContext.request.contextPath}/assistant/form">
                                <button class="btn btn-lg btn-primary">Add New Assistant</button>
                            </a>
                        </div>

                        <div class="col">
                            <a href="${pageContext.request.contextPath}/patient/form">
                                <button class="btn btn-lg btn-primary">Add New Patient</button>
                            </a>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${sessionScope.token.assistants}">
                <div class="card text-center shadow m-auto mt-4 p-2">
                    <div class="card-header">
                        <h5><fmt:message key="card.header.assistant"/></h5>
                    </div>

                    <div class="card-body text-center">
                        <div class="row text-center">
                            <div class="col text-end">
                                <a href="${pageContext.request.contextPath}/patient/list">
                                    <button class="btn btn-sm btn-primary">Patients</button>
                                </a>
                            </div>

                            <div class="col text-start">
                                <a href="${pageContext.request.contextPath}/patient/form">
                                    <button class="btn btn-sm btn-primary">Add New Patient</button>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </main>

        <script src="${bootstrapJs}" type="text/javascript">
        </script>

    </body>
</html>
