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
  Date: 09/05/23
--%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="${bootstrapCss}" type="text/css" rel="stylesheet">
        <script src="${jQueryJs}" type="text/javascript"></script>
        <link href="${myCss}" type="text/css" rel="stylesheet">
        <title><fmt:message key="patient.form.title"/></title>
    </head>

    <body id="bodyId">

        <jsp:include page="../header.jsp"/>

        <main class="container text-center">
            <div class="row mb-4">
                <h3 id="form-title"><fmt:message key="patient.form.header"/></h3>
            </div>

            <section class="row card border-0 shadow p-4 col-12 col-sm-8 col-md-8 col-lg-10 m-auto">
                <div class="row text-start">
                    <h5><fmt:message key="patient.form.header"/></h5>
                </div>
                <form:form modelAttribute="commandPatient" action="/patient/form" method="post">
                    <div class="row">
                        <div class="col">
                            <div class="form-floating mt-2 mb-1 shadow">
                                <form:input path="firstName" class="form-control" type="text" placeholder="fname"/>
                                <form:label path="firstName"><fmt:message key="patient.input.first-name"/></form:label>
                            </div>
                            <form:errors path="firstName" cssClass="error"/>
                        </div>

                        <div class="col">
                            <div class="form-floating mt-2 mb-1 shadow">
                                <form:input path="lastName" class="form-control" type="text" placeholder="lname"/>
                                <form:label path="lastName"><fmt:message key="patient.input.last-name"/></form:label>
                            </div>
                            <form:errors path="lastName" cssClass="error"/>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col">
                            <div class="form-floating mt-2 mb-1 shadow">
                                <form:input path="dateOfBirth" cssClass="form-control" type="date" placeholder="dob"/>
                                <form:label path="dateOfBirth"><fmt:message key="patient.input.date-of-birth"/></form:label>
                            </div>
                            <form:errors path="dateOfBirth" cssClass="error"/>
                        </div>

                        <div class="col">
                            <div class="form-floating mt-2 mb-1 shadow">
                                <form:select path="bloodGroup" cssClass="form-select">
                                    <form:options items="${bloodGroups}" itemLabel="value" />
                                </form:select>
                                <form:label path="bloodGroup"><fmt:message key="patient.input.blood-group"/></form:label>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-6">
                            <div class="form-floating mt-2 mb-1 shadow">
                                <form:input path="mobileNumber" cssClass="form-control" type="text" placeholder="mobile"/>
                                <form:label path="mobileNumber"><fmt:message key="patient.input.mobile"/></form:label>
                            </div>
                            <form:errors path="mobileNumber" cssClass="error"/>
                        </div>
                    </div>


                    <div class="row">
                        <div class="col text-start">
                            <a href="${pageContext.request.contextPath}/home">
                                <button class="btn btn-lg btn-secondary w-100 mt-2" type="button">
                                    <fmt:message key="btn.cancel"/>
                                </button>
                            </a>
                        </div>

                        <div class="col text-end">
                            <button id="btn-save"
                                    name="_action_save_or_update"
                                    class="btn btn-lg btn-success w-100 mt-2"
                                    type="submit">
                                <c:choose>
                                    <c:when test="${commandPatient.isNew()}"><fmt:message key="btn.save"/></c:when>
                                    <c:otherwise><fmt:message key="btn.update"/></c:otherwise>
                                </c:choose>
                            </button>
                        </div>
                    </div>
                </form:form>
            </section>

            <c:if test="${not commandPatient.isNew()}">
                <section class="row card border-0 shadow p-4 col-12 col-sm-8 col-md-8 col-lg-10 m-auto mt-4">
                    <div class="row text-start">
                        <h5><fmt:message key="prescription.list"/></h5>
                    </div>

                    <div class="col-2 text-start mb-2">
                        <a href="${pageContext.request.contextPath}${addNewPrescriptionLink}">
                            <button id="addBtn" class="btn btn-sm btn-secondary" type="button">
                                <fmt:message key="btn.add"/>
                            </button>
                        </a>
                    </div>

                    <div class="table row col-12 text-center ps-4">
                        <c:choose>
                            <c:when test="${empty prescriptionList}">
                                <p class="alert-warning"> <c:out value="No prescription available"/></p>
                            </c:when>

                            <c:otherwise>
                                <table id="prescriptinTable" class="text-center table-primary">

                                    <thead>
                                    <tr>
                                        <th>Date</th>
                                        <th>Doctor</th>
                                        <th>Action</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach items="${prescriptionList}" var="prescription">
                                        <tr>
                                            <td><fmt:formatDate value="${prescription.createdAt}" type="both"/></td>
                                            <td>${prescription.doctor.user.name}</td>
                                            <td>
                                                <c:url var="prescriptionURL" value="/prescription/form">
                                                    <c:param name="prescriptionId" value="${prescription.id}"/>
                                                </c:url>

                                                <a href="${prescriptionURL}">
                                                    <button class="btn btn-sm btn-danger">
                                                        <fmt:message key="btn.view"/>
                                                    </button>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </section>
            </c:if>
        </main>

        <script src="${bootstrapJs}" type="text/javascript">
        </script>

    </body>
</html>
