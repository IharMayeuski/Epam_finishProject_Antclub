$(document).ready(function () {
    var $signInForm = $('#loginForm');
    var $responseTag = $('#responseTag');

    $signInForm.validate({
        rules: {
            login: {
                required: true,
                minlength: 5
            },
            userPassword: {
                required: true,
                minlength: 5
            }
        }
    });

    $signInForm.ajaxForm(function (responseJson) {
        $responseTag.empty();
        switch (responseJson['AJAX_STATE']) {
            case 'ERROR':
                $responseTag.removeClass("hidden");
                $responseTag.text(responseJson['JSON']);
                break;
            case 'REDIRECT':
                window.location.replace(responseJson['JSON']);
                break;
        }
    });

});