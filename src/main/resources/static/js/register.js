$(document).ready(function(){
    $('.input').focus(function(){
        $(this).parent().find(".label-txt").addClass('label-active');
    });

    $(".input").focusout(function(){
        let labelText = $(this).parent().find(".label-txt");
        labelText.removeClass('label-active');
    });
});