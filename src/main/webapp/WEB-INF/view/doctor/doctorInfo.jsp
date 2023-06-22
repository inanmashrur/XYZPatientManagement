<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="bootstrapCss" value="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css"/>
<c:url var="bootstrapJs" value="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.bundle.js"/>
<c:url var="myCss" value="${pageContext.request.contextPath}/resources/css/commonCss_v1.0.css"/>
<c:url var="jQueryJs" value="${pageContext.request.contextPath}/resources/jQuery/jquery.min_v1.11.2.js"/>

<%--
  User: inanmashrur
  Date: 31/01/23
--%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="${bootstrapCss}" type="text/css" rel="stylesheet">
        <script src="${jQueryJs}" type="text/javascript"></script>
        <link href="${myCss}" type="text/css" rel="stylesheet">
        <title><fmt:message key="doctor.form.title"/></title>
    </head>

    <body id="body-id">

        <jsp:include page="../header.jsp"/>

        <main class="container text-center">
            <div class="row mb-4">
                <h3 id="form-title"><fmt:message key="doctor.form.header"/></h3>
            </div>

            <div class="row card border-0 shadow p-5 col-10 col-sm-8 col-md-8 col-lg-5 m-auto">
                <form:form modelAttribute="commandDoctor" action="/doctor/doctorInfo" method="post">

                    <div class="form-floating mt-2 mb-1 shadow">
                        <form:input path="education" class="form-control" type="textarea" placeholder="ed"/>
                        <form:label path="education"><fmt:message key="doctor.textarea.education-info"/></form:label>
                    </div>
                    <form:errors path="education" cssClass="error"/>

                    <div class="form-floating mt-2 mb-1 shadow">
                        <form:input path="specialities" class="form-control" type="textarea" placeholder="sp"/>
                        <form:label path="specialities"><fmt:message key="doctor.textarea.specialities"/></form:label>
                    </div>
                    <form:errors path="specialities" cssClass="error"/>

                    <button id="btn-save"
                            name="_action_save_or_update"
                            class="btn btn-lg btn-success w-100 mt-2"
                            type="submit">
                        <c:out value="Preview"/>
                    </button>

                    <a href="${pageContext.request.contextPath}/home">
                        <button class="btn btn-lg btn-secondary w-100 mt-2" type="button">
                            <fmt:message key="btn.cancel"/>
                        </button>
                    </a>
                </form:form>
            </div>
        </main>

        <script src="${bootstrapJs}" type="text/javascript">
        </script>

    </body>
</html>
