$(function () {
    let $usercode = $("#usercode");
    let $password = $("#password");
    // let $userroles = $("#userroles");
    let $reset = $("#reset");
    let $submit = $("#submit");
    // let $rememberme = $("#remember-me");
    $(document).ajaxStart(function () {
        $.blockUI({
            message: $('#loadingSpinner'),
            css: {
                top: ($(window).height() - 100) / 2 + 'px',
                left: ($(window).width() - 100) / 2 + 'px',
                width: '100px',
                border: 'none',
                backgroundColor: 'none',
                color: '#fff'
            },
            overlayCSS: {backgroundColor: '#808080'}
        });

    }).ajaxStop($.unblockUI);

    $reset.click(function () {
        $password[0].usercode = "";
        $password[0].password = "";
        // $userroles.empty();
    });
    $submit.click(function () {
        $password[0].usercode = "";
        $password[0].password = "";
    });
    $("form[role='form']").keypress(function (e) {
            // 回车键事件
            if (e.which === 13) {
                initForm();
            }
        }
    );

    $password.blur(function () {
        let usercode = $usercode.val();
        let password = $password.val();
        if (this.usercode === usercode && this.password === password) {
            return
        }
        // $userroles.empty();
        if (usercode === "" || usercode === undefined) {
            return;
        }
        if (password === "" || password === undefined) {
            return;
        }
        // $.ajax({
        //     type: "GET",
        //     url: "auth/userRoles",
        //     data: "usercode=" + usercode + "&password=" + password,
        //     success: function (data, textStatus, jqXHR) {
        //         if (data === undefined || !(data instanceof Array) || data.length === 0) {
        //             $(location).attr("href", "login?error")
        //             return;
        //         }
        //         $.each(data, function (index, role) {
        //             if (index === 0) {
        //                 $userroles.append("<option selected=true value='" + role.id + "'>" + role.name + "</option>")
        //             } else {
        //                 $userroles.append("<option value='" + role.id + "'>" + role.name + "</option>")
        //             }
        //         });
        //         if (data.length === 1) {
        //             $userroles.attr("disabled", "disabled");
        //             $submit.focus();
        //         } else {
        //             $userroles.removeAttr("disabled");
        //             $userroles.focus();
        //         }
        //         // $userroles.trigger("change");
        //         $userroles.change();
        //     },
        //     error: function (XMLHttpRequest, textStatus, errorThrown) {
        //         let res = XMLHttpRequest.responseJSON;
        //         $(location).attr("href", "login?error")
        //     }
        // });
        this.usercode = usercode;
        this.password = password;
    });

    function initForm() {
        let usercode = $usercode.val();
        let password = $password.val();
        // let selectuserrole = $userroles.children("option:selected").val();

        if (usercode !== "" && usercode !== undefined) {
            if (password !== "" && password !== undefined) {
                // if (selectuserrole !== "" && selectuserrole !== undefined) {
                //     $userroles.attr("disabled", "disabled");
                //     $submit.click();
                // } else {
                $password.blur();
                // $userroles.focus();
                // }
            } else {
                $password.focus();
            }
        }
    }

    initForm();

});