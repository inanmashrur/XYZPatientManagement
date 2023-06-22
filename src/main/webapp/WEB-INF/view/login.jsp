<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="bootstrapCss" value="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css"/>
<c:url var="bootstrapJs" value="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.bundle.js"/>
<c:url var="myCss" value="${pageContext.request.contextPath}/resources/css/commonCss_v1.0.css"/>

<%--
  User: inanmashrur
  Date: 31/01/23
--%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${bootstrapCss}" type="text/css" rel="stylesheet">
        <link href="${myCss}" type="text/css" rel="stylesheet">
        <script src="${bootstrapJs}" type="text/javascript"></script>
        <title><fmt:message key="login.title"/></title>
    </head>

    <body>
        <header class="p-3 text-bg-light mb-3 shadow">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <a href="/home" class="nav col-12 col-lg-auto me-lg-auto justify-content-center mb-md-0 nav-link">
                    <h4><fmt:message key="title"/></h4>
                </a>
            </div>
        </header>

        <main class="container text-center">
            <div class="row mb-4">
                <h1><fmt:message key="login.form.header"/></h1>
            </div>

            <div class="row card border-0 shadow p-5 col-10 col-sm-8 col-md-8 col-lg-5 m-auto">
                <form:form action="/auth/login" modelAttribute="commandUser" method="post">
                    <h1 class="h6 mb-3 fw-normal"><fmt:message key="login.form.msg"/></h1>

                    <form:errors cssClass="error mb-2"/>

                    <div class="form-floating mb-1 shadow">
                        <form:input class="form-control" path="username" type="text" placeholder="username"/>
                        <form:label path="username"><fmt:message key="btn.login.username"/></form:label>
                    </div>
                    <form:errors path="username" cssClass="error"/>

                    <div class="form-floating mb-2 shadow">
                        <form:password class="form-control" path="password" placeholder="Password"/>
                        <form:label path="password"><fmt:message key="btn.login.password"/></form:label>
                    </div>
                    <form:errors path="password" cssClass="error mb-2"/>

                    <button class="btn btn-lg btn-primary w-100 mt-2" type="submit">
                        <fmt:message key="login.btn.login"/>
                    </button>
                </form:form>
            </div>
        </main>

    </body>
</html>