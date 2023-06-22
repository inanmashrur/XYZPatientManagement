<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="homeUrl" value="${pageContext.request.contextPath}/home"/>
<c:url var="icons" value="${pageContext.request.contextPath}/resources/assets/icons"/>

<%--
  User: inanmashrur
  Date: 31/12/22
--%>
<header class="p-3 text-primary text-bg-light mb-3 shadow">

    <div class="d-flex flex-wrap align-items-center justify-content-end justify-content-lg-start">
        <a href="${homeUrl}" class="nav col-auto m-auto ms-1 justify-content-center mb-md-0 nav-link">
            <h4><fmt:message key="title"/></h4>
        </a>

        <c:if test="${sessionScope.token != null}">

            <div class="dropdown text-end me-2">
                <img class="d-block link-dark text-decoration-none dropdown-toggle" data-bs-toggle="dropdown"
                     aria-expanded="true" src="${icons}/man.png">

                <ul class="dropdown-menu text-small">
                    <li>
                        <c:url var="profileUrl" value="/user/profile"/>

                        <a class="nav-link" href="${profileUrl}">
                            <h6 class="text-center m-2"><c:out value="${sessionScope.token.user.name}"/></h6>
                        </a>
                    </li>

                    <hr class="m-1"/>

                    <li>
                        <a class="dropdown-item text-center" href="${pageContext.request.contextPath}/auth/logout">
                            <fmt:message key="header.btn.logout"/>
                        </a>
                    </li>
                </ul>
            </div>
        </c:if>
    </div>
</header>