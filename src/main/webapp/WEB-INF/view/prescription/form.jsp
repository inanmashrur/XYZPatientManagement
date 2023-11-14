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
  Date: 27/05/23
--%>
<!DOCTYPE>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${bootstrapCss}" type="text/css" rel="stylesheet">
    <script src="${jQueryJs}" type="text/javascript"></script>
    <link href="${myCss}" type="text/css" rel="stylesheet">
    <title><fmt:message key="prescription.form.title"/></title>

    <script>
        function delRow($btn) {
            $btn.closest('tr').remove();
        }

        function toggleAddMedicineDiv() {
            $("#medInputDiv").toggleClass('hidden');
            $("#addMedBtn").toggleClass('hidden');
        }

        function toggleAddTestDiv() {
            $("#testInputDiv").toggleClass('hidden');
            $("#addTestBtn").toggleClass('hidden');
        }

        $medTrTemplate = '<tr>'
                    + '    <td>#form</td>'
                    + '    <td>#name</td>'
                    + '    <td>#dosage</td>'
                    + '    <td>#onFullStomach</td>'
                    + '    <td>#freq</td>'
                    + '    <td>'
                    + '         <input name="medicineList" value="#value" hidden />'
                    + '         <button type="button" class="btn btn-sm btn-danger" onclick="delRow(this)">'
                    + '             Remove'
                    + '         </button>'
                    + '    <td>'
                    + '</tr>';

        $testTrTemplate = '<tr>'
            + '    <td>#testName</td>'
            + '    <td>#additionalInfo</td>'
            + '    <td>'
            + '         <input name="testList" value="#value" hidden />'
            + '         <button type="button" class="btn btn-sm btn-danger" onclick="delRow(this)">'
            + '             Remove'
            + '         </button>'
            + '    <td>'
            + '</tr>';

        function addMedicine() {
            const form = $("#form").val();
            const name = $("#name").val();
            const dosage = $("#dosage").val();
            const onFullStomach = $("#onFullStomach").val();
            const freq = $("#freq").val();
            const value = form+'-%-'+name+'-%-'+dosage+'-%-'+onFullStomach+'-%-'+freq;

            if (name === "" || dosage === "" || freq === "") {
                alert("All fields are required for adding a medicine!");
                return;
            }

            toggleAddMedicineDiv();

            $tr = $medTrTemplate.replace("#form", form)
                .replace("#name", name)
                .replace("#dosage", dosage)
                .replace("#onFullStomach", onFullStomach)
                .replace("#freq", freq)
                .replace("#value", value)

            $('#medicineTable > tbody:last-child').append($tr);
        }

        function addTest() {
            const testName = $("#testName").val();
            const additionalInfo = $("#additionalInfo").val();
            const value = testName+'-%-'+additionalInfo;

            if (testName === "") {
                alert("Test name is required for adding a test!");
                return;
            }

            toggleAddTestDiv();

            $tr = $testTrTemplate.replace("#testName", testName)
                .replace("#additionalInfo", additionalInfo)
                .replace("#value", value)

            $('#testTable > tbody:last-child').append($tr);
        }
    </script>
</head>

<body id="bodyId">

<jsp:include page="../header.jsp"/>

<main class="container text-center">

    <section class="row card border-0 p-4 col-12 col-sm-8 col-md-12 col-lg-9 m-auto mb-1 pb-2 bg-primary bg-opacity-10">
        <div class="row text-center">
            <h5><fmt:message key="prescription.form.header"/></h5>
        </div>

        <div class="row">
            <div class="col-6 text-start">
                <label><p class="h6">Diagnosed By: </p></label> <c:out value="${commandPrescription.doctor.user.name}"/>
            </div>

            <div class="col-6 text-end">
                <label><p class="h6">Date: </p></label> dd/MM/yyyy
            </div>
        </div>
    </section>

    <section class="row card border-0 p-4 col-12 col-sm-8 col-md-12 col-lg-9 m-auto mb-1 pt-4 pb-2 bg-secondary bg-opacity-10">
        <div class="row text-start mb-1 ps-2">
            <h5><fmt:message key="patient.form.header"/></h5>
        </div><hr/>

        <div class="row">
            <div class="col-6 text-start ">
                <label><p class="h6">Patient Name: </p></label> <c:out value="${commandPrescription.patient.name}"/>
            </div>

            <div class="col-6 text-start">
                <label><p class="h6">Date of Birth: </p></label> <fmt:formatDate
                    value="${commandPrescription.patient.dateOfBirth}"/>
            </div>
        </div>

        <div class="row">
            <div class="col-6 text-start">
                <label><p class="h6">Contact No: </p></label> <c:out
                    value="${commandPrescription.patient.mobileNumber}"/>
            </div>

            <div class="col-6 text-start">
                <label><p class="h6">Blood Group: </p></label> <c:out
                    value="${commandPrescription.patient.bloodGroup.value}"/>
            </div>
        </div>
    </section>

    <form:form modelAttribute="commandPrescription" action="/prescription/form" method="post"
               enctype="multipart/form-data">

        <section class="row card border-0 shadow p-4 col-12 col-sm-8 col-md-12 col-lg-9 m-auto">
            <div class="row text-start ps-1 pb-1">
                <h5>Patient's Health Metric</h5>
            </div><hr/>

            <div class="col-12">
                <table class="table text-center">
                    <thead class="table-primary">
                        <tr class="text-center">
                            <th>Height</th>
                            <th>Weight</th>
                            <th>Temp (F)</th>
                            <th>BP (high)</th>
                            <th>BP (low)</th>
                            <th>Heart Rate</th>
                            <th>Sugar level</th>
                        </tr>
                    </thead>

                    <tbody class="text-center">
                        <tr>
                            <td>
                                <form:input path="healthMetric.height" class="form-control" type="number"/>
                            </td>
                            <form:errors path="healthMetric.height" cssClass="error"/>

                            <td>
                                <form:input path="healthMetric.weight" class="form-control" type="number"/>
                            </td>
                            <form:errors path="healthMetric.weight" cssClass="error"/>

                            <td>
                                <form:input path="healthMetric.temperature" class="form-control" type="number"/>
                            </td>
                            <form:errors path="healthMetric.temperature" cssClass="error"/>

                            <td>
                                <form:input path="healthMetric.bpHigh" class="form-control" type="number"/>
                            </td>
                            <form:errors path="healthMetric.bpHigh" cssClass="error"/>

                            <td>
                                <form:input path="healthMetric.bpLow" class="form-control" type="number"/>
                            </td>
                            <form:errors path="healthMetric.bpLow" cssClass="error"/>

                            <td>
                                <form:input path="healthMetric.heartRate" class="form-control" type="number"/>
                            </td>
                            <form:errors path="healthMetric.heartRate" cssClass="error"/>

                            <td>
                                <form:input path="healthMetric.sugarLevel" class="form-control" type="number"/>
                            </td>
                            <form:errors path="healthMetric.sugarLevel" cssClass="error"/>
                        </tr>
                    </tbody>
                </table>
            </div>
        </section>

        <section class="row card border-0 shadow p-4 col-12 col-sm-8 col-md-12 col-lg-9 m-auto mt-1">
            <div class="row text-start ps-0">
                <h5>Medicines</h5>
            </div><hr/>

            <div class="col-2 text-start mb-2">
                <button id="addMedBtn" class="btn btn-sm btn-secondary" type="button" onclick="toggleAddMedicineDiv()">
                    <fmt:message key="btn.add"/>
                </button>
            </div>

            <div id="medInputDiv" class="m-2 hidden">
                <div class="row m-auto mb-1">
                    <div class="col-3 offset-1 form-floating shadow p-0 me-2">
                        <select id="form" class="form-select">
                            <c:forEach items="${medicineFormList}" var="form">
                                <option value="${form}">${form.value}</option>
                            </c:forEach>
                        </select>
                        <label for="form">Medicine Form</label>
                    </div>

                    <div class="col-3 form-floating shadow p-0 me-2">
                        <input id="name" class="form-control" type="text" placeholder="form"/>
                        <label for="name">Medicine Name</label>
                    </div>

                    <div class="col-3 form-floating shadow p-0">
                        <input id="dosage" class="form-control" type="text" placeholder="form"/>
                        <label for="dosage">Medicine Dosage</label>
                    </div>
                </div>

                <div class="row m-auto mb-3">
                    <div class="col-3 offset-1 form-floating p-0 shadow me-2">
                        <select id="onFullStomach" class="form-select">
                            <c:forEach items="${yesNoMap}" var="itm">
                                <option value="${itm}">${itm.label}</option>
                            </c:forEach>
                        </select>
                        <label for="onFullStomach">On Full Stomach?</label>
                    </div>

                    <div class="col-3 form-floating shadow p-0 me-2">
                        <input id="freq" class="form-control" type="text" placeholder="form"/>
                        <label for="freq">Frequency</label>
                    </div>

                    <div class="col-3 text-center mt-auto mb-auto">
                        <button class="btn btn-md btn-secondary w-auto" type="button" onclick="addMedicine()">
                            Add
                        </button>
                        <button class="btn btn-md btn-danger w-auto" type="button" onclick="toggleAddMedicineDiv()">
                            Cancel
                        </button>
                    </div>
                </div>
            </div>

            <div class="col-12 text-start">
                <table id="medicineTable" class="table text-center w-100">
                    <thead class="table-primary">
                        <tr>
                            <th>Form</th>
                            <th>Name</th>
                            <th>Dosage</th>
                            <th>Full Stomach</th>
                            <th>Freq</th>
                            <th>Action</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${commandPrescription.medicineList}" var="medicine">
                            <tr>
                                <td>${medicine.form.value}</td>
                                <td>${medicine.name}</td>
                                <td>${medicine.dosage}</td>
                                <td>${medicine.onFullStomach.label}</td>
                                <td>${medicine.frequency}</td>
                                <td>
                                    <input name="medicineList"
                                           value="${medicine.form}-%-${medicine.name}-%-${medicine.dosage}-%-${medicine.onFullStomach}-%-${medicine.frequency}"
                                           hidden/>
                                    <button name="del" type="button" class="btn btn-sm btn-danger" onclick="delRow(this)">
                                        <fmt:message key="btn.remove"/>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>

        <section class="row card border-0 shadow p-4 col-12 col-sm-8 col-md-12 col-lg-9 m-auto mt-1">
            <div class="row text-start ps-0">
                <h5>Tests</h5>
            </div><hr/>

            <div class="col-2 text-start mb-2">
                <button id="addTestBtn" class="btn btn-sm btn-secondary" type="button" onclick="toggleAddTestDiv()">
                    <fmt:message key="btn.add"/>
                </button>
            </div>

            <div id="testInputDiv" class="m-2 hidden">
                <div class="row m-auto mb-1">
                    <div class="col-4 form-floating shadow p-0 me-2">
                        <input id="testName" class="form-control" type="text" placeholder="form"/>
                        <label for="testName">Test Name</label>
                    </div>

                    <div class="col-4 form-floating shadow p-0">
                        <input id="additionalInfo" class="form-control" type="text" placeholder="form"/>
                        <label for="additionalInfo">Additional Info</label>
                    </div>

                    <div class="col-3 text-center p-0 mt-auto mb-auto">
                        <button class="btn btn-md btn-secondary w-auto" type="button" onclick="addTest()">
                            Add
                        </button>

                        <button class="btn btn-md btn-danger w-auto" type="button" onclick="toggleAddTestDiv()">
                            Cancel
                        </button>
                    </div>
                </div>
            </div>

            <div class="col-12 text-start">
                <table id="testTable" class="table text-center w-100">
                    <thead class="table-primary">
                        <tr>
                            <th>Name</th>
                            <th>Additional Info</th>
                            <th>Action</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${commandPrescription.testList}" var="test">
                            <tr>
                                <td>${test.testName}</td>
                                <td>${test.additionalInfo}</td>
                                <td>
                                    <input name="testList"
                                           value="${test.testName}-%-${test.additionalInfo}"
                                           hidden/>
                                    <button name="del" type="button" class="btn btn-sm btn-danger" onclick="delRow(this)">
                                        <fmt:message key="btn.remove"/>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>

        <section class="row card border-0 shadow p-4 col-12 col-sm-8 col-md-12 col-lg-9 m-auto mt-1">
            <div class="row text-start ps-0">
                <h5>Attachments</h5>
            </div><hr/>

            <div class="row text-center">
                <div class="col text-end">
                    <input class="form-control form-control-sm" name="file" type="file"/>
                </div>

                <div class="col text-start m-auto">
                    <button name="_action_upload"
                            class="btn btn-sm btn-secondary"
                            type="submit">
                        Upload
                    </button>
                </div>
            </div>

            <div class="col-12 text-start mt-3">
                <table id="attachmentTable" class="table text-center w-100">
                    <thead class="table-primary">
                        <tr>
                            <th>Name</th>
                            <th>Size</th>
                            <th>Type</th>
                            <th>Preview</th>
                            <th>Action</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${commandPrescription.attachments}" var="attacment">
                            <tr>
                                <td><c:out value="${attacment.name}"/></td>
                                <td><c:out value="${attacment.size}"/></td>
                                <td><c:out value="${attacment.type}"/></td>
                                <td><img alt="attachment" src="data:image/jpeg;base64,${attacment.dataInBase64}" height="100px" width="100px"></td>
                                <td>
                                    <button type="button"
                                            class="btn btn-sm btn-danger"
                                            name="_action_remove">
                                        <fmt:message key="btn.remove"/>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>

                </table>
            </div>
        </section>

        <section class="row card border-0 p-3 col-12 col-sm-8 col-md-12 col-lg-9  m-auto mt-2 bg-secondary bg-opacity-50">
            <div class="row pe-0">
                <div class="col-2 text-start">
                    <a href="${pageContext.request.contextPath}/home">
                        <button class="btn btn-sm btn-secondary w-100" type="button">
                            <fmt:message key="btn.cancel"/>
                        </button>
                    </a>
                </div>

                <div class="col-2 offset-8 text-end">
                    <button id="btn-save"
                            name="_action_save_or_update"
                            class="btn btn-sm btn-success w-100"
                            type="submit">
                        <c:choose>
                            <c:when test="${commandPrescription.isNew()}"><fmt:message key="btn.save"/></c:when>
                            <c:otherwise><fmt:message key="btn.update"/></c:otherwise>
                        </c:choose>
                    </button>
                </div>
            </div>
        </section>
    </form:form>
</main>

<script src="${bootstrapJs}" type="text/javascript">
</script>

</body>
</html>
