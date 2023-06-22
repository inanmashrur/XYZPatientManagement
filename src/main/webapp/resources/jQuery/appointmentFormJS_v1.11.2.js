$(document).ready(function () {
    const date = $("#appointmentDate").val();

    if (date.length > 0) {
        enable("#appointmentDate", "#btn-save");
    }
});

$("#speciality").change(function () {
    const specialityId = $(this).val();

    if (specialityId === '0') {
        resetDoctor();

        return;
    }

    showLoading();

    $.ajax({
        url: '/user/api/get',
        method: 'GET',
        data: {"specialityId": specialityId},
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,

        success: function (data) {
            var options = '<option value="0">Select Doctor</option>';

            data.forEach(function (item) {
                options += '<option value="' + item.id + '">' + item.name + '</option>';
            });

            $('#doctor').html(options);

            enable("#doctor");

            hideLoading();
        },

        error: function () {
            alert("Something went wrong!");

            hideLoading();
        }
    });
    resetSchedule();
});

$("#doctor").change(function () {
    const doctorId = $(this).val();

    if (doctorId === '0') {
        resetSchedule();

        return;
    }

    showLoading();

    $.ajax({
        url: '/schedule/api/get',
        method: 'GET',
        data: {'doctorId': doctorId},
        contentType: 'application/json; charset=utf-8',
        dataType: "json",
        async: false,

        success: function (data) {
            var options = '<option disabled selected>Select Schedule</option>';

            data.forEach(function (item) {
                options +=
                    '<option value="' + item.id + '">'
                    + 'Loc: ' + item.location +
                    ' | On: ' + item.daysToString +
                    ' : ' + item.timeRangeStr +
                    ' | Fee: ' + item.consultationFee + 'BDT'
                    + '</option>';
            });

            $('#schedule').html(options);

            enable("#schedule");

            hideLoading();
        },

        error: function () {
            alert("Something went wrong!");

            hideLoading();
        }
    });
    resetDate();
});

$("#schedule").change(function () {
    enable("#appointmentDate");
});

$("#appointmentDate").change(function () {
    enable("#btn-save");
});

function enable(...ids) {
    for (const id of ids) {
        $(id).removeAttr('disabled');
    }
}

function disable(...ids) {
    for (const id of ids) {
        $(id).attr('disabled', 'disabled');
    }
}

function resetDoctor() {
    $("#doctor").html('<option></option>');

    disable("#doctor");

    resetSchedule();
}

function resetSchedule() {
    $("#schedule").html('<option></option>');

    disable("#schedule");

    resetDate();
}

function resetDate() {
    $('#appointmentDate').val('').change();

    disable("#appointmentDate", "#btn-save");
}

function showLoading() {
    $("#loading-div").removeClass("hidden");
}

function hideLoading() {
    $("#loading-div").addClass("hidden");
}