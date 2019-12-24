$(document).ready(function(){
    $.fn.selectpicker.Constructor.BootstrapVersion = '4';

    $('.input').focus(function(){
        $(this).parent().find(".label-txt").addClass('label-active');
    });

    $(".input").focusout(function(){
        let labelText = $(this).parent().find(".label-txt");
        labelText.removeClass('label-active');
    });
});