$(document).ready(function () {
    $("#MessageForm").submit(function (e) {
        e.preventDefault();
    });
    $("#submitButton").click(function (e) {
        var data = $(MessageForm).serialize();
        $.ajax({
            type: "POST",
            url: "new-message",
            data: data,
            datatype: "json",
            success: function (data, textStatus, jqXHR) {
                if (data.success) {
                    window.location.href = "/messages?sendMes=1";
                }
                else if (data.errors) {
                    document.getElementById("author_text_error").innerHTML = "" ;
                    document.getElementById("message_text_error").innerHTML = "";
                    document.getElementById("max_kol_message_text_error").innerHTML = "";
                    for (var key in data.errors) {
                        $("#" + key).text(data.errors[key]);
                    }
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Error in checkMessage.js: " + textStatus);
            }
        });
        return false;
    });

});

